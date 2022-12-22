package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.summer_school.SummerSchoolDao;
import com.summer_school.pojo.summer_school.SummerSchool;
import com.summer_school.service.signup_and_examine.CreateSummerSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSummerSchoolServiceImpl implements CreateSummerSchoolService {
    @Autowired
    private SummerSchoolDao summerSchoolDao;

    @Override
    public boolean createSummerSchool(SummerSchool summerSchool) {
        if (summerSchoolDao.addSummerSchool(summerSchool) > 0){
            return true;
        }
        return false;
    }
}
