package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.StudentDao;
import com.summer_school.pojo.po.SignUpStudent;
import com.summer_school.service.signup_and_examine.StudentSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImplStudent implements StudentSignUpService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public boolean signup(SignUpStudent signUpStudent) {
        if (studentDao.signup(signUpStudent) > 0){
            return true;
        }
        return false;
    }
}
