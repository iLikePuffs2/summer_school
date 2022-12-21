package com.summer_school.dao.user;

import com.summer_school.domain.user.lasting.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherDao {
    //老师登陆
    @Select("select * from teacher_table where account = #{account} and password = #{password}")
    public Teacher signIn(String account, String password);
}