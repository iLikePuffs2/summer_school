package com.summer_school.pojo.po;

public class SignUpInfo extends AbstractUser{

    /**
     * 身份（老师/教务管理员）
     * 暑期学校名称
     * 该暑期学校创办的年份
     * 暑期学校id
     * 账号（电话号码）
     * 密码
     * 姓名
     * 职称
     * 学校名称
     * 院系
     * 工作证照片（存的是图片url）
     */

    /**
     * 暑期学校名称
     * 该暑期学校创办的年份
     * 账号（电话号码）
     * 密码
     * 姓名
     * 学校名称
     * 学生类别(本科生，研究生等)
     * 就读年级
     * 所学专业
     * 性别
     * 政治面貌（团员，党员）
     * 学生证照片（存的是图片url）
     */
    private String identity;
    private String summerSchoolName;
    private int year;
    private String account;
    private String password;
    private String name;
    private String post;
    private String schoolName;
    private String academy;
    private String photo;
    private String studentType;
    private String grade;
    private String profession;
    private String gender;
    private String politicalStatus;


    public SignUpInfo() {
    }

    public SignUpInfo(String identity, String summerSchoolName, int summerSchoolId, int year, String account, String password, String name, String post, String schoolName, String academy, String photo, String studentType, String grade, String profession, String gender, String politicalStatus) {
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
        this.studentType = studentType;
        this.grade = grade;
        this.profession = profession;
        this.gender = gender;
        this.politicalStatus = politicalStatus;
    }


    public String getSummerSchoolName() {
        return summerSchoolName;
    }

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

    /**
     * 获取
     * @return studentType
     */
    public String getStudentType() {
        return studentType;
    }

    /**
     * 设置
     * @param studentType
     */
    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    /**
     * 获取
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return politicalStatus
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置
     * @param politicalStatus
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }


}
