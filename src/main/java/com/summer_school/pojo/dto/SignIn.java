package com.summer_school.pojo.dto;

public class SignIn {
    private String account;
    private String password;


    public SignIn() {
    }

    public SignIn(String account, String password) {
        this.account = account;
        this.password = password;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "SignIn{account = " + account + ", password = " + password + "}";
    }
}
