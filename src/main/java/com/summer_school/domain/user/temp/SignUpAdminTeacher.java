package com.summer_school.domain.user.temp;

public class SignUpAdminTeacher {

    /**
     * 身份（老师/教务管理员）
     * 暑期学校名称
     * 该暑期学校创办的年份
     * 账号（电话号码）
     * 密码
     * 姓名
     * 职称
     * 学校名称
     * 院系
     * 工作证照片（存的是图片url）
     */
    private String identity;
    private String summerSchoolName;
    private Integer year;
    private String account;
    private String password;
    private String name;
    private String post;
    private String schoolName;
    private String academy;
    private String photo;


    public SignUpAdminTeacher() {
    }

    public SignUpAdminTeacher(String identity, String summerSchoolName, int year, String account, String password, String name, String post, String schoolName, String academy, String photo) {
        this.identity = identity;
        this.summerSchoolName = summerSchoolName;
        this.year = year;
        this.account = account;
        this.password = password;
        this.name = name;
        this.post = post;
        this.schoolName = schoolName;
        this.academy = academy;
        this.photo = photo;
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

    /**
     * 获取
     * @return summerSchoolName
     */
    public String getSummerSchoolName() {
        return summerSchoolName;
    }

    /**
     * 设置
     * @param summerSchoolName
     */
    public void setSummerSchoolName(String summerSchoolName) {
        this.summerSchoolName = summerSchoolName;
    }

    /**
     * 获取
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * 设置
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return post
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置
     * @param post
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取
     * @return schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置
     * @param schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取
     * @return academy
     */
    public String getAcademy() {
        return academy;
    }

    /**
     * 设置
     * @param academy
     */
    public void setAcademy(String academy) {
        this.academy = academy;
    }

    /**
     * 获取
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String toString() {
        return "SignUpAdminTeacher{identity = " + identity + ", summerSchoolName = " + summerSchoolName + ", year = " + year + ", account = " + account + ", password = " + password + ", name = " + name + ", post = " + post + ", schoolName = " + schoolName + ", academy = " + academy + ", photo = " + photo + "}";
    }
}
