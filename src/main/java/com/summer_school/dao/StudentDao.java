package com.summer_school.dao;

import com.summer_school.pojo.dto.SignIn;
import com.summer_school.pojo.po.SignUpStudent;
import com.summer_school.pojo.po.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDao {
    /**
     * 学生登陆
     * @param signIn
     * @return
     */
    @Select("select * from student_table where account = #{account} and password = #{password}")
    public Student signIn(SignIn signIn);


    /**
     * 学生注册
     * @param signUpStudent
     * @return
     */
    @Insert("insert into student_sign_up_table(summerSchoolName, year, account, password, name, schoolName, studentType, grade, profession, gender, politicalStatus, photo) values (#{summerSchoolName},#{year},#{account},#{password},#{name},#{schoolName},#{studentType},#{grade},#{profession},#{gender},#{politicalStatus},#{photo})")
    public int signup(SignUpStudent signUpStudent);
}
