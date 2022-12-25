package com.summer_school.service.data_cleaning.impl;

import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    private List<String> enterTime = new ArrayList<>();
    private List<String> quitTime = new ArrayList<>();
    private List<String> onceLength = new ArrayList<>();

    private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");


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
     * 总体逻辑：
     * 先将名字这个字符串根据()用.split方法划分——绝大多数情况会得到2个元素
     * 经观察，元素2准确度更高，所以优先考虑对元素2进行.split划分
     * 元素2如果被拆分为三元素/双元素，只看元素2即可
     * 元素2如果被拆分为单元素(此时才用到元素1)，又分多种情况：
     * 对元素1用.split方法划分，元素1如果被拆分为三元素/双元素，只看元素1即可(方法同只看元素2)
     * 元素1如果被拆分为单元素，此时：
     * 如果元素1,2都不是纯中文，判断为无效数据
     * 如果元素1,2一个不是纯中文，另一个是，就对纯中文进行(名字+学校名称提取)
     * 如果元素1,2都是纯中文，就对它们都进行(名字+学校名称提取),如果两边提取后的名字equals，就被认为是有效名字;学校名称equals，就被认为是有效学校名称
     * 如果被划分成1个元素，名字和学校名字都无效
     */
    void cleanUserNameAndSchool() {
        List<String> stringList;
        List<String> testList;
        String eachUserName = "";
        String eachSchoolName = "";

        for (int i = 0; i < userName.size(); i++) {
            //拿到原始用户名
            String s = userName.get(i);

            //先只去掉括号()，将原始用户名划分为list里的2个元素(只有1个元素就被判定无效数据)
            stringList = Arrays.asList(s.split("[()]"));

            //被()划分为2个元素的数据进行正常处理
            if (stringList.size() == 2) {
                String element1 = stringList.get(0);
                String element2 = stringList.get(1);

                //对元素2再进行一次划分,进行处理
                testList = Arrays.asList(element2.split("[- _+]"));

                //元素2的三元素处理
                //逻辑：哪个字段有“大||学院”,哪个字段就代表大学;2<=长度<=3且不包含”院“字，就代表名字
                if (testList.size() == 3) {
                    boolean schoolName = false;
                    String tempSchoolName = null;
                    boolean profession = false;

                    for (int j = 0; j < 3; j++) {
                        if (testList.get(j).contains("大")){
                            schoolName = true;
                            tempSchoolName = testList.get(j);
                        }

                        if (testList.get(j).contains("学院")){
                            profession = true;
                        }

                        if (testList.get(j).contains("大") || testList.get(j).contains("学院")) {
                            eachSchoolName = testList.get(j);
                        }

                        if (testList.get(j).length() >= 2 && testList.get(j).length() <= 3 && !(testList.get(j).contains("院"))) {
                            eachUserName = testList.get(j);
                        }
                    }

                    if (schoolName && profession){
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
                            if (testList.get(j).contains("大")){
                                schoolName = true;
                                tempSchoolName = testList.get(j);
                            }

                            if (testList.get(j).contains("学院")){
                                profession = true;
                            }

                            if (testList.get(j).contains("大") || testList.get(j).contains("学院")) {
                                eachSchoolName = testList.get(j);
                            }

                            if (testList.get(j).length() >= 2 && testList.get(j).length() <= 3 && !(testList.get(j).contains("院"))) {
                                eachUserName = testList.get(j);
                            }
                        }

                        if (schoolName && profession){
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

                        //如果元素1,2都不是纯中文，判断为无效数据
                        if (!isAllChinese(element1) && !isAllChinese(element2)) {
                            eachUserName = "";
                            eachSchoolName = "";

                            /**
                             * 如果元素1,2都是纯中文，就对它们都进行(名字+学校名称提取)
                             * 如果两边提取后的名字equals，就被认为是有效名字
                             * 学校名称equals，就被认为是有效学校名称
                             */
                        } else if (isAllChinese(element1) && isAllChinese(element2)) {
                            String[] elementString1 = extractNameAndSchool(element1);
                            String[] elementString2 = extractNameAndSchool(element2);

                            //两个名字equals
                            if (elementString1[0].equals(elementString2[0])){
                                eachUserName = elementString1[0];
                            }

                            //两个学校名称equals
                            if (elementString1[1].equals(elementString2[1])){
                                eachSchoolName = elementString1[1];
                            }

                        //如果元素1,2一个不是纯中文，另一个是，就对纯中文进行(名字+学校名称提取)
                        } else{
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

                //被()划分为1个元素的数据舍弃
            } else {
                eachUserName = "";
                eachSchoolName = "";
            }

            //在if-else结束后才进行设置和add，防止多add数据导致全部数据错位
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
     * 名字+学校名称提取（判定为纯中文才进行）：
     * 长度<=3，被认为只有名字
     * “大学”及其前面的字为学校名称，后面的为名字
     * “学院”及其前面的字为学校名称，后面的为名字
     * “大”及其前面的字为学校名称，后面的为名字
     *
     * @param rawSingleString 不含任何字符的纯中文
     * @return [0]是名字，[1]是学校名称
     */
    public static String[] extractNameAndSchool(String rawSingleString) {
        String[] nameAndSchool = {"", ""};
        int index = 0;

        //2<=长度<=3，被认为只有名字
        if (rawSingleString.length() >= 2 && rawSingleString.length() <= 3) {
            nameAndSchool[0] = rawSingleString;
        } else if (rawSingleString.length() > 3){
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


}



