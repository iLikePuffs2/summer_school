package com.summer_school.service.signup_and_examine;

import com.summer_school.pojo.dto.SignIn;

public interface UserSignInService {
    /**
     * 登陆时的查询（账号、密码）
     * @param signIn
     * @return 查询结果和用户身份
     */
    public String signIn(SignIn signIn);

}
