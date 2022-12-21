package com.summer_school.dao.user_sign_in;

import com.summer_school.domain.user.AbstractUser;
import com.summer_school.domain.user.SystemAdministrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SystemAdministratorDao {

    //系统管理员登陆
    @Select("select * from system_administrator_table where account = #{account} and password = #{password}")
    public SystemAdministrator signIn(String account, String password);
}
