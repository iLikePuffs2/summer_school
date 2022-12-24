package com.summer_school.service.data_cleaning.impl;

import com.summer_school.pojo.dto.CleanSignUp;
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

import static com.summer_school.service.data_cleaning.impl.ParticipationDetailFormImpl.extractNameAndSchool;
import static com.summer_school.service.data_cleaning.impl.ParticipationDetailFormImpl.isAllChinese;

@Service
public class SignInFormImpl implements FormCleaningService {
    private List<String> startSignInTime = new ArrayList<>();
    private List<List<String>> userName = new ArrayList<>();
    private List<List<String>> signInTime = new ArrayList<>();
    private List<String> schoolName = new ArrayList<>();
    private List<List<String>> schoolNameList = new ArrayList<>();

    /**
     * 将表格里每列的数据读到一个list里
     *
     * @return 这些list的集合
     */
    @Override
    public void readToList(CleanSignUp cleanSignUp) throws Exception {


        String fileURL = cleanSignUp.getFileURL();
        File excel = new File(fileURL);
        Workbook wb;

        wb = new XSSFWorkbook(new FileInputStream(excel));

        //有几个签到表就循环几次
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {

            Sheet sheet = wb.getSheetAt(i);

            List<String> nameTemp = new ArrayList<>();
            List<String> signInTimeTemp = new ArrayList<>();

            //读取签到开始时间(从索引7开始截取,就没有中文了)
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            startSignInTime.add(cell.toString().substring(7));


            int lastRowIndex = sheet.getLastRowNum();

            //遍历行
            for (int rIndex = 4; rIndex <= lastRowIndex; rIndex++) {
                row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();

                    //遍历列
                    for (int cIndex = firstCellIndex; cIndex < 7; cIndex++) {
                        switch (cIndex) {
                            case 0:
                                nameTemp.add(row.getCell(cIndex).toString());
                                break;
                            case 2:
                                signInTimeTemp.add(row.getCell(cIndex).toString());
                                break;
                        }
                    }
                }
            }

            //把两个list加入到双重list里
            userName.add(nameTemp);
            signInTime.add(signInTimeTemp);
        }

    }


    /**
     * 调用多个相关清洗算法进行清洗
     *
     * @return
     */
    @Override
    public void clean() {

        //清洗签到表里的名字+学校名称
        cleanUserNameAndSchool();
    }

    /**
     * 调用相关分析算法进行分析
     */
    @Override
    public void analyze() {

    }

    /**
     * 将清洗后的数据存入相应的位置
     *
     * @return
     */
    @Override
    public boolean save(CleanSignUp cleanSignUp) {
        return false;
    }


    /**
     * 清洗签到表的名字和学校名称
     * 因为没有括号，所以和参会详情表略有不同
     */
    void cleanUserNameAndSchool() {
        List<String> stringList;
        List<String> testList = new ArrayList<>();
        String eachUserName = "";
        String eachSchoolName = "";

        //有几个签到表，最外层遍历几次
        for (int k = 0; k < userName.size(); k++) {

            //每个表有多少数据，第二层就遍历几次
            for (int i = 0; i < userName.get(k).size(); i++) {
                //清空testList
                testList.clear();

                //拿到原始用户名
                String s = userName.get(k).get(i);

                //先只去掉- +
                stringList = Arrays.asList(s.split("[- _+]"));

                //把不为空的字符串放到一个新的list里
                for (int j = 0; j < stringList.size(); j++) {
                    if (!("".equals(stringList.get(j)))) {
                        testList.add(stringList.get(j));
                    }
                }


                //被划分为3个及以上元素的数据
                if (testList.size() >= 3) {
                    boolean schoolName = false;
                    String tempSchoolName = null;
                    boolean profession = false;

                    for (int j = 0; j < testList.size(); j++) {
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
                    if (testList.get(0).contains("大") || testList.get(0).contains("学院")) {
                        eachSchoolName = testList.get(0);
                        eachUserName = testList.get(1);
                    } else if (testList.get(1).contains("大") || testList.get(0).contains("学院")) {
                        eachSchoolName = testList.get(1);
                        eachUserName = testList.get(0);
                    }

                    //单元素处理
                } else if (testList.size() == 1) {

                    //如果这个元素不是纯中文，判断为无效数据
                    if (!isAllChinese(testList.get(0))) {
                        eachUserName = "";
                        eachSchoolName = "";

                        //如果这个元素是纯中文，就对他进行(名字+学校名称提取)
                    } else {
                        String[] elementString1 = extractNameAndSchool(testList.get(0));
                        eachUserName = elementString1[0];
                        eachSchoolName = elementString1[1];
                    }

                }

                //在if-else结束后才进行设置和add，防止多add数据导致全部数据错位
                userName.get(k).set(i, eachUserName);
                schoolName.add(eachSchoolName);
            }

            schoolNameList.add(schoolName);
            schoolName.clear();
        }
    }
}
