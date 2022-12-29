package com.summer_school.service.analyze.impl.analyse;

import com.summer_school.dao.HotSpotDao;
import com.summer_school.dao.SignInDao;
import com.summer_school.dao.StudentDao;
import com.summer_school.dao.TopicDao;
import com.summer_school.mapper.HotSpotMapper;
import com.summer_school.mapper.SignInMapper;
import com.summer_school.mapper.StudentMapper;
import com.summer_school.mapper.TopicMapper;
import com.summer_school.pojo.dto.back.AnalyseBackDto;
import com.summer_school.pojo.dto.into.FreeStudentIntoDto;
import com.summer_school.pojo.dto.into.SolidStudentIntoDto;
import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.Student;
import com.summer_school.pojo.po.Topic;
import com.summer_school.service.analyze.OptionalAnalysisService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArrivalAnalyseImpl implements OptionalAnalysisService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    SignInDao signInDao;

    @Autowired
    HotSpotDao hotSpotDao;

    @Autowired
    TopicDao topicDao;

    //设置学校层次，学生类别，就读年级，所学专业(唯一变动)，性别
    String[] schoolLevelArr = new String[]{"985", "211", "一本", "二本"};
    String[] studentTypeArr = new String[]{"本科生", "研究生", "博士生"};
    String[] gradeArr = new String[]{"大一", "大二", "大三", "大四", "研一", "研二", "研三", "博一", "博二"};
    String[] genderArr = new String[]{"男", "女"};

    /**
     * 指定研究热点,学生群体可变
     *
     * @param freeInfo
     */
    @Override
    public AnalyseBackDto solidHotSpot(FreeStudentIntoDto freeInfo) {
        String[] professionArr = studentDao.selectDistinctProfession();
        AnalyseBackDto back = new AnalyseBackDto();

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

        //实际查询
//        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//        List<Student> users = mapper.selectByDifferent(student);


        //拿到研究热点id
        int hotSpotId = freeInfo.getSolidValue();

        //拿到学生群体固定项的值，封装在一个学生对象里，等下动态sql
        Student student = new Student();
        for (int i = 0; i < freeInfo.getStudentSolidNum().length; i++) {
            switch (freeInfo.getStudentSolidNum()[i]) {
                case 1:
                    student.setSchoolLevel(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 2:
                    student.setStudentType(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 3:
                    student.setGrade(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 4:
                    student.setProfession(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 5:
                    student.setGender(freeInfo.getStudentSolidValue()[i]);
                    break;

                default:
            }
        }

        //动态sql找出全部群体,等下再细分操作
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> users = mapper.selectByDifferent(student);


        Integer[] integerVertical;
        //根据学生群体可变项是哪个,执行对应操作（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
        switch (freeInfo.getStudentVariableNum()) {
            //如果学校层次可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 1:
                integerVertical = new Integer[schoolLevelArr.length];
                for (int i = 0; i < schoolLevelArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getSchoolLevel().equals(schoolLevelArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个学校层次的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(hotSpotId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }
                }
                back.setHorizontalValue(schoolLevelArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果学生类别可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 2:
                integerVertical = new Integer[studentTypeArr.length];
                for (int i = 0; i < studentTypeArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getStudentType().equals(studentTypeArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个学生类别的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(hotSpotId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }
                }
                back.setHorizontalValue(studentTypeArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果就读年级可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 3:
                integerVertical = new Integer[gradeArr.length];
                for (int i = 0; i < gradeArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getGrade().equals(gradeArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个就读年级的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(hotSpotId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }

                }
                back.setHorizontalValue(gradeArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果所学专业可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 4:
                integerVertical = new Integer[professionArr.length];
                for (int i = 0; i < professionArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getProfession().equals(professionArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个所学专业的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(hotSpotId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }

                }
                back.setHorizontalValue(professionArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果性别可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 5:
                integerVertical = new Integer[genderArr.length];
                for (int i = 0; i < genderArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getGender().equals(genderArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个性别的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(hotSpotId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }

                }
                back.setHorizontalValue(genderArr);
                back.setVerticalValue(integerVertical);
                break;

            default:

        }

        return back;
    }

    /**
     * 指定主题,学生群体可变
     *
     * @param freeInfo
     */
    @Override
    public AnalyseBackDto solidTopic(FreeStudentIntoDto freeInfo) {
        String[] professionArr = studentDao.selectDistinctProfession();
        AnalyseBackDto back = new AnalyseBackDto();

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

        //实际查询
//        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//        List<Student> users = mapper.selectByDifferent(student);


        //拿到主题热点id
        int topicId = freeInfo.getSolidValue();

        //拿到学生群体固定项的值，封装在一个学生对象里，等下动态sql
        Student student = new Student();
        for (int i = 0; i < freeInfo.getStudentSolidNum().length; i++) {
            switch (freeInfo.getStudentSolidNum()[i]) {
                case 1:
                    student.setSchoolLevel(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 2:
                    student.setStudentType(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 3:
                    student.setGrade(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 4:
                    student.setProfession(freeInfo.getStudentSolidValue()[i]);
                    break;

                case 5:
                    student.setGender(freeInfo.getStudentSolidValue()[i]);
                    break;

                default:
            }
        }

        //动态sql找出全部群体,等下再细分操作
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> users = mapper.selectByDifferent(student);


        Integer[] integerVertical;
        //根据学生群体可变项是哪个,执行对应操作（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
        switch (freeInfo.getStudentVariableNum()) {
            //如果学校层次可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 1:
                integerVertical = new Integer[schoolLevelArr.length];
                for (int i = 0; i < schoolLevelArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getSchoolLevel().equals(schoolLevelArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个学校层次的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    TopicMapper mapper1 = sqlSession.getMapper(TopicMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(topicId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }
                }
                back.setHorizontalValue(schoolLevelArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果学生类别可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 2:
                integerVertical = new Integer[studentTypeArr.length];
                for (int i = 0; i < studentTypeArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getStudentType().equals(studentTypeArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个学生类别的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(topicId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }
                }
                back.setHorizontalValue(studentTypeArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果就读年级可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 3:
                integerVertical = new Integer[gradeArr.length];
                for (int i = 0; i < gradeArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getGrade().equals(gradeArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个就读年级的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(topicId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }

                }
                back.setHorizontalValue(gradeArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果所学专业可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 4:
                integerVertical = new Integer[professionArr.length];
                for (int i = 0; i < professionArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getProfession().equals(professionArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个所学专业的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(topicId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }

                }
                back.setHorizontalValue(professionArr);
                back.setVerticalValue(integerVertical);
                break;

            //如果性别可变,就将刚刚得到的符合固定条件的users按学校层次划分
            case 5:
                integerVertical = new Integer[genderArr.length];
                for (int i = 0; i < genderArr.length; i++) {
                    List<Integer> idList = new ArrayList<>();
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getGender().equals(genderArr[i])) {
                            idList.add(users.get(j).getId());
                        }
                    }

                    //此时得到了这个符合固定条件的第一个性别的学生群体的全都id
                    //就可以在签到表里查出这个群体的到课率的均值了,然后存入纵坐标的数组即可
                    HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
                    Integer sum = mapper1.countSumEffectiveNum(topicId, idList);
                    double arrivalRate = 0;
                    if (sum == null) {
                        integerVertical[i] = 0;
                    } else {
                        if (idList.size() > 0){
                            arrivalRate = (double) sum / (double) idList.size();
                            BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                            arrivalRate = bigDecimal.doubleValue();
                            arrivalRate = arrivalRate * 100;
                            integerVertical[i] = (int) arrivalRate;
                        } else {
                            integerVertical[i] = 0;
                        }
                    }

                }
                back.setHorizontalValue(genderArr);
                back.setVerticalValue(integerVertical);
                break;

            default:

        }

        return back;
    }

    /**
     * 指定天数,学生群体可变
     *
     * @param freeInfo
     */
    @Override
    public AnalyseBackDto solidDay(FreeStudentIntoDto freeInfo) {
        return null;
    }

    /**
     * 指定学生群体,研究热点可变
     *
     * @param solidInfo
     */
    @Override
    public AnalyseBackDto variableHotSpot(SolidStudentIntoDto solidInfo) {
        String[] professionArr = studentDao.selectDistinctProfession();
        AnalyseBackDto back = new AnalyseBackDto();

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

        //实际查询
//        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//        List<Student> users = mapper.selectByDifferent(student);


        //拿到学生群体固定项的值，封装在一个学生对象里，等下动态sql
        Student student = new Student();
        for (int i = 0; i < solidInfo.getStudentSolidNum().length; i++) {
            switch (solidInfo.getStudentSolidNum()[i]) {
                case 1:
                    student.setSchoolLevel(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 2:
                    student.setStudentType(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 3:
                    student.setGrade(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 4:
                    student.setProfession(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 5:
                    student.setGender(solidInfo.getStudentSolidValue()[i]);
                    break;

                default:
            }
        }

        //动态sql找出这个群体
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Integer> idList = mapper.selectBySolidDifferent(student);

        //找出全部研究热点名字和对应id
        List<HotSpot> hotSpotList = hotSpotDao.selectAll();


        Integer[] integerVertical = new Integer[hotSpotList.size()];
        String[] integerHorizontal = new String[hotSpotList.size()];

        //此时得到了这个符合固定条件的学生群体的全都id
        //就可以在签到表里查出这个群体的签到评分的均值了,然后存入纵坐标的数组即可
        for (int i = 0; i < hotSpotList.size(); i++) {
            HotSpotMapper mapper1 = sqlSession.getMapper(HotSpotMapper.class);
            Integer value = mapper1.countSumEffectiveNum(hotSpotList.get(i).getResearchHotSpotId(), idList);
            double arrivalRate = 0;
            if (value == null) {
                integerVertical[i] = 0;
            } else {
                if (idList.size() > 0){
                    arrivalRate = (double) value / (double) idList.size();
                    BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                    arrivalRate = bigDecimal.doubleValue();
                    arrivalRate = arrivalRate * 100;
                    integerVertical[i] = (int) arrivalRate;
                } else {
                    integerVertical[i] = 0;
                }
            }

            integerHorizontal[i] = hotSpotList.get(i).getName();
        }

        back.setHorizontalValue(integerHorizontal);
        back.setVerticalValue(integerVertical);


        return back;
    }

    /**
     * 指定学生群体,主题可变
     *
     * @param solidInfo
     */
    @Override
    public AnalyseBackDto variableTopic(SolidStudentIntoDto solidInfo) {
        String[] professionArr = studentDao.selectDistinctProfession();
        AnalyseBackDto back = new AnalyseBackDto();

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

        //实际查询
//        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//        List<Student> users = mapper.selectByDifferent(student);


        //拿到学生群体固定项的值，封装在一个学生对象里，等下动态sql
        Student student = new Student();
        for (int i = 0; i < solidInfo.getStudentSolidNum().length; i++) {
            switch (solidInfo.getStudentSolidNum()[i]) {
                case 1:
                    student.setSchoolLevel(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 2:
                    student.setStudentType(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 3:
                    student.setGrade(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 4:
                    student.setProfession(solidInfo.getStudentSolidValue()[i]);
                    break;

                case 5:
                    student.setGender(solidInfo.getStudentSolidValue()[i]);
                    break;

                default:
            }
        }

        //动态sql找出这个群体
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Integer> idList = mapper.selectBySolidDifferent(student);

        //找出全部主题名字和对应id
        List<Topic> topicList = topicDao.selectAll();


        Integer[] integerVertical = new Integer[topicList.size()];
        String[] integerHorizontal = new String[topicList.size()];

        //此时得到了这个符合固定条件的学生群体的全都id
        //就可以在签到表里查出这个群体的签到评分的均值了,然后存入纵坐标的数组即可
        for (int i = 0; i < topicList.size(); i++) {
            TopicMapper mapper1 = sqlSession.getMapper(TopicMapper.class);
            Integer value = mapper1.countSumEffectiveNum(topicList.get(i).getId(), idList);
            double arrivalRate = 0;
            if (value == null) {
                integerVertical[i] = 0;
            } else {
                if (idList.size() > 0){
                    arrivalRate = (double) value / (double) idList.size();
                    BigDecimal bigDecimal = new BigDecimal(arrivalRate).setScale(2, RoundingMode.HALF_UP);
                    arrivalRate = bigDecimal.doubleValue();
                    arrivalRate = arrivalRate * 100;
                    integerVertical[i] = (int) arrivalRate;
                } else {
                    integerVertical[i] = 0;
                }
            }

            integerHorizontal[i] = topicList.get(i).getName();
        }

        back.setHorizontalValue(integerHorizontal);
        back.setVerticalValue(integerVertical);


        return back;
    }

    /**
     * 指定学生群体,天数可变
     *
     * @param solidInfo
     */
    @Override
    public AnalyseBackDto variableDay(SolidStudentIntoDto solidInfo) {
        return null;
    }
}
