package com.summer_school.service.data_cleaning.impl;

import com.csvreader.CsvReader;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipationDetailFormImpl implements FormCleaningService {
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String sumUser;
    private List<String> userName = new ArrayList<>();
    private List<String> enterTime = new ArrayList<>();
    private List<String> quitTime = new ArrayList<>();
    private List<String> onceLength = new ArrayList<>();


    /**
     * 将表格里每列的数据读到一个list里
     *
     * @return 这些list的集合
     */
    @Override
    public void readToList() throws Exception {

        File excel = new File("src/main/java/com/summer_school/file/ParticipationDetail_17.xlsx");
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
                    }
                }
            }
        }

        System.out.println(userName.size());
    }

        /**
         * 调用多个相关清洗算法进行清洗
         *
         * @return
         */
        @Override
        public void clean () {

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
        public boolean save () {
            return false;
        }
    }
