package com.summer_school.service.data_cleaning.impl;

import com.csvreader.CsvReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.summer_school.pojo.SignUpForm;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SignUpFormImpl implements FormCleaningService {

    private SignUpForm signUpForm = new SignUpForm();
    private List<String> studentName = new ArrayList<>();
    private List<String> gender = new ArrayList<>();
    private List<String> schoolName = new ArrayList<>();
    private List<String> studentType = new ArrayList<>();
    private List<String> grade = new ArrayList<>();
    private List<String> profession = new ArrayList<>();
    private List<String> politicalStatus = new ArrayList<>();
    private List<String> schoolLevel = new ArrayList<>();


    /**
     * 将表格里每列的数据读到一个list里
     *
     * @return 这些list的集合
     */
    @Override
    public void readToList() throws Exception {

        String csvFilePath = "src/main/java/com/summer_school/file/SignUp.csv";
        try {
            //容器：对象少的时候，直接把对象列出来；当对象很多的时候，要用一个容器装起来打包
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 这个不用背，只要看得懂会用就行。创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK"));
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
    public boolean save() {
        return false;
    }

    /**
     * 清洗夹杂的标点符号
     */
    private void removePunctuation(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String washed = list.get(i).replaceAll("\\p{Punct}", "");
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



}
