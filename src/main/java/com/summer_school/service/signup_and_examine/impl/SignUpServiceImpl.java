package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.EducationalAdministartorDao;
import com.summer_school.dao.StudentDao;
import com.summer_school.dao.SummerSchoolDao;
import com.summer_school.dao.TeacherDao;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.service.signup_and_examine.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public boolean signup(AbstractUser abstractUser) {
        String identity = abstractUser.getIdentity();
        String account = abstractUser.getAccount();
        if (identity.equals("教务管理员")){
            if (educationalAdministartorDao.signUpCheck1(account) == null && educationalAdministartorDao.signUpCheck2(account) == null ){
                return educationalAdministartorDao.signup(abstractUser) > 0 ? true : false;
            }
        } else if (identity.equals("老师")) {
            if (teacherDao.signUpCheck1(account) == null  && teacherDao.signUpCheck2(account) == null ){
                return teacherDao.signup(abstractUser) > 0 ? true : false;
            }

        } else if (identity.equals("学生")) {
            if (studentDao.signUpCheck1(account) == null && studentDao.signUpCheck2(account) == null){
                return studentDao.signup(abstractUser) > 0 ? true : false;
            }
        }

        return false;
    }
}
