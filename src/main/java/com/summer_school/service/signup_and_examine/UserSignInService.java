package com.summer_school.service.signup_and_examine;

public interface UserSignInService {
    /**
     * 登陆时的查询（账号、密码）
     * @param account
     * @param password
     * @return 查询结果和用户身份
     */
    public String signIn(String account, String password);

}
