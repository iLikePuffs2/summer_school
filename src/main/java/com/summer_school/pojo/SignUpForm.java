package com.summer_school.pojo;

import java.util.List;

public class SignUpForm {
    /**
     * 名字
     * 性别
     * 就读学校
     * 学生类别
     * 就读年级
     * 所学专业
     * 政治面貌
     * 学校层次
     */
    List<String> studentName;
    List<String> gender;
    List<String> schoolName;
    List<String> studentType;
    List<String> grade;
    List<String> profession;
    List<String> politicalStatus;
    List<String> schoolLevel;


    public SignUpForm() {
    }

    public SignUpForm(List<String> studentName, List<String> gender, List<String> schoolName, List<String> studentType, List<String> grade, List<String> profession, List<String> politicalStatus, List<String> schoolLevel) {
        this.studentName = studentName;
        this.gender = gender;
        this.schoolName = schoolName;
        this.studentType = studentType;
        this.grade = grade;
        this.profession = profession;
        this.politicalStatus = politicalStatus;
        this.schoolLevel = schoolLevel;
    }

    /**
     * 获取
     * @return studentName
     */
    public List<String> getStudentName() {
        return studentName;
    }

    /**
     * 设置
     * @param studentName
     */
    public void setStudentName(List<String> studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取
     * @return gender
     */
    public List<String> getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return schoolName
     */
    public List<String> getSchoolName() {
        return schoolName;
    }

    /**
     * 设置
     * @param schoolName
     */
    public void setSchoolName(List<String> schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取
     * @return studentType
     */
    public List<String> getStudentType() {
        return studentType;
    }

    /**
     * 设置
     * @param studentType
     */
    public void setStudentType(List<String> studentType) {
        this.studentType = studentType;
    }

    /**
     * 获取
     * @return grade
     */
    public List<String> getGrade() {
        return grade;
    }

    /**
     * 设置
     * @param grade
     */
    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    /**
     * 获取
     * @return profession
     */
    public List<String> getProfession() {
        return profession;
    }

    /**
     * 设置
     * @param profession
     */
    public void setProfession(List<String> profession) {
        this.profession = profession;
    }

    /**
     * 获取
     * @return politicalStatus
     */
    public List<String> getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置
     * @param politicalStatus
     */
    public void setPoliticalStatus(List<String> politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取
     * @return schoolLevel
     */
    public List<String> getSchoolLevel() {
        return schoolLevel;
    }

    /**
     * 设置
     * @param schoolLevel
     */
    public void setSchoolLevel(List<String> schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String toString() {
        return "SignUpForm{studentName = " + studentName + ", gender = " + gender + ", schoolName = " + schoolName + ", studentType = " + studentType + ", grade = " + grade + ", profession = " + profession + ", politicalStatus = " + politicalStatus + ", schoolLevel = " + schoolLevel + "}";
    }
}
