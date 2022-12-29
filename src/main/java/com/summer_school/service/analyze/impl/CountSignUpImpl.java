package com.summer_school.service.analyze.impl;

import com.summer_school.dao.StudentDao;
import com.summer_school.pojo.dto.CountSignUp;
import com.summer_school.pojo.dto.GradeDto;
import com.summer_school.pojo.dto.ProfessionDto;
import com.summer_school.service.analyze.CountSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CountSignUpImpl implements CountSignUpService {
    /**
     * 计算学生表性别比例
     * @return 男生比例+女生比例
     */
    @Autowired
    StudentDao studentDao;

    @Override
    public CountSignUp gender() {
        String[] genderName = new String[]{"男", "女"};
        Integer[] genderNumber = new Integer[2];
        double[] genderProportion = new double[2];

        genderNumber[0] = studentDao.countBoy();
        genderNumber[1] = studentDao.countGirl();
        
        int sum = genderNumber[0] + genderNumber[1];

        double temp0 = (double) genderNumber[0] / (double) sum;
        double temp1 = (double) genderNumber[1] / (double) sum;
        
        BigDecimal bigDecimal = new BigDecimal(temp0).setScale(2, RoundingMode.HALF_UP);
        genderProportion[0] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp1).setScale(2, RoundingMode.HALF_UP);
        genderProportion[1] = bigDecimal.doubleValue();

        CountSignUp countSignUp = new CountSignUp(genderName, genderNumber, genderProportion);
        
        return countSignUp;
    }

    /**
     * 学生类别
     */
    @Override
    public CountSignUp studentType() {

        String[] typeName = new String[]{"本科生", "研究生", "博士生"};
        Integer[] typeNumber = new Integer[3];
        double[] typeProportion = new double[3];

        typeNumber[0] = studentDao.countStudentType("本科生");
        typeNumber[1] = studentDao.countStudentType("研究生");
        typeNumber[2] = studentDao.countStudentType("博士生");


        int sum = typeNumber[0] + typeNumber[1] + typeNumber[2];

        double temp0 = (double) typeNumber[0] / (double) sum;
        double temp1 = (double) typeNumber[1] / (double) sum;
        double temp2 = (double) typeNumber[2] / (double) sum;


        BigDecimal bigDecimal = new BigDecimal(temp0).setScale(2, RoundingMode.HALF_UP);
        typeProportion[0] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp1).setScale(2, RoundingMode.HALF_UP);
        typeProportion[1] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp2).setScale(2, RoundingMode.HALF_UP);
        typeProportion[2] = bigDecimal.doubleValue();

        CountSignUp countSignUp = new CountSignUp(typeName, typeNumber, typeProportion);

        return countSignUp;
    }

    /**
     * 就读年级
     */
    @Override
    public CountSignUp grade() {
        
        String[] gradeName = new String[]{"大一", "大二", "大三", "大四", "研一", "研二", "研三", "博一", "博二", "博三",};
        Integer[] gradeNumber = new Integer[10];
        double[] gradeProportion = new double[10];
        
        gradeNumber[0] = studentDao.countGrade("大一");
        gradeNumber[1] = studentDao.countGrade("大二");
        gradeNumber[2] = studentDao.countGrade("大三");
        gradeNumber[3] = studentDao.countGrade("大四");
        gradeNumber[4] = studentDao.countGrade("研一");
        gradeNumber[5] = studentDao.countGrade("研二");
        gradeNumber[6] = studentDao.countGrade("研三");
        gradeNumber[7] = studentDao.countGrade("博一");
        gradeNumber[8] = studentDao.countGrade("博二");
        gradeNumber[9] = studentDao.countGrade("博三");


        int sum = gradeNumber[0] + gradeNumber[1] + gradeNumber[2] + gradeNumber[3] + gradeNumber[4] + gradeNumber[5] + gradeNumber[6] + gradeNumber[7] + gradeNumber[8] + gradeNumber[9];

        double temp0 = (double) gradeNumber[0] / (double) sum;
        double temp1 = (double) gradeNumber[1] / (double) sum;
        double temp2 = (double) gradeNumber[2] / (double) sum;
        double temp3 = (double) gradeNumber[3] / (double) sum;
        double temp4 = (double) gradeNumber[4] / (double) sum;
        double temp5 = (double) gradeNumber[5] / (double) sum;
        double temp6 = (double) gradeNumber[6] / (double) sum;
        double temp7 = (double) gradeNumber[7] / (double) sum;
        double temp8 = (double) gradeNumber[8] / (double) sum;
        double temp9 = (double) gradeNumber[9] / (double) sum;


        BigDecimal bigDecimal = new BigDecimal(temp0).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[0] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp1).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[1] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp2).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[2] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp3).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[3] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp4).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[4] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp5).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[5] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp6).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[6] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp7).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[7] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp8).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[8] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp9).setScale(2, RoundingMode.HALF_UP);
        gradeProportion[9] = bigDecimal.doubleValue();

        CountSignUp countSignUp = new CountSignUp(gradeName, gradeNumber, gradeProportion);
        return countSignUp;
    }

    /**
     * 学校层次
     */
    @Override
    public CountSignUp schoolLevel() {


        String[] schoolLevel = new String[]{"985", "211", "一本", "二本"};
        Integer[] schoolLevelNum = new Integer[4];
        double[] schoolLevelPro = new double[4];

        schoolLevelNum[0] = studentDao.countByLevel("985");
        schoolLevelNum[1] = studentDao.countByLevel("211");
        schoolLevelNum[2] = studentDao.countByLevel("一本");
        schoolLevelNum[3] = studentDao.countByLevel("二本");


        int sum = schoolLevelNum[0] + schoolLevelNum[1] + schoolLevelNum[2] + schoolLevelNum[3];

        double temp0 = (double) schoolLevelNum[0] / (double) sum;
        double temp1 = (double) schoolLevelNum[1] / (double) sum;
        double temp2 = (double) schoolLevelNum[2] / (double) sum;
        double temp3 = (double) schoolLevelNum[3] / (double) sum;


        BigDecimal bigDecimal = new BigDecimal(temp0).setScale(2, RoundingMode.HALF_UP);
        schoolLevelPro[0] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp1).setScale(2, RoundingMode.HALF_UP);
        schoolLevelPro[1] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp2).setScale(2, RoundingMode.HALF_UP);
        schoolLevelPro[2] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp3).setScale(2, RoundingMode.HALF_UP);
        schoolLevelPro[3] = bigDecimal.doubleValue();

        CountSignUp countSignUp = new CountSignUp(schoolLevel, schoolLevelNum, schoolLevelPro);


        return countSignUp;

    }

    /**
     * 政治面貌
     */
    @Override
    public CountSignUp politicalStatus() {

        String[] politicalStatus = new String[]{"群众", "共青团员", "预备党员", "中共党员"};
        Integer[] politicalNum = new Integer[4];
        double[] politicalPro = new double[4];

        politicalNum[0] = studentDao.countByPolitic("群众");
        politicalNum[1] = studentDao.countByPolitic("共青团员");
        politicalNum[2] = studentDao.countByPolitic("预备党员");
        politicalNum[3] = studentDao.countByPolitic("中共党员");


        int sum = politicalNum[0] + politicalNum[1] + politicalNum[2] + politicalNum[3];

        double temp0 = (double) politicalNum[0] / (double) sum;
        double temp1 = (double) politicalNum[1] / (double) sum;
        double temp2 = (double) politicalNum[2] / (double) sum;
        double temp3 = (double) politicalNum[3] / (double) sum;


        BigDecimal bigDecimal = new BigDecimal(temp0).setScale(2, RoundingMode.HALF_UP);
        politicalPro[0] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp1).setScale(2, RoundingMode.HALF_UP);
        politicalPro[1] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp2).setScale(2, RoundingMode.HALF_UP);
        politicalPro[2] = bigDecimal.doubleValue();

        bigDecimal = new BigDecimal(temp3).setScale(2, RoundingMode.HALF_UP);
        politicalPro[3] = bigDecimal.doubleValue();

        CountSignUp countSignUp = new CountSignUp(politicalStatus, politicalNum, politicalPro);


        return countSignUp;
    }

    /**
     * 专业名称
     */
    @Override
    public CountSignUp profession() {
        List<ProfessionDto> list = studentDao.countAllProfession();

        String[] profession = new String[list.size()];
        Integer[] professionNum = new Integer[list.size()];
        double[] professionPro = new double[list.size()];

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            profession[i] = list.get(i).getProfession();
            professionNum[i] = list.get(i).getNum();
            sum += professionNum[i];
        }

        for (int i = 0; i < list.size(); i++) {
            double proportion = (double) professionNum[i] / (double) sum;
            BigDecimal bigDecimal = new BigDecimal(proportion).setScale(2, RoundingMode.HALF_UP);
            professionPro[i] = bigDecimal.doubleValue();
        }

        CountSignUp countSignUp = new CountSignUp(profession, professionNum, professionPro);
        return countSignUp;
    }


}
