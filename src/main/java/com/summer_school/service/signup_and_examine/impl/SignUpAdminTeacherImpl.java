package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.user.EducationalAdministartorDao;
import com.summer_school.pojo.user.lasting.AbstractUser;
import com.summer_school.service.signup_and_examine.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpAdminTeacherImpl implements UserService {

    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;

    @Override
    public boolean add(AbstractUser abstract_user) {
        return false;
    }

    @Override
    public boolean delete(AbstractUser abstract_user) {
        return false;
    }

    @Override
    public boolean update(AbstractUser abstract_user) {
        return false;
    }
}
