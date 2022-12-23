package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.EducationalAdministartorDao;
import com.summer_school.dao.StudentDao;
import com.summer_school.dao.SystemAdministratorDao;
import com.summer_school.dao.TeacherDao;

import com.summer_school.pojo.dto.SignIn;
import com.summer_school.service.signup_and_examine.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignInServiceImpl implements UserSignInService {

    @Autowired
    private SystemAdministratorDao systemAdministratorDao;

    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    /**
     * 查询账号和密码，并携带身份返回
     * @param signIn
     * @return identity
     */
    @Override
    public String signIn(SignIn signIn) {
        if (systemAdministratorDao.signIn(signIn) != null){
            return "系统管理员";
        } else if (educationalAdministartorDao.signIn(signIn) != null){
            return "教务管理员";
        } else if (teacherDao.signIn(signIn) != null){
            return "老师";
        } else if (studentDao.signIn(signIn) != null){
            return "学生";
        }

        return null;
    }
}
