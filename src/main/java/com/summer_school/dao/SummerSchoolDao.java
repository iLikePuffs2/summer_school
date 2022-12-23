package com.summer_school.dao;

import com.summer_school.pojo.dto.SummerSchool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SummerSchoolDao {

    @Insert("insert into summer_school_table(summerSchoolName, year) values (#{summerSchoolName},#{year})")
    public int addSummerSchool(SummerSchool summerSchool);

    /**
     * 根据暑期学校名字和年份查对应的那个id
     * @param summerSchoolName
     * @param year
     * @return
     */
    @Select("select id from summer_school_table where summerSchoolName = #{summerSchoolName} and year = #{year}")
    public int selectId(String summerSchoolName,int year);

    /**
     * 查询暑期学校的名字和年份,按年份排序
     * @return
     */
    @Select("select summerSchoolName,year from summer_school_table order by year")
    public List<SummerSchool> selectAll();
}
