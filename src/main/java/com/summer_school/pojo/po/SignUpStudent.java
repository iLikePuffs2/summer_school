package com.summer_school.pojo.po;

public class SignUpStudent {
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
    private String summerSchoolName;
    private Integer year;
    private String account;
    private String password;
    private String name;
    private String schoolName;
    private String studentType;
    private String grade;
    private String profession;
    private String gender;
    private String politicalStatus;
    private String photo;


    public SignUpStudent() {
    }

    public SignUpStudent(String summerSchoolName, Integer year, String account, String password, String name, String schoolName, String studentType, String grade, String profession, String gender, String politicalStatus, String photo) {
        this.summerSchoolName = summerSchoolName;
        this.year = year;
        this.account = account;
        this.password = password;
        this.name = name;
        this.schoolName = schoolName;
        this.studentType = studentType;
        this.grade = grade;
        this.profession = profession;
        this.gender = gender;
        this.politicalStatus = politicalStatus;
        this.photo = photo;
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
    public Integer getYear() {
        return year;
    }

    /**
     * 设置
     * @param year
     */
    public void setYear(Integer year) {
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
        return "SignUpStudent{summerSchoolName = " + summerSchoolName + ", year = " + year + ", account = " + account + ", password = " + password + ", name = " + name + ", schoolName = " + schoolName + ", studentType = " + studentType + ", grade = " + grade + ", profession = " + profession + ", gender = " + gender + ", politicalStatus = " + politicalStatus + ", photo = " + photo + "}";
    }
}
