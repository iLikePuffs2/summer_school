package com.summer_school.dao.user_sign_in;

import com.summer_school.domain.user.AbstractUser;
import com.summer_school.domain.user.EducationalAdministartor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EducationalAdministartorDao{
    //教务管理员登陆
    @Select("select * from educational_manager_table where account = #{account} and password = #{password}")
    public EducationalAdministartor signIn(String account, String password);
}
