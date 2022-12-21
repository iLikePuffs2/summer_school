package com.summer_school.dao.user_sign_in;

import com.summer_school.domain.user.AbstractUser;
import com.summer_school.domain.user.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDao {
    //学生登陆
    @Select("select * from educational_manager_table where account = #{account} and password = #{password}")
    public Student signIn(String account, String password);
}
