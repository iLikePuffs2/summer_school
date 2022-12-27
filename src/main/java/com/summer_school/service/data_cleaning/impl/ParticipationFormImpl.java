package com.summer_school.service.data_cleaning.impl;

import com.summer_school.dao.HotSpotDao;
import com.summer_school.dao.SignInDao;
import com.summer_school.dao.StudentDao;
import com.summer_school.dao.TopicDao;
import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.pojo.dto.Conference;
import com.summer_school.pojo.dto.ParticipationHelper;
import com.summer_school.pojo.dto.TopicHelper;
import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.HotSpotAttendance;
import com.summer_school.pojo.po.Student;
import com.summer_school.pojo.po.TopicAttendance;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParticipationFormImpl implements FormCleaningService {
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String sumUser;
    private List<String> userName = new ArrayList<>();
    private List<String> schoolName = new ArrayList<>();

    private List enterTime = new ArrayList<>();
    private List quitTime = new ArrayList<>();

    private List hotSpotStartTime = new ArrayList<>();
    private List hotSpotEndTime = new ArrayList<>();
    private List hotSpotLength = new ArrayList<>();

    private List<String> onceLength = new ArrayList<>();

    private List<Student> studentList = new ArrayList<>();

    private List<ParticipationHelper> helperList = new ArrayList<>();
    private List<TopicHelper> topicList = new ArrayList<>();

    private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");

    @Autowired
    HotSpotDao hotSpotDao;
    @Autowired
    TopicDao topicDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    SignInDao signInDao;


    /**
     * 将表格里每列的数据读到一个list里
     *
     * @return 这些list的集合
     */
    @Override
    public void readToList(CleanInfo cleanInfo) throws Exception {

        String fileURL = cleanInfo.getFileURL();
        File excel = new File(fileURL);
        Workbook wb;

        wb = new XSSFWorkbook(new FileInputStream(excel));

        //开始解析
        //读取sheet 1
        Sheet sheet = wb.getSheetAt(1);

        int lastRowIndex = sheet.getLastRowNum();

        Row row = sheet.getRow(3);
        Cell cell = row.getCell(1);

        //读取预定开始时间
        scheduleStartTime = cell.toString();

        //读取预定结束时间
        row = sheet.getRow(4);
        cell = row.getCell(1);
        scheduleEndTime = cell.toString();

        //读取参会用户总数
        row = sheet.getRow(6);
        cell = row.getCell(1);
        sumUser = cell.toString();


        //遍历行
        for (int rIndex = 9; rIndex <= lastRowIndex; rIndex++) {
            row = sheet.getRow(rIndex);
            if (row != null) {
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();

                //遍历列
                for (int cIndex = firstCellIndex; cIndex < 7; cIndex++) {
                    switch (cIndex) {
                        case 0:
                            userName.add(row.getCell(cIndex).toString());
                            break;
                        case 4:
                            enterTime.add(row.getCell(cIndex).toString());
                            break;
                        case 5:
                            quitTime.add(row.getCell(cIndex).toString());
                            break;
                        case 6:
                            onceLength.add(row.getCell(cIndex).toString());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * 调用多个相关清洗算法进行清洗
     *
     * @return
     */
    @Override
    public void clean() {

        //清洗名字+学校名称
        cleanUserNameAndSchool();

    }

    /**
     * 调用相关分析算法进行分析
     */
    @Override
    public void analyze(CleanInfo cleanInfo) {
        //把入会时间转为date类型
        cleanTime(enterTime);
        //把退出时间转为date类型
        cleanTime(quitTime);

        //获取数据库里这一天的每场研究热点的开始时间、结束时间、时长
        getHotSpotInfo(cleanInfo);

        //获取学生表里这个暑期学校的全部学生信息
        getStudentInfo(cleanInfo);

        //把之前有用的数据装在helperList里
        addToHelperList();

        //标记参会数据是否有效(对比studentList,找出在学生表里有的数据,标记学生id)
        markIsEffectiveAndStudentId();

        //分析并保存参会详情表里每个研究热点的全部相关数据
        analyzeEachHotSpotAndSave(cleanInfo);

        //分析并保存主题的全部相关数据
        analyzeEachTopicAndSave(cleanInfo);

    }

    /**
     * 将清洗后的数据存入相应的位置
     *
     * @return
     */
    @Override
    public boolean save(CleanInfo cleanInfo) {
        return false;
    }


    /**
     * 将参会详情表混乱的用户名清洗为名字+大学名称
     * 和清洗报名表不同的地方在于：无效名字和大学名称不会被设为"",而是会被保留（为了后面能正常分析旁观者数据）
     * 总体逻辑：
     * 先将名字这个字符串根据()用.split方法划分——绝大多数情况会得到2个元素
     * 经观察,元素2准确度更高,所以优先考虑对元素2进行.split划分
     * 元素2如果被拆分为三元素/双元素,只看元素2即可
     * 元素2如果被拆分为单元素(此时才用到元素1),又分多种情况：
     * 对元素1用.split方法划分,元素1如果被拆分为三元素/双元素,只看元素1即可(方法同只看元素2)
     * 元素1如果被拆分为单元素,此时：
     * 如果元素1,2都不是纯中文,判断为无效数据
     * 如果元素1,2一个不是纯中文,另一个是,就对纯中文进行(名字+学校名称提取)
     * 如果元素1,2都是纯中文,就对它们都进行(名字+学校名称提取),如果两边提取后的名字equals,就被认为是有效名字;学校名称equals,就被认为是有效学校名称
     * 如果被划分成1个元素,名字和学校名字都无效
     */
    void cleanUserNameAndSchool() {
        List<String> stringList;
        List<String> testList;
        String eachUserName = "";
        String eachSchoolName = "";

        for (int i = 0; i < userName.size(); i++) {
            //拿到原始用户名
            String s = userName.get(i);

            //先只去掉括号(),将原始用户名划分为list里的2个元素(只有1个元素就被判定无效数据)
            stringList = Arrays.asList(s.split("[()]"));

            //被()划分为2个元素的数据进行正常处理
            if (stringList.size() == 2) {
                String element1 = stringList.get(0);
                String element2 = stringList.get(1);

                //对元素2再进行一次划分,进行处理
                testList = Arrays.asList(element2.split("[- _+]"));

                //元素2的三元素处理
                //逻辑：哪个字段有“大||学院”,哪个字段就代表大学;2<=长度<=3且不包含”院“字,就代表名字
                if (testList.size() == 3) {
                    boolean schoolName = false;
                    String tempSchoolName = null;
                    boolean profession = false;

                    for (int j = 0; j < 3; j++) {
                        if (testList.get(j).contains("大")) {
                            schoolName = true;
                            tempSchoolName = testList.get(j);
                        }

                        if (testList.get(j).contains("学院")) {
                            profession = true;
                        }

                        if (testList.get(j).contains("大") || testList.get(j).contains("学院")) {
                            eachSchoolName = testList.get(j);
                        }

                        if (testList.get(j).length() >= 2 && testList.get(j).length() <= 3 && !(testList.get(j).contains("院"))) {
                            eachUserName = testList.get(j);
                        }
                    }

                    if (schoolName && profession) {
                        eachSchoolName = tempSchoolName;
                    }

                    //元素2的双元素处理
                } else if (testList.size() == 2) {
                    //逻辑:哪个字段有“大||学院”,哪个字段就代表大学,另一个代表名字
                    if (testList.get(0).contains("大") || testList.get(0).contains("学院")) {
                        eachSchoolName = testList.get(0);
                        eachUserName = testList.get(1);
                    } else if (testList.get(1).contains("大") || testList.get(1).contains("学院")) {
                        eachSchoolName = testList.get(1);
                        eachUserName = testList.get(0);
                    }

                    //元素2的单元素处理(此时才用到元素1)
                } else if (testList.size() == 1) {
                    //对元素1再进行一次划分,进行处理
                    testList = Arrays.asList(element1.split("[- _+]"));

                    //元素1的三元素处理
                    if (testList.size() == 3) {
                        boolean schoolName = false;
                        String tempSchoolName = null;
                        boolean profession = false;

                        for (int j = 0; j < 3; j++) {
                            if (testList.get(j).contains("大")) {
                                schoolName = true;
                                tempSchoolName = testList.get(j);
                            }

                            if (testList.get(j).contains("学院")) {
                                profession = true;
                            }

                            if (testList.get(j).contains("大") || testList.get(j).contains("学院")) {
                                eachSchoolName = testList.get(j);
                            }

                            if (testList.get(j).length() >= 2 && testList.get(j).length() <= 3 && !(testList.get(j).contains("院"))) {
                                eachUserName = testList.get(j);
                            }
                        }

                        if (schoolName && profession) {
                            eachSchoolName = tempSchoolName;
                        }

                        //元素1的双元素处理
                    } else if (testList.size() == 2) {
                        //逻辑:哪个字段有“大||学院”,哪个字段就代表大学,另一个代表名字
                        if (testList.get(0).contains("大") || testList.get(0).contains("学院")) {
                            eachSchoolName = testList.get(0);
                            eachUserName = testList.get(1);
                        } else if (testList.get(1).contains("大") || testList.get(1).contains("学院")) {
                            eachSchoolName = testList.get(1);
                            eachUserName = testList.get(0);
                        }

                        //元素1的单元素处理
                    } else if (testList.size() == 1) {

                        /**
                         *
                         */

                        //如果元素1,2都不是纯中文,不对他进行改动
                        if (!isAllChinese(element1) && !isAllChinese(element2)) {

                            eachUserName = s;
                            eachSchoolName = s;

                            /**
                             * 如果元素1,2都是纯中文,就对它们都进行(名字+学校名称提取)
                             * 如果两边提取后的名字equals,就被认为是有效名字
                             * 学校名称equals,就被认为是有效学校名称
                             */
                        } else if (isAllChinese(element1) && isAllChinese(element2)) {
                            String[] elementString1 = extractNameAndSchool(element1);
                            String[] elementString2 = extractNameAndSchool(element2);

                            //两个名字equals
                            if (elementString1[0].equals(elementString2[0])) {
                                eachUserName = elementString1[0];
                            } else {
                                eachUserName = s;
                            }

                            //两个学校名称equals
                            if (elementString1[1].equals(elementString2[1])) {
                                eachSchoolName = elementString1[1];
                            } else {
                                if ("".equals(elementString1[1])) {
                                    eachSchoolName = elementString2[1];
                                }
                            }

                            //如果元素1,2一个不是纯中文,另一个是,就对纯中文进行(名字+学校名称提取)
                        } else {
                            if (isAllChinese(element1)) {
                                String[] elementString1 = extractNameAndSchool(element1);
                                eachUserName = elementString1[0];
                                eachSchoolName = elementString1[1];

                                //element2是纯中文
                            } else {
                                String[] elementString2 = extractNameAndSchool(element2);
                                eachUserName = elementString2[0];
                                eachSchoolName = elementString2[1];
                            }

                        }

                    }
                }

                //被()划分为1个元素的数据不动
            } else {
                eachUserName = s;
                eachSchoolName = s;
            }

            //在if-else结束后才进行设置和add,防止多add数据导致全部数据错位
            userName.set(i, eachUserName);
            schoolName.add(eachSchoolName);

        }
    }


    /**
     * 判断字符串是否为纯中文
     *
     * @param str 待校验字符串
     * @return 是否全是中文
     */
    public static boolean isAllChinese(String str) {
        if (str == null) {
            return false;
        }
        Matcher m = pattern.matcher(str);

        boolean test = m.matches();
        return m.matches();
    }

    /**
     * 名字+学校名称提取(判定为纯中文才进行)：
     * 长度<=3,被认为只有名字
     * “大学”及其前面的字为学校名称,后面的为名字
     * “学院”及其前面的字为学校名称,后面的为名字
     * “大”及其前面的字为学校名称,后面的为名字
     *
     * @param rawSingleString 不含任何字符的纯中文
     * @return [0]是名字, [1]是学校名称
     */
    public static String[] extractNameAndSchool(String rawSingleString) {
        String[] nameAndSchool = {"", ""};
        int index = 0;

        //2<=长度<=3,被认为只有名字
        if (rawSingleString.length() >= 2 && rawSingleString.length() <= 3) {
            nameAndSchool[0] = rawSingleString;
        } else if (rawSingleString.length() > 3) {
            if (rawSingleString.contains("大学")) {
                index = rawSingleString.indexOf("大学");
                //名字
                nameAndSchool[0] = rawSingleString.substring(index + 2);
                //学校名称
                nameAndSchool[1] = rawSingleString.substring(0, index + 2);
            } else if (rawSingleString.contains("学院")) {
                index = rawSingleString.indexOf("学院");
                //名字
                nameAndSchool[0] = rawSingleString.substring(index + 2);
                //学校名称
                nameAndSchool[1] = rawSingleString.substring(0, index + 2);
            } else if (rawSingleString.contains("大")) {
                index = rawSingleString.indexOf("大");
                //名字
                nameAndSchool[0] = rawSingleString.substring(index + 1);
                //学校名称
                nameAndSchool[1] = rawSingleString.substring(0, index + 1);
            }
        }

        return nameAndSchool;
    }


    /**
     * 把String类型时间转为date类型
     *
     * @param list
     */
    public void cleanTime(List list) {
        for (int i = 0; i < list.size(); i++) {
            String str1 = list.get(i).toString();
            Date date = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = formatter.parse(str1);
                list.set(i, date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 计算时间差(分钟)
     */
    public int calculateTimeDifference(Date startTime, Date endTime) {
        //计算两个日期的分钟差
        int seconds = (int) Duration.between(startTime.toInstant(), endTime.toInstant()).getSeconds();
        int minute = seconds / 60;

        return minute;
    }


    /**
     * 根据前端传入的主题id,获取数据库里这一天的每场研究热点的：
     * 开始时间
     * 结束时间
     * 时长
     *
     * @param cleanInfo
     */
    public void getHotSpotInfo(CleanInfo cleanInfo) {

        //根据主题id找到每场研究热点的开始和结束时间
        List<HotSpot> hotSpotList = hotSpotDao.selectTimeByTopicId(cleanInfo.getTopicId());

        for (int i = 0; i < hotSpotList.size(); i++) {
            hotSpotStartTime.add(hotSpotList.get(i).getStartTime());
            hotSpotEndTime.add(hotSpotList.get(i).getEndTime());
        }

        //再转为date类型
        cleanTime(hotSpotStartTime);
        cleanTime(hotSpotEndTime);

        //计算每场的时长
        for (int i = 0; i < hotSpotStartTime.size(); i++) {
            int minute = calculateTimeDifference((Date) hotSpotStartTime.get(i), (Date) hotSpotEndTime.get(i));
            hotSpotLength.add(minute);
        }

    }


    /**
     * 根据前端传入的暑期学校id,获取学生表里这个暑期学校的全部学生信息
     */
    private void getStudentInfo(CleanInfo cleanInfo) {
        studentList = studentDao.showPartStudentInfo(cleanInfo.getSummerSchoolId());
    }

    /**
     * 把之前有用的数据装在helperList里:
     * 名字
     * 学校名称
     * 入会时间
     * 退会时间
     */
    private void addToHelperList() {

        for (int i = 0; i < userName.size(); i++) {
            ParticipationHelper participationHelper = new ParticipationHelper();
            participationHelper.setName(userName.get(i));
            participationHelper.setSchoolName(schoolName.get(i));
            participationHelper.setEnterTime((Date) enterTime.get(i));
            participationHelper.setQuitTime((Date) quitTime.get(i));
            participationHelper.setOnceRestrictedLength(0);
            participationHelper.setSumEffectiveLength(0);
            participationHelper.setIsAllEffective(false);
            participationHelper.setIsRestrictedEffective(false);
            participationHelper.setCumulativeTimeQualified(false);
            participationHelper.setType(0);
            participationHelper.setActiveScore(0);
            participationHelper.setActiveScoreTopic(0);
            participationHelper.setActiveSumTimesTopic(0);
            participationHelper.setSignInScore(0);
            participationHelper.setSignInScoreTopic(0);
            participationHelper.setCommitmentScore(0);
            participationHelper.setCommitmentScoreTopic(0);
            helperList.add(participationHelper);
        }
    }


    /**
     * 判定单次参会时长是否满足这次有效参会时长的判定条件
     */
    private boolean judgeIsEffective(int minute) {

        //如果参会时长小等于5min,就被判定为无效参会数据;反之,被判定为有效
        if (minute <= 5) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 判定学生/旁观者的累计参会时长是否达到有效参与这场会议的最低标准（会议时长的30%)
     *
     * @param conferenceLength
     * @param cumulativeLength
     * @return
     */
    private boolean judgeIsCumulativeQualified(Integer conferenceLength, Integer cumulativeLength) {
        boolean flag = false;
        if ((cumulativeLength * 100) > (conferenceLength * 30)) {
            flag = true;
        }

        return flag;
    }

    /**
     * 标记参会数据是否有效+(对比studentList,找出在学生表里有的数据,标记学生id)
     * 双重循环,第一层循环标记参会数据是否有效
     * 第二层循环找出在学生表里有的数据,标记学生id
     */
    private void markIsEffectiveAndStudentId() {
        for (int i = 0; i < helperList.size(); i++) {

            //得到入会时间和退会时间的分钟差,设置单次参会时长
            int minute = calculateTimeDifference(helperList.get(i).getEnterTime(), helperList.get(i).getQuitTime());
            helperList.get(i).setOnceLength(minute);

            //标记参会数据是否有效
            boolean judge = judgeIsEffective(minute);
            helperList.get(i).setIsAllEffective(judge);

            //如果被判定为有效参会,再遍历学生表,标记学生id
            if (judge) {
                for (int j = 0; j < studentList.size(); j++) {

                    //如果i的学生名字、学校名称和j的相同,就把j的id赋给i
                    if (helperList.get(i).getName().equals(studentList.get(j).getStudentName()) && helperList.get(i).getSchoolName().equals(studentList.get(j).getSchoolName())) {
                        helperList.get(i).setStudentId(studentList.get(j).getId());
                    }
                }
            }

        }
    }


    /**
     * 分析并保存参会详情表里每个研究热点的全部相关数据
     */
    private void analyzeEachHotSpotAndSave(CleanInfo cleanInfo) {

        //每次循环处理一个研究热点
        for (int i = 0; i < cleanInfo.getHotSpotId().length; i++) {


            /**
             * 一开始没想明白，其实根本不用三个循环
             * 1个双重循环就能搞定全部(参考处理主题的循环)
             */


            /**
             * 对许可范围内的单次参会时长进行有效参会判定,set进helperList
             * 如果被判定为有效参会,先计算积极性评分
             * 再计算并返回这条数据在许可范围内的起始时间、终止时间、时长
             */
            //循环1(单重循环),遍历helperList,先判断是否为有效参会
            for (int j = 0; j < helperList.size(); j++) {

                //如果是有效参会
                if (helperList.get(j).isIsAllEffective()) {
                    Conference conference = new Conference((Date) hotSpotStartTime.get(i), (Date) hotSpotEndTime.get(i), (Integer) hotSpotLength.get(i));
                    Conference data = new Conference(helperList.get(j).getEnterTime(), helperList.get(j).getQuitTime(), helperList.get(j).getOnceLength());

                    //计算积极性评分
                    int activeScore = calculateActiveScore((Date) hotSpotStartTime.get(i), data);

                    //计算并返回这条数据在许可范围内的起始时间、终止时间、时长(用Conference对象封装)
                    data = calculateRestrictedOnceLength(conference, data);

                    //如果data不为空,打标记+设置许可范围内的单次参会时长+积极性评分
                    //如果为空,打标记+设置0、0
                    if (data != null) {
                        helperList.get(j).setIsRestrictedEffective(true);
                        helperList.get(j).setOnceRestrictedLength(data.getLength());
                        helperList.get(j).setActiveScore(activeScore);
                    } else {
                        helperList.get(j).setIsRestrictedEffective(false);
                        helperList.get(j).setOnceRestrictedLength(0);
                        helperList.get(j).setActiveScore(0);
                    }
                }
            }

            //循环2(双重循环)(设置了type属性)
            for (int j = 0; j < helperList.size() - 1; j++) {

                //进入第二层循环前先判断是否为许可范围内的有效参会数据,如果不是直接continue
                if (helperList.get(j).isIsRestrictedEffective()) {

                    //如果是,再判断这个i的type值为是否为2
                    if (helperList.get(j).getType() == 2) {
                        //如果是,continue
                        continue;

                        //反之,先将自己的type值设为1,再进入第二层循环
                    } else {
                        helperList.get(j).setType(1);

                        //进入第二层循环
                        for (int k = j + 1; k < helperList.size(); k++) {

                            //如果j和k的姓名、学校名称相同(代表他们是同一个人的不同时间段的参会数据),做3件事：
                            if (helperList.get(j).getName().equals(helperList.get(k).getName()) && helperList.get(j).getSchoolName().equals(helperList.get(k).getSchoolName())) {

                                //1. k的type值设为2
                                helperList.get(k).setType(2);

                                //2. k的许可范围内的有效参会时长累加到j的许可范围内的有效参会时长
                                helperList.get(j).setOnceRestrictedLength(helperList.get(j).getOnceRestrictedLength() + helperList.get(k).getOnceRestrictedLength());
                                //最大值不能超过会议时长
                                if (helperList.get(j).getOnceRestrictedLength() >= (Integer) hotSpotLength.get(i)) {
                                    helperList.get(j).setOnceRestrictedLength((Integer) hotSpotLength.get(i));
                                }

                                //3. 比较j和k的积极性评分,把更大的值赋给j
                                if (helperList.get(j).getActiveScore() < helperList.get(k).getActiveScore()) {
                                    helperList.get(j).setActiveScore(helperList.get(k).getActiveScore());
                                }
                            }
                        }
                    }


                } else {
                    continue;
                }
            }

            int participantNum = 0;
            int countActive = 0;

            //循环3(检验type值后,把type归0,为下一次的循环2做准备)
            for (int j = 0; j < helperList.size(); j++) {

                //如果type的值不是1,就把它设为0,然后continue
                if (helperList.get(j).getType() != 1) {
                    helperList.get(j).setType(0);
                    continue;

                    //如果type的值是1(有效参会者的主体)
                } else if (helperList.get(j).getType() == 1) {

                    //就先把它设为0
                    helperList.get(j).setType(0);

                    //判定主体是不是有效参与,如果是,才做后续事情
                    if (judgeIsCumulativeQualified((Integer) hotSpotLength.get(i), helperList.get(j).getOnceRestrictedLength())) {

                        //将cumulativeTimeQualified设为true
                        helperList.get(j).setCumulativeTimeQualified(true);

                        //让participantNum++(旁观者/学生)(type值为1的都是有效参会数据)
                        participantNum++;

                        //如果他的积极性评分不为0,countActive++
                        if (helperList.get(j).getActiveScore() != 0) {
                            countActive++;
                        }

                        //如果他的学生id不为空(说明是暑期学校学生),就做下列事情
                        if (helperList.get(j).getStudentId() != null) {

                            Integer testHotId = cleanInfo.getHotSpotId()[i];
                            Integer testStuId = helperList.get(j).getStudentId();

                            //1.用这个学生id和研究热点id,从签到表里查出他的签到评分,set进签到得分里
                            Integer score = signInDao.selectSignInScore(cleanInfo.getHotSpotId()[i], helperList.get(j).getStudentId());

                            //考虑null的情况
                            if (score != null) {
                                helperList.get(j).setSignInScore(score);
                            } else {
                                score = 0;
                            }


                            //2.把(会议时长、许可范围内的累计参会时长、签到得分、积极性评分)传入计算研究热点学习投入度方法,计算出学习投入度
                            int commitment = calculateHotSpotCommitment((Integer) hotSpotLength.get(i), helperList.get(j).getOnceRestrictedLength(), score, helperList.get(j).getActiveScore());
                            helperList.get(j).setCommitmentScore(commitment);

                            //3.把研究热点id、学生id、许可范围内的累计参会时长、积极性评分、学习投入度存入研究热点参与表里
                            HotSpotAttendance hotSpotAttendance = new HotSpotAttendance();
                            hotSpotAttendance.setResearchHotSpotId(cleanInfo.getHotSpotId()[i]);
                            hotSpotAttendance.setStudentId(helperList.get(j).getStudentId());
                            hotSpotAttendance.setEffectiveAttendanceTime(helperList.get(j).getOnceRestrictedLength());
                            hotSpotAttendance.setActiveScore(helperList.get(j).getActiveScore());
                            hotSpotAttendance.setCommitmentIndex(commitment);

                            //每次插入数据前用研究热点id和学生id找一下有没有重复的,防止重复插入
                            HotSpotAttendance tempAttendanceList = hotSpotDao.selectByHotSpotIdAndStudentId(cleanInfo.getHotSpotId()[i], helperList.get(j).getStudentId());
                            //如果对象为null,说明数据库里没有,这才能insert
                            if (tempAttendanceList == null) {
                                hotSpotDao.addAttendanceInfo(hotSpotAttendance);
                            }
                        }
                    } else {
                        //将cumulativeTimeQualified设为false
                        helperList.get(j).setCumulativeTimeQualified(false);
                    }


                }
            }

            /**
             * 在即将退出循环这个研究热点循环,进入下一个研究热点循环的时候:
             * 根据participantNum和countActive计算积极参与率activeRate(double型)
             * 再根据研究热点id把participantNum和activeRate存入之前数据不完整的研究热点表的数据(update语句)
             */
            double activeRate = (double) (countActive) / (double) (participantNum);
            BigDecimal bigDecimal = new BigDecimal(activeRate).setScale(2, RoundingMode.HALF_UP);
            activeRate = bigDecimal.doubleValue();
            hotSpotDao.updateParticipation(cleanInfo.getHotSpotId()[i], participantNum, activeRate);
        }
    }

    /**
     * 计算并返回许可范围内的起始时间、终止时间、有效时长:
     * 传入的参数包括：
     * 传入热点会议的起始时间、终止时间、时长
     * 参会数据的起始时间、终止时间、时长
     * 逻辑：
     * 根据两个时长的不同和交集位置的不同,一共可归纳为6种情况：画图很清晰
     */
    Conference calculateRestrictedOnceLength(Conference conference, Conference data) {

        Date conferenceStart = conference.getStartTime();
        Date conferenceEnd = conference.getEndTime();
        Date dataStart = data.getStartTime();
        Date dataEnd = data.getEndTime();

        //1.data在conference左边,且无交集
        if (calculateTimeDifference(dataEnd, conferenceStart) >= 0) {
            data = null;
        }

        //2.data在conference左边,有交集
        if (calculateTimeDifference(dataStart, conferenceStart) > 0 && calculateTimeDifference(conferenceStart, dataEnd) > 0) {
            int onceLength = calculateTimeDifference(conferenceStart, dataEnd);
            //如果交集时间差满足最低有效时长判定,就返回一个Conference对象,里面包含许可范围内的起始时间、终止时间、有效时长、积极度评分
            if (judgeIsEffective(onceLength)) {
                data = new Conference(conferenceStart, dataEnd, onceLength);
            } else {
                data = null;
            }
        }

        //3.data在conference内部(包括完全重合的情况)
        if (calculateTimeDifference(conferenceStart, dataStart) >= 0 && calculateTimeDifference(dataEnd, conferenceEnd) >= 0) {
            data = new Conference(dataStart, dataEnd, data.getLength());
        }

        //4.conference在data内部
        if (calculateTimeDifference(dataStart, conferenceStart) >= 0 && calculateTimeDifference(conferenceEnd, dataEnd) >= 0) {
            data = new Conference(conferenceStart, conferenceEnd, conference.getLength());
        }

        //5.data在conference右边,有交集
        if (calculateTimeDifference(dataStart, conferenceEnd) > 0 && calculateTimeDifference(conferenceEnd, dataEnd) > 0) {
            int onceLength = calculateTimeDifference(dataStart, conferenceEnd);
            if (judgeIsEffective(onceLength)) {
                data = new Conference(dataStart, conferenceEnd, onceLength);
            } else {
                data = null;
            }
        }

        //6.data在conference右边,且无交集
        if (calculateTimeDifference(conferenceEnd, dataStart) >= 0) {
            data = null;
        }

        //如果data不为空,检查data的单次有效参会时长,防止超过上线
        if (data != null) {
            if (data.getLength() >= conference.getLength()) {
                data.setLength(conference.getLength());
            }
        }


        return data;
    }

    //计算积极性评分
    int calculateActiveScore(Date conferenceStartTime, Conference data) {

        int score = 0;

        //将会议实际开始时间前的15min视作积极性判定的起始时间
        //将会议实际开始时间后的5min视作积极性判定的结束时间
        long startLength = 15 * 60 * 1000;
        long endLength = 5 * 60 * 1000;
        Date conferenceStart = new Date(conferenceStartTime.getTime() - startLength);
        Date conferenceEnd = new Date(conferenceStartTime.getTime() + endLength);

        Date dataStart = data.getStartTime();
        Date dataEnd = data.getEndTime();

        //1.data在conference左边,且无交集
        if (calculateTimeDifference(dataEnd, conferenceStart) >= 0) {
            score = 0;
        }


        //2.data在conference左边,有交集
        if (calculateTimeDifference(dataStart, conferenceStart) > 0 && calculateTimeDifference(conferenceStart, dataEnd) > 0) {
            int onceLength = calculateTimeDifference(conferenceStart, dataEnd);

            //如果交集时间不少于3min,就视作他最早就来了(评分为满分),否则还是0分
            if (onceLength >= 3) {
                score = calculateActiveScoreFormula(conferenceStart, conferenceStart);
            }
        }

        //3.data在conference内部(包括完全重合的情况)
        if (calculateTimeDifference(conferenceStart, dataStart) >= 0 && calculateTimeDifference(dataEnd, conferenceEnd) >= 0) {
            score = calculateActiveScoreFormula(conferenceStart, dataStart);
        }

        //4.conference在data内部——连续上课——视作他最早就来了(评分为满分)
        if (calculateTimeDifference(dataStart, conferenceStart) >= 0 && calculateTimeDifference(conferenceEnd, dataEnd) >= 0) {
            score = calculateActiveScoreFormula(conferenceStart, conferenceStart);
        }

        //5.data在conference右边,有交集
        if (calculateTimeDifference(dataStart, conferenceEnd) > 0 && calculateTimeDifference(conferenceEnd, dataEnd) > 0) {
            int onceLength = calculateTimeDifference(dataStart, conferenceEnd);

            //如果交集时间不少于3min,就进行评分
            if (onceLength >= 3) {
                score = calculateActiveScoreFormula(conferenceStart, dataStart);
            }
        }

        //6.data在conference右边,且无交集
        if (calculateTimeDifference(conferenceEnd, dataStart) >= 0) {
            score = 0;
        }

        return score;
    }

    /**
     * 计算积极性评分的函数表达式
     * 二次函数拟合,总共20min
     * 在会议开始前15min到得100分,在会议开始后5min到得0分(只有这20min之内到才有分)
     *
     * @param conferenceActiveStart
     * @param enterTime
     * @return
     */
    int calculateActiveScoreFormula(Date conferenceActiveStart, Date enterTime) {
        //计算分钟差
        int minute = calculateTimeDifference(conferenceActiveStart, enterTime);

        //y=-0.25x²+100
        int score = (int) (-0.25 * minute * minute + 100);

        if (score < 0) {
            score = 0;
        }
        return score;
    }


    //计算研究热点的学习投入度
    int calculateHotSpotCommitment(Integer conferenceLength, Integer cumulativeLength, Integer signInScore, Integer activeScore) {

        int sumScore = 0;
        double lengthScore = 0;

        //计算有效参会总时长得分(基础分50——大家都有,额外分最高25——线性)
        lengthScore = 50 + 25 * ((double)cumulativeLength / (double)conferenceLength);

        //计算签到得分(实际签到得分的1/4,满分25)
        signInScore = signInScore / 4;

        //计算积极性得分(实际积极性得分的1/4,满分25)
        activeScore = activeScore / 4;

        sumScore = (int)lengthScore + signInScore + activeScore;

        if (sumScore > 100) {
            sumScore = 100;
        }

        return sumScore;
    }


    //计算主题的学习投入度
    int calculateTopicCommitment(Integer cumulativeLength, Integer signInScore, Integer activeScore) {

        int sumScore = 0;

        //计算主题总时间
        int sumTime = 0;
        for (int i = 0; i < hotSpotLength.size(); i++) {
            sumTime += (Integer) hotSpotLength.get(i);
        }

        //计算有效参会总时长得分(最高80——线性)
        double lengthScore = 80 * ((double)cumulativeLength / (double)sumTime);

        //计算签到得分(实际签到得分的1/4,满分25)
        signInScore = signInScore / 4;

        //计算积极性得分(实际积极性得分的1/4,满分25)
        activeScore = activeScore / 4;

        sumScore = (int)lengthScore + signInScore + activeScore;

        if (sumScore > 100) {
            sumScore = 100;
        }


        return sumScore;
    }

    //分析并保存主题的全部相关数据
    private void analyzeEachTopicAndSave(CleanInfo cleanInfo) {

        //第一轮循环,把helperList里有效的数据的有用的部分挪到topicList里
        for (int i = 0; i < helperList.size(); i++) {

            //如果单次时长>5min
            if (helperList.get(i).isIsAllEffective()) {

                Integer[] integers = new Integer[cleanInfo.getHotSpotId().length];

                TopicHelper topicHelper = new TopicHelper();
                topicHelper.setName(helperList.get(i).getName());
                topicHelper.setSchoolName(helperList.get(i).getSchoolName());
                topicHelper.setStudentId(helperList.get(i).getStudentId());
                topicHelper.setEnterTime(helperList.get(i).getEnterTime());
                topicHelper.setQuitTime(helperList.get(i).getQuitTime());
                topicHelper.setOnceLength(helperList.get(i).getOnceLength());
                topicHelper.setId(0);
                topicHelper.setEachLength(integers);
                topicHelper.setSumLength(0);
                topicHelper.setActiveScore(0);
                topicHelper.setActiveNumber(0);
                topicHelper.setSignInScore(0);
                topicHelper.setCommitment(0);

                topicList.add(topicHelper);
            }
        }

        //参与主题的有效人数
        int participantNum = 0;

        //参与主题的积极人数
        int countActive = 0;

        //第二轮循环（双层自我循环）
        for (int i = 0; i < topicList.size() - 1; i++) {

            //如果topicList的id=0，说明没被遍历过
            if (topicList.get(i).getId() == 0) {
                //先把topicList标记为1
                topicList.get(i).setId(1);

                //计算对主题的有效参会总时长(算i的，然后加到i里）
                Integer sumLength = calculateTopicEffectTime(topicList.get(i));
                topicList.get(i).setSumLength(sumLength);

                //计算对主题的积极性评分(算j的，然后加到i里）
                Integer activeScore = calculateTopicActiveScore(topicList.get(i));
                topicList.get(i).setActiveScore(activeScore);

                //计算对主题的积极参与次数(算j的，然后加到i里）
                if (activeScore > 0) {
                    int now = topicList.get(i).getActiveNumber() + 1;
                    topicList.get(i).setActiveNumber(now);
                }

                //记录有几个一样的，等下才好求均值
                int count = 1;

                //再进入2层循环，往下找名字和学校名称相同的
                for (int j = i + 1; j < topicList.size(); j++) {

                    if (topicList.get(i).getName().equals(topicList.get(j).getName()) && topicList.get(i).getSchoolName().equals(topicList.get(j).getSchoolName())) {
                        //先标记j
                        topicList.get(j).setId(1);
                        count++;

                        //计算对主题的有效参会总时长(算j的，然后累加到i里）
                        sumLength = calculateTopicEffectTime(topicList.get(j));
                        int now = topicList.get(i).getSumLength() + sumLength;
                        topicList.get(i).setSumLength(now);

                        //计算对主题的积极性评分只和(等下j后求均值)(算j的，然后加到i里）
                        activeScore = calculateTopicActiveScore(topicList.get(j));
                        now = topicList.get(i).getActiveScore() + activeScore;
                        topicList.get(i).setActiveScore(now);

                        //计算对主题的积极参与次数(算j的，然后加到i里）
                        if (activeScore > 0) {
                            now = topicList.get(i).getActiveNumber() + 1;
                            topicList.get(i).setActiveNumber(now);
                        }


                    }
                }
                //对主题的积极性评分用count求均值(从i里拿出来，取均值)
                activeScore = topicList.get(i).getActiveScore() / count;


                //最值限制
                int sumTime = 0;
                for (int j = 0; j < hotSpotLength.size(); j++) {
                    sumTime += (Integer)hotSpotLength.get(j);
                }

                if (topicList.get(i).getSumLength() >= sumTime) {
                    topicList.get(i).setSumLength(sumTime);
                }

                if (activeScore >= 100) {
                    activeScore = 100;
                }


                //如果有学生id(不为null),额外做3件事
                if (topicList.get(i).getStudentId() != null) {

                    //1.用学生id和所有研究热点id，到签到表里找签到评分，一次只能找一个分数,循环找几次，全部找出后再取平均分即可
                    int sum = 0;
                    int averageSignInScore = 0;
                    for (int j = 0; j < cleanInfo.getHotSpotId().length; j++) {
                        Integer score = signInDao.selectSignInScore(cleanInfo.getHotSpotId()[j], topicList.get(i).getStudentId());
                        if (score != null) {
                            sum += score;
                        }
                    }
                    //这就是对主题的签到评分
                    averageSignInScore = sum / cleanInfo.getHotSpotId().length;


                    //2.计算对主题的学习投入度
                    int commitment = calculateTopicCommitment(topicList.get(i).getSumLength(), averageSignInScore, activeScore);

                    //3.将6条数据插入主题参与表

                    TopicAttendance topicAttendance = new TopicAttendance(cleanInfo.getTopicId(), topicList.get(i).getStudentId(), topicList.get(i).getSumLength(), activeScore, commitment, averageSignInScore);

                    //每次插入数据前用主题id和学生id找一下有没有重复的,防止重复插入
                    TopicAttendance topicAttendanceSelect = topicDao.selectByTopicIdAndStudentId(topicAttendance.getTopicId(), topicAttendance.getStudentId());

                    //如果对象为null,说明数据库里没有,这才能insert
                    if (topicAttendanceSelect == null) {
                        topicDao.addTopicAttendance(topicAttendance);
                    }

                }


                //判断是否有效参与主题
                boolean judge = judgeTopicEffective(topicList.get(i).getSumLength());
                if (judge) {
                    participantNum++;
                }

                //判断是否积极参与主题(积极参与次数>=2)
                if (topicList.get(i).getActiveNumber() >= 2) {
                    countActive++;
                }
            }
        }


        //跳出循环后，计算对主题的积极参与率（参与主题的积极人数/参与主题的有效人数）
        double activeRate = (double) (countActive) / (double) (participantNum);
        BigDecimal bigDecimal = new BigDecimal(activeRate).setScale(2, RoundingMode.HALF_UP);
        activeRate = bigDecimal.doubleValue();
        //update主题表，补充这两个数据
        topicDao.addTopicById(cleanInfo.getTopicId(), participantNum, activeRate);


    }

    //计算这条数据对主题的有效参会时长
    private int calculateTopicEffectTime(TopicHelper topicHelper) {
        int sumTime = 0;


        //这条数据要和每个研究热点对照
        for (int i = 0; i < hotSpotStartTime.size(); i++) {

            Conference conference = new Conference((Date) hotSpotStartTime.get(i), (Date) hotSpotEndTime.get(i), (Integer) hotSpotLength.get(i));
            Conference data = new Conference(topicHelper.getEnterTime(), topicHelper.getQuitTime(), topicHelper.getOnceLength());

            data = calculateRestrictedOnceLength(conference, data);
            //如果有返回值,累加
            if (data != null) {
                sumTime += data.getLength();
            }
        }

        return sumTime;
    }

    //计算这条数据对主题的积极性评分
    private int calculateTopicActiveScore(TopicHelper topicHelper) {
        int score = 0;

        //这条数据要和每个研究热点对照
        for (int i = 0; i < hotSpotStartTime.size(); i++) {
            Conference data = new Conference(topicHelper.getEnterTime(), topicHelper.getQuitTime(), topicHelper.getOnceLength());
            score += calculateActiveScore((Date) hotSpotStartTime.get(i), data);
        }

        return score;
    }

    //判断主题的累计有效参会时长是否达到最低标准(所有热点时间和*30%)
    private boolean judgeTopicEffective(Integer sumEffectiveLength) {

        //计算所有研究热点时间和
        int sumTime = 0;
        for (int i = 0; i < hotSpotLength.size(); i++) {
            sumTime += (int) hotSpotLength.get(i);
        }

        //比较
        if (sumEffectiveLength * 100 >= sumTime * 30) {
            return true;
        }
        return false;
    }

}



