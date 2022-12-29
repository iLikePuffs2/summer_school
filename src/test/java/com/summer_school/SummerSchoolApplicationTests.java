package com.summer_school;

import com.summer_school.dao.SignInDao;
import com.summer_school.mapper.SignInMapper;
import com.summer_school.mapper.StudentMapper;
import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.pojo.dto.back.AnalyseBackDto;
import com.summer_school.pojo.dto.into.FreeStudentIntoDto;
import com.summer_school.pojo.dto.into.SolidStudentIntoDto;
import com.summer_school.pojo.po.Student;
import com.summer_school.service.analyze.OptionalAnalysisService;
import com.summer_school.service.analyze.impl.analyse.*;
import com.summer_school.service.data_cleaning.FillInDataService;
import com.summer_school.service.data_cleaning.FormCleaningService;
import com.summer_school.service.data_cleaning.impl.ParticipationFormImpl;
import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//    @Qualifier("participationDetailFormImpl")
//    @Qualifier("signInFormImpl")

@SpringBootTest
@EnableTransactionManagement
class SummerSchoolApplicationTests {

    @Qualifier("participationFormImpl")
//    @Qualifier("signInFormImpl")
//    @Qualifier("signUpFormImpl")
    @Autowired
    FormCleaningService formCleaningService;

    @Autowired
    FillInDataService fillInDataService;

    @Autowired
    SignInDao signInDao;

//        @Qualifier("signInAnalyseImpl")
//    @Qualifier("activeAnalyseImpl")
//    @Qualifier("arrivalAnalyseImpl")
//    @Qualifier("commitmentAnalyseImpl")
    @Qualifier("effectiveLengthAnalyseImpl")
    @Autowired
    OptionalAnalysisService optionalAnalysisService;

    @Test
    void test() {

        //固定项的值(天数/主题id/研究热点id)
//        Integer solidValue = 50;
        Integer solidValue = 14;

        //学生群体固定项编号（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
        Integer[] studentSolidNum={2};
//        Integer[] studentSolidNum={2};

        //学生群体固定项的值
        String[] studentSolidValue={"研究生"};

        //学生群体可变项编号（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
        Integer studentVariableNum=4;

        FreeStudentIntoDto freeStudentIntoDto = new FreeStudentIntoDto(solidValue,studentSolidNum,studentSolidValue,studentVariableNum);
        SolidStudentIntoDto solidStudentIntoDto = new SolidStudentIntoDto(studentSolidNum, studentSolidValue);
//        AnalyseBackDto analyseBackDto = optionalAnalysisService.solidHotSpot(freeStudentIntoDto);
//        AnalyseBackDto analyseBackDto = optionalAnalysisService.solidTopic(freeStudentIntoDto);
//        AnalyseBackDto analyseBackDto = optionalAnalysisService.variableHotSpot(solidStudentIntoDto);
        AnalyseBackDto analyseBackDto = optionalAnalysisService.variableTopic(solidStudentIntoDto);

        if (analyseBackDto != null){
            for (int i = 0; i < analyseBackDto.getHorizontalValue().length; i++) {
                System.out.println(analyseBackDto.getHorizontalValue()[i]);
                System.out.println(analyseBackDto.getVerticalValue()[i]);
            }
        }



//        try {
//            CleanInfo cleanInfo = new CleanInfo(1,"D:\\Idea project\\summer_school\\src\\main\\java\\com\\summer_school\\file\\ParticipationDetail_13.xlsx",1, new Integer[]{1, 2, 3, 4});
//            formCleaningService.execute(cleanInfo);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

//        fillInDataService.save(null);




/*
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Integer> integers = new ArrayList<>();
        integers.add(226);
        integers.add(58);
        integers.add(48);
        integers.add(139);
        SignInMapper mapper = sqlSession.getMapper(SignInMapper.class);
        Integer score = mapper.countGroupAvg(49, integers);

        System.out.println(score);

        //4. 释放资源
        sqlSession.close();*/



    }

}
