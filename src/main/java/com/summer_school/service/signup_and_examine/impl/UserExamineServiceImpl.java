package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.EducationalAdministartorDao;
import com.summer_school.dao.StudentDao;
import com.summer_school.dao.SummerSchoolDao;
import com.summer_school.dao.TeacherDao;
import com.summer_school.pojo.dto.ExamineResultInfo;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.service.signup_and_examine.SignUpService;
import com.summer_school.service.signup_and_examine.UserExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统管理员审核教务管理员
 * 教务管理员审核老师
 * 教务管理员审核并关联学生
 */
@Service
public class UserExamineServiceImpl implements UserExamineService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private SummerSchoolDao summerSchoolDao;

    /**
     * 如果审核者身份是系统管理员，给他展示教务管理员的注册申请信息
     * 如果审核者身份是教务管理员，给他展示老师的注册申请信息/(学生的注册申请信息+报名学生信息）
     * @param examineResultInfo
     * @return
     */
    @Override
    public List<List> showSignUpInfo(ExamineResultInfo examineResultInfo) {
        String operatorIdentity = examineResultInfo.getOperatorIdentity();
        String candidateIdentity = examineResultInfo.getCandidateIdentity();
        List<List> userList = new ArrayList<>();

        if (operatorIdentity.equals("系统管理员")){
            userList.add(educationalAdministartorDao.showSignUpInfo("教务管理员"));
            return userList;
        } else if (operatorIdentity.equals("教务管理员")) {
            if (candidateIdentity.equals("老师")){
                userList.add(teacherDao.showSignUpInfo("老师"));
                return userList;
            } else if (candidateIdentity.equals("学生")){
                userList.add(studentDao.showSignUpInfo());
                userList.add(studentDao.showStudentInfo());
                return userList;
            }
        }
        return null;
    }

    @Override
    public boolean examine(ExamineResultInfo examineResultInfo) {
        String identity = examineResultInfo.getCandidateIdentity();
        String account = examineResultInfo.getAccount();

        if (examineResultInfo.getCandidateIdentity().equals("教务管理员")){
            //教务管理员审核通过
            if (examineResultInfo.isResult()){
                //先根据被审核者的账号找出他在对应的注册申请表里的那条数据
                SignUpInfo signUpInfo = educationalAdministartorDao.getSignUpInfo(identity, account);

                //根据找出的暑期学校名字和年份，再找到暑期学校id,存入signUpInfo里
                int id = summerSchoolDao.selectId(signUpInfo.getSummerSchoolName(), signUpInfo.getYear());
                signUpInfo.setSummerSchoolId(id);

                //再把这条数据插入对应身份的表里
                educationalAdministartorDao.signUpSuccess(signUpInfo);

                //再把他在对应的注册申请表里的那条数据删除
                return educationalAdministartorDao.deleteSignUp(identity,account) > 0 ? true : false;
            }else {
                return educationalAdministartorDao.deleteSignUp(identity,account) > 0 ? true : false;
            }
        } else if (examineResultInfo.getCandidateIdentity().equals("老师")){
            if (examineResultInfo.isResult()){

                SignUpInfo signUpInfo = teacherDao.getSignUpInfo(identity, account);

                int id = summerSchoolDao.selectId(signUpInfo.getSummerSchoolName(), signUpInfo.getYear());
                signUpInfo.setSummerSchoolId(id);

                teacherDao.signUpSuccess(signUpInfo);

                return teacherDao.deleteSignUp(identity,account) > 0 ? true : false;
            }else {
                return teacherDao.deleteSignUp(identity,account) > 0 ? true : false;
            }
        } else if (examineResultInfo.getCandidateIdentity().equals("学生")){
            if (examineResultInfo.isResult()){

            }else {

            }
        }
        return false;
    }
}
