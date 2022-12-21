package com.summer_school.domain.user.lasting;

public abstract class AbstractUser {
    private Integer id;
    private Integer summerSchoolId;
    private String account;
    private String password;
    private String identity;


    public AbstractUser() {
    }

    public AbstractUser(Integer id, Integer summerSchoolId, String account, String password, String identity) {
        this.id = id;
        this.summerSchoolId = summerSchoolId;
        this.account = account;
        this.password = password;
        this.identity = identity;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return summerSchoolId
     */
    public Integer getSummerSchoolId() {
        return summerSchoolId;
    }

    /**
     * 设置
     * @param summerSchoolId
     */
    public void setSummerSchoolId(Integer summerSchoolId) {
        this.summerSchoolId = summerSchoolId;
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

    /**
     * 获取
     * @return identity
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置
     * @param identity
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String toString() {
        return "AbstractUser{id = " + id + ", summerSchoolId = " + summerSchoolId + ", account = " + account + ", password = " + password + ", identity = " + identity + "}";
    }
}
