package com.summer_school.domain.user;

public class Student extends AbstractUser {

    /**
姓名
学校层次(985,211)
就读学校
学生类别(本科生，研究生等)
就读年级
所学专业
性别
政治面貌（团员，党员）
     */

    private String studentName;
    private String schoolLevel;
    private String schoolName;
    private String studentType;
    private String grade;
    private String profession;
    private String gender;
    private String politicalStatus;


    public Student() {
    }

    public Student(String studentName, String schoolLevel, String schoolName, String studentType, String grade, String profession, String gender, String politicalStatus) {
        this.studentName = studentName;
        this.schoolLevel = schoolLevel;
        this.schoolName = schoolName;
        this.studentType = studentType;
        this.grade = grade;
        this.profession = profession;
        this.gender = gender;
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取
     * @return studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取
     * @return schoolLevel
     */
    public String getSchoolLevel() {
        return schoolLevel;
    }

    /**
     * 设置
     * @param schoolLevel
     */
    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
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

    public String toString() {
        return "Student{studentName = " + studentName + ", schoolLevel = " + schoolLevel + ", schoolName = " + schoolName + ", studentType = " + studentType + ", grade = " + grade + ", profession = " + profession + ", gender = " + gender + ", politicalStatus = " + politicalStatus + "}";
    }
}
