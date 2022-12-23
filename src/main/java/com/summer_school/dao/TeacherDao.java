package com.summer_school.dao;

import com.summer_school.pojo.dto.SignIn;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.pojo.po.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * @param abstractUser
     * @return
     */
    @Insert("insert into teacher_educational_administrator_sign_up_table(identity, summerSchoolName, year, account, password, name, post, schoolName, academy, photo) values (#{identity},#{summerSchoolName},#{year},#{account},#{password},#{name},#{post},#{schoolName},#{academy},#{photo})")
    public int signup(AbstractUser abstractUser);


    /**
     * 老师注册验证1（老师注册表里——防止重复注册）
     * @param account
     * @return
     */
    @Select("select account from teacher_educational_administrator_sign_up_table where account = #{account}")
    public String signUpCheck1(String account);

    /**
     * 老师注册验证2（老师表里——防止重复注册）
     * @param account
     * @return
     */
    @Select("select account from teacher_table where account = #{account}")
    public String signUpCheck2(String account);


    /**
     * 在老师和教务管理员注册申请表里查出全部老师信息
     * @param identity
     * @return
     */
    @Select("select identity,year,account,name,post,schoolName,academy,photo,summerSchoolName from teacher_educational_administrator_sign_up_table where identity = #{identity}")
    public List<SignUpInfo> showSignUpInfo(String identity);

    /**
     * 根据identity和account找出注册表的那条老师信息
     */
    @Select("select * from teacher_educational_administrator_sign_up_table where identity = #{identity} and account = #{account}")
    public SignUpInfo getSignUpInfo(String identity,String account);

    /**
     * 注册成功，为老师表添加一条数据
     * @param signUpInfo
     * @return
     */
    @Insert("insert into teacher_table(summerSchoolId, identity, account, password, name, post, schoolName, academy) VALUES (#{summerSchoolId},#{identity},#{account},#{password},#{name},#{post},#{schoolName},#{academy})")
    public int signUpSuccess(SignUpInfo signUpInfo);


    /**
     * 审核后（无论成功与否），都删除老师的这条注册申请
     * @param identity
     * @param account
     * @return
     */
    @Delete("delete from teacher_educational_administrator_sign_up_table where identity = #{identity} and account = #{account}")
    public int deleteSignUp(String identity,String account);
}