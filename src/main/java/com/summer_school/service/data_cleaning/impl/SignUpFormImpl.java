package com.summer_school.service.data_cleaning.impl;

import com.csvreader.CsvReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.summer_school.dao.StudentDao;
import com.summer_school.dao.SummerSchoolDao;
import com.summer_school.pojo.dto.CleanSignUp;
import com.summer_school.pojo.po.SchoolLevelContrast;
import com.summer_school.pojo.po.Student;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SignUpFormImpl implements FormCleaningService {

    private List<String> studentName = new ArrayList<>();
    private List<String> gender = new ArrayList<>();
    private List<String> schoolName = new ArrayList<>();
    private List<String> studentType = new ArrayList<>();
    private List<String> grade = new ArrayList<>();
    private List<String> profession = new ArrayList<>();
    private List<String> politicalStatus = new ArrayList<>();
    private List<String> schoolLevel = new ArrayList<>();
    private List<Integer> similarRatesList = new ArrayList<>();
    private List<SchoolLevelContrast> schoolLevelContrast = new ArrayList<>();

    @Autowired
    SummerSchoolDao summerSchoolDao;

    @Autowired
    StudentDao studentDao;

    /**
     * 将表格里每列的数据读到一个list里
     *
     * @return 这些list的集合
     */
    @Override
    public void readToList(CleanSignUp cleanSignUp) throws Exception {

        String fileURL = cleanSignUp.getFileURL();
//        fileURL = "\""+fileURL+"\"";
        try {
            //容器：对象少的时候，直接把对象列出来；当对象很多的时候，要用一个容器装起来打包
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 这个不用背，只要看得懂会用就行。创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(fileURL, ',', Charset.forName("GBK"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            //boolean变量：真假true或者false

            //将一行的字符串按照“，”逗号分成多列，存放到String[]数组中
            //再将这个string[]放到list容器中存起来
            for (int i = 0; reader.readRecord(); i++) {
                csvFileList.add(reader.getValues());
                studentName.add(csvFileList.get(i)[3]);
                gender.add(csvFileList.get(i)[4]);
                politicalStatus.add(csvFileList.get(i)[5]);
                studentType.add(csvFileList.get(i)[6]);
                schoolName.add(csvFileList.get(i)[7]);
                profession.add(csvFileList.get(i)[8]);
                grade.add(csvFileList.get(i)[9]);
            }
            //数据取完了，关闭文件
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 调用多个相关清洗算法进行清洗
     *
     * @return
     */
    @Override
    public void clean() {
        //去除所有数据中夹杂的标点符号
        removeAllPunctuation();

        //清洗政治面貌
        cleanPoliticalStatus();

        //清洗学生类别
        cleanStudentType();

        //清洗就读年级
        cleanGrade();

        //清洗专业名称
        cleanProfession();
    }

    /**
     * 调用相关分析算法进行分析
     */
    @Override
    public void analyze() {
        //清洗学校名字+获得对应的学校层次
        cleanSchoolName();
    }

    /**
     * 将清洗后的数据存入相应的位置
     *
     * @return
     */

    @Override
    public boolean save(CleanSignUp cleanSignUp) {

        int insertNum = 0;
        Student student = new Student();
        student.setSummerSchoolId(cleanSignUp.getSummerSchoolId());
        student.setIdentity("学生");

        for (int i = 0; i < schoolName.size(); i++) {
            student.setSchoolName(schoolName.get(i));
            student.setGender(gender.get(i));
            student.setStudentName(studentName.get(i));
            student.setStudentType(studentType.get(i));
            student.setGrade(grade.get(i));
            student.setProfession(profession.get(i));
            student.setPoliticalStatus(politicalStatus.get(i));
            student.setSchoolLevel(schoolLevel.get(i));
            studentDao.insertSignUp(student);
            insertNum++;
        }


        return insertNum == schoolName.size() ? true : false;
    }

    /**
     * 清洗夹杂的标点符号
     */
    private void removePunctuation(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String washed = list.get(i).replaceAll("\\p{Punct}", "");
            washed = washed.replaceAll("\\R", "");
            list.set(i, washed);
        }
    }

    /**
     * 姓名和性别的清洗到此为止
     */
    private void removeAllPunctuation() {
        removePunctuation(studentName);
        removePunctuation(gender);
        removePunctuation(schoolName);
        removePunctuation(studentType);
        removePunctuation(grade);
        removePunctuation(profession);
        removePunctuation(politicalStatus);
    }

    /**
     * 清洗政治面貌：
     * 群众
     * 共青团员
     * 预备党员
     * 中共党员
     */
    private void cleanPoliticalStatus() {
        for (int i = 0; i < politicalStatus.size(); i++) {
            if (politicalStatus.get(i).contains("群")){
                politicalStatus.set(i,"群众");
            } else if (politicalStatus.get(i).contains("团") || politicalStatus.get(i).contains("积极")){
                politicalStatus.set(i,"共青团员");
            } else if (politicalStatus.get(i).contains("预备")){
                politicalStatus.set(i,"预备党员");
            } else if ("党员".equals(politicalStatus.get(i)) || "中共党员".equals(politicalStatus.get(i))){
                politicalStatus.set(i,"中共党员");
            } else {
                politicalStatus.set(i,"共青团员");
            }
        }
    }

    /**
     * 清洗学生类别：
     * 本科生
     * 研究生
     * 博士生
     */
    private void cleanStudentType() {
        for (int i = 0; i < studentType.size(); i++) {
            if (studentType.get(i).contains("本")){
                studentType.set(i,"本科生");
            } else if (studentType.get(i).contains("硕") || studentType.get(i).contains("研")){
                studentType.set(i,"研究生");
            } else if (studentType.get(i).contains("博")){
                studentType.set(i,"博士生");
            } else {
                studentType.set(i,"研究生");
            }
        }
    }

    /**
     * 清洗就读年级：
     * 大一、大二、大三、大四
     * 研一、研二、研三
     * 博一、博二、博三
     */
    private void cleanGrade() {
        for (int i = 0; i < grade.size(); i++) {
            if (studentType.get(i).equals("本科生")){
                if (grade.get(i).contains("一") || grade.get(i).contains("22") || grade.get(i).equals("1")){
                    grade.set(i,"大一");
                } else if (grade.get(i).contains("二") || grade.get(i).contains("21") || grade.get(i).equals("2")){
                    grade.set(i,"大二");
                } else if (grade.get(i).contains("三") || grade.get(i).contains("2020") || grade.get(i).equals("3") || grade.get(i).equals("20")){
                    grade.set(i,"大三");
                } else if (grade.get(i).contains("四") || grade.get(i).contains("19") || grade.get(i).equals("4")){
                    grade.set(i,"大四");
                } else {
                    grade.set(i,"大二");
                }

            } else if (studentType.get(i).equals("研究生")){
                if (grade.get(i).contains("一") || grade.get(i).contains("新") || grade.get(i).contains("22")){
                    grade.set(i,"研一");
                } else if (grade.get(i).contains("二")){
                    grade.set(i,"研二");
                } else if (grade.get(i).contains("三")){
                    grade.set(i,"研三");
                } else {
                    grade.set(i,"研一");
                }
            } else {
                if (grade.get(i).contains("一") || grade.get(i).equals("博1")){
                    grade.set(i,"博一");
                } else if (grade.get(i).contains("二") || grade.get(i).equals("博2")){
                    grade.set(i,"博二");
                } else if (grade.get(i).contains("三")){
                    grade.set(i,"博三");
                } else {
                    grade.set(i,"博一");
                }
            }
        }
    }


    /**
     * 清洗学校名字+获得对应的学校层次
     */
    void cleanSchoolName(){
        //先从数据库查出学校层次对照表里全部的学校名称(组成一个list)
        schoolLevelContrast = summerSchoolDao.selectContrast();

        //相似率最大值
        int similarRatesMax = 0;

        //相似率最大值对应的学校名字索引
        int indexMax = 0;

        //双重循环是为了便利两个list中的每个字符串
        for (int i = 0; i < schoolName.size(); i++) {

            //用1个清洗前的学校名称和学校层次表里的学校名称逐个对比，计算相似率
            for (int j = 0; j < schoolLevelContrast.size(); j++) {
                similarRatesList.add(similarRates(schoolName.get(i),schoolLevelContrast.get(j).getSchoolName()));
            }

            /**
             * 二重循环结束后,找到此轮相似率集合的最大值的索引（对应了学校层次表里的学校名称和学校层次的索引）
             * 将schoolName这个list里的这个索引为i的学校名字，替换成学校层次表里相似度最高的那个暑期学校名字
             * 在schoolLevel这个list里加入学校层次表里相似度最高的那个暑期学校名字对应的学校层次
             * 清空similarRatesList,为下次循环做准备
             */
            similarRatesMax = Collections.max(similarRatesList);

            indexMax = similarRatesList.indexOf(similarRatesMax);

            schoolName.set(i,schoolLevelContrast.get(indexMax).getSchoolName());

            schoolLevel.add(schoolLevelContrast.get(indexMax).getLevel());

            similarRatesList.clear();
        }
    }

    /**
     * Levenshtein Distance编辑距离算法(求出两个字符串的相似率)
     * @param str1
     * @param str2
     * @return
     */
    public static Integer similarRates(String str1 , String str2){
        //确定二维距离表distance的维度
        int str1Len = str1.length();
        int str2Len = str2.length();
        //如果一个字符串的内容为空就返回另一个字符串的长度
        if (str1Len == 0) {
            return str2Len;
        }
        if (str2Len == 0) {
            return str1Len;
        }
        //定义一张二维距离表distance
        int[][] distance = new int[str1Len + 1][str2Len + 1];

        //给二维数组的第一行第一列赋值
        int maxLen = str1Len > str2Len ? str1Len : str2Len;
        for (int num = 0; num < maxLen + 1; num++){
            if (num<str1Len + 1) {
                distance[num][0] = num;
            }
            if (num<str2Len + 1) {
                distance[0][num] = num;
            }
        }

        /**
         * 补全二维数组除第一行第一列的其他值
         * 行列索引进行对比，相同的话直接取左上方值，不同的话采用最小距离算法
         */
        for (int row = 1; row < str1Len+1; row++){
            char c1 = str1.charAt(row - 1);
            for (int col = 1; col < str2Len+1; col++){
                char c2 = str2.charAt(col - 1);
                if (c1 == c2) {
                    distance[row][col] = distance[row - 1][col - 1];
                } else {
                    //最小距离算法就是，取该元素左上方值、左边值、上边值，找到三个之中的最小值再加1即最终距离
                    distance[row][col] = mostMin(distance[row-1][col], distance[row][col-1], distance[row-1][col-1]) + 1;
                }
            }
        }

        //二维数组中的最后一个元素即是两个字符串间不同字符的个数
        int notSimilarNum = distance[str1Len][str2Len];

        //求出相似率
        double similarRates = (1- (double)notSimilarNum / maxLen)*100;
        return (int)similarRates;
    }

    //取三个数中的最小值
    public static int mostMin(int up, int left, int upLeft){
        int min = up < left ? up : left;
        min = min < upLeft ? min : upLeft;
        return min;
    }

    /**
     * 从专业名称参考表里把专业名称放到一个局部List里
     * 用的也是Levenshtein Distance编辑距离算法
     */
    void cleanProfession(){
        //先从数据库查出专业名称参考表全部的专业名称(局部list)
        List<String> professionFromDB;
        professionFromDB = summerSchoolDao.selectProfession();

        //相似率最大值
        int similarRatesMax = 0;

        //相似率最大值对应的专业名称索引
        int indexMax = 0;

        //双重循环是为了便利两个list中的每个字符串
        for (int i = 0; i < profession.size(); i++) {

            //用1个清洗前的专业名称和专业名称参考表里的专业名称逐个对比，计算相似率
            for (int j = 0; j < professionFromDB.size(); j++) {
                similarRatesList.add(similarRates(profession.get(i),professionFromDB.get(j)));
            }

            /**
             * 二重循环结束后,找到此轮相似率集合的最大值的索引（对应了学校层次表里的学校名称和学校层次的索引）
             * 将schoolName这个list里的这个索引为i的学校名字，替换成学校层次表里相似度最高的那个暑期学校名字
             * 在schoolLevel这个list里加入学校层次表里相似度最高的那个暑期学校名字对应的学校层次
             * 清空similarRatesList,为下次循环做准备
             */
            similarRatesMax = Collections.max(similarRatesList);

            indexMax = similarRatesList.indexOf(similarRatesMax);

            profession.set(i,professionFromDB.get(indexMax));

            similarRatesList.clear();
        }
    }




}
