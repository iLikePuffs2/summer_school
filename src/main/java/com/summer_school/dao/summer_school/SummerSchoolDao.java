package com.summer_school.dao.summer_school;

import com.summer_school.pojo.summer_school.SummerSchool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SummerSchoolDao {

    @Insert("insert into summer_school_table(summerSchoolName, year) values (#{summerSchoolName},#{year})")
    public int addSummerSchool(SummerSchool summerSchool);

    @Select("select summerSchoolName,year from summer_school_table order by year")
    public List<SummerSchool> selectAll();
}
