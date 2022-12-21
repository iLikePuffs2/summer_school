package com.summer_school.service.impl;

import com.summer_school.dao.user_sign_in.EducationalAdministartorDao;
import com.summer_school.dao.user_sign_in.StudentDao;
import com.summer_school.dao.user_sign_in.SystemAdministratorDao;
import com.summer_school.dao.user_sign_in.TeacherDao;
import com.summer_school.domain.user.AbstractUser;
import com.summer_school.service.UserService;

import com.summer_school.service.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSignInImpl implements UserSignInService {

    @Autowired
    private SystemAdministratorDao systemAdministratorDao;

    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    //查询账号和密码，并携带身份返回
    @Override
    public String signIn(String account, String password) {
        if (systemAdministratorDao.signIn(account,password) != null){
            return "系统管理员";
        } else if (educationalAdministartorDao.signIn(account,password) != null){
            return "教务管理员";
        } else if (teacherDao.signIn(account,password) != null){
            return "老师";
        } else if (studentDao.signIn(account,password) != null){
            return "学生";
        }

        return null;
    }
}
