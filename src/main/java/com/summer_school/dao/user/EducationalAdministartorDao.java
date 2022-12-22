package com.summer_school.dao.user;

import com.summer_school.pojo.user.lasting.EducationalAdministartor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EducationalAdministartorDao{
    //教务管理员登陆
    @Select("select * from educational_administrator_table where account = #{account} and password = #{password}")
    public EducationalAdministartor signIn(String account, String password);

    //将教务管理员注册信息保存到老师和教务管理员注册申请表（teacher_educational_administrator_sign_up_table）
    @Insert("insert into teacher_educational_administrator_sign_up_table values (identity = #{identity},year = #{year},account = #{account},password = #{password},post = #{post},schoolName = #{schoolName},academy = #{academy},photo = #{photo},schoolName = #{schoolName})")
    public boolean saveSignUpInfo(String identity,Integer year,String account,String password,String post,String schoolName,String academy,String photoschoolName);
}
