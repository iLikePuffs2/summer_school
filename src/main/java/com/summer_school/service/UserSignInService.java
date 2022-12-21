package com.summer_school.service;

import com.summer_school.dao.user_sign_in.SystemAdministratorDao;
import com.summer_school.domain.user.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserSignInService {
    /**
     * 登陆时的查询（账号、密码）
     * @param account
     * @param password
     * @return 查询结果和用户身份
     */
    public String signIn(String account, String password);

}
