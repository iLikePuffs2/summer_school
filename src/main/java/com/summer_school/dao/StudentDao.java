package com.summer_school.dao;

import com.summer_school.pojo.dto.SignIn;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.pojo.po.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * @param abstractUser
     * @return
     */
    @Insert("insert into student_sign_up_table(summerSchoolName, year, account, password, name, schoolName, studentType, grade, profession, gender, politicalStatus, photo) values (#{summerSchoolName},#{year},#{account},#{password},#{name},#{schoolName},#{studentType},#{grade},#{profession},#{gender},#{politicalStatus},#{photo})")
    public int signup(AbstractUser abstractUser);

    /**
     * 学生注册验证1（学生注册表里——防止重复注册）
     * @param account
     * @return
     */
    @Select("select account from student_sign_up_table where account = #{account}")
    public String signUpCheck1(String account);

    /**
     * 学生注册验证2（学生表里——防止重复注册）
     * @param account
     * @return
     */
    @Select("select account from student_table where account = #{account}")
    public String signUpCheck2(String account);


    /**
     * 查出所有学生注册申请表数据
     * @return
     */
    @Select("select * from student_sign_up_table")
    public List<SignUpInfo> showSignUpInfo();

    /**
     * 查出所有学生表数据
     * @return
     */
    @Select("select * from student_table")
    public List<Student> showStudentInfo();

    /**
     * 注册成功，为学生表的对应那一条添加账号和密码
     * @param signUpInfo
     * @return
     */
    @Insert("insert into educational_administrator_table(summerSchoolId, identity, account, password, name, post, schoolName, academy) VALUES (#{summerSchoolId},#{identity},#{account},#{password},#{name},#{post},#{schoolName},#{academy})")
    public int signUpSuccess(SignUpInfo signUpInfo);


    /**
     * 审核后（无论成功与否），都删除教务管理员的这条注册申请
     * @param identity
     * @param account
     * @return
     */
    @Delete("delete from teacher_educational_administrator_sign_up_table where identity = #{identity} and account = #{account}")
    public int deleteSignUp(String identity,String account);
}
