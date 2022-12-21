package com.summer_school.dao.user_sign_in;

import com.summer_school.domain.user.AbstractUser;
import com.summer_school.domain.user.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherDao {
    //老师登陆
    @Select("select * from educational_manager_table where account = #{account} and password = #{password}")
    public Teacher signIn(String account, String password);
}