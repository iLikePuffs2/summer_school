package com.summer_school.dao.summer_school;

import com.summer_school.domain.summer_school.SummerSchool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SummerSchoolDao {

    @Insert("insert into summer_school_table(summerSchoolName, year) values (#{summerSchoolName},#{year})")
    public int addSummerSchool(SummerSchool summerSchool);
}
