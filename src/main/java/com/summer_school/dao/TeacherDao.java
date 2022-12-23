package com.summer_school.dao;

import com.summer_school.pojo.dto.SignIn;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpAdminTeacher;
import com.summer_school.pojo.po.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherDao {
    /**
     * 老师登陆
     * @param signIn
     * @return
     */
    @Select("select * from teacher_table where account = #{account} and password = #{password}")
    public Teacher signIn(SignIn signIn);

    /**
     * 老师注册申请
     * @param signUpAdminTeacher
     * @return
     */
    @Insert("insert into teacher_educational_administrator_sign_up_table(identity, summerSchoolName, year, account, password, name, post, schoolName, academy, photo) values (#{identity},#{summerSchoolName},#{year},#{account},#{password},#{name},#{post},#{schoolName},#{academy},#{photo})")
    public int signup(SignUpAdminTeacher signUpAdminTeacher);
}