package com.summer_school.service.signup_and_examine.impl;

import com.summer_school.dao.SummerSchoolDao;
import com.summer_school.pojo.dto.SummerSchool;
import com.summer_school.service.signup_and_examine.SelectSummerSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectSummerSchoolServiceImpl implements SelectSummerSchoolService {
    @Autowired
    private SummerSchoolDao summerSchoolDao;

    @Override
    public List<SummerSchool> selectSummerSchool() {
        return summerSchoolDao.selectAll();
    }
}
