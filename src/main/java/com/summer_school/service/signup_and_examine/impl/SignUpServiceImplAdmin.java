package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.EducationalAdministartorDao;
import com.summer_school.pojo.po.SignUpAdminTeacher;
import com.summer_school.service.signup_and_examine.TeacherOrAdminSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImplAdmin implements TeacherOrAdminSignUpService {
    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;

    @Override
    public boolean signup(SignUpAdminTeacher signUpAdminTeacher) {
        if (educationalAdministartorDao.signup(signUpAdminTeacher) > 0){
            return true;
        }
        return false;
    }
}
