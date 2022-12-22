package com.summer_school.dao.user;

import com.summer_school.pojo.user.lasting.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDao {
    //学生登陆
    @Select("select * from student_table where account = #{account} and password = #{password}")
    public Student signIn(String account, String password);
}
