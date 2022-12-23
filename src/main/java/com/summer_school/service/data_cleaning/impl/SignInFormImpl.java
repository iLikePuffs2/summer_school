package com.summer_school.service.data_cleaning.impl;

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
import java.util.List;

@Service
public class SignInFormImpl implements FormCleaningService {
    private List<String> startSignInTime = new ArrayList<>();
    private List<List<String>> name = new ArrayList<>();
    private List<List<String>> signInTime = new ArrayList<>();


    /**
     * 将表格里每列的数据读到一个list里
     *
     * @return 这些list的集合
     */
    @Override
    public void readToList() throws Exception {

        File excel = new File("src/main/java/com/summer_school/file/SignIn.xlsx");
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
            System.out.println(startSignInTime.get(0));


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
            name.add(nameTemp);
            signInTime.add(signInTimeTemp);
        }

        System.out.println(1);

    }


    /**
     * 调用多个相关清洗算法进行清洗
     *
     * @return
     */
    @Override
    public void clean() {

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
}
