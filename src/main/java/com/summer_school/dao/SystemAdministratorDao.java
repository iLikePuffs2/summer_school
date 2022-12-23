package com.summer_school.dao;

import com.summer_school.pojo.dto.SignIn;
import com.summer_school.pojo.po.SystemAdministrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemAdministratorDao {

    /**
     * 系统管理员登陆
     * @param signIn
     * @return
     */
    @Select("select * from system_administrator_table where account = #{account} and password = #{password}")
    public SystemAdministrator signIn(SignIn signIn);
}
