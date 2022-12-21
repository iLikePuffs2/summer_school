package com.summer_school.domain.user;

public class Teacher extends AbstractUser{
    /**
     * 姓名
     * 职称
     * 学校名称
     * 院系
     */
    private String name;
    private String post;
    private String schoolName;
    private String academy;


    public Teacher() {
    }

    public Teacher(String name, String post, String schoolName, String academy) {
        this.name = name;
        this.post = post;
        this.schoolName = schoolName;
        this.academy = academy;
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

    public String toString() {
        return "Teacher{name = " + name + ", post = " + post + ", schoolName = " + schoolName + ", academy = " + academy + "}";
    }
}
