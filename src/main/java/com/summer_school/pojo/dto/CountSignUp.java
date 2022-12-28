package com.summer_school.pojo.dto;


public class CountSignUp {
    private String[] genderName;
    private Integer[] genderNumber;
    private double[] genderProportion;

    private String[] typeName;
    private Integer[] typeNumber;
    private double[] typeProportion;


    private String[] gradeName;
    private Integer[] gradeNumber;
    private double[] gradeProportion;


    private String[] schoolLevel;
    private Integer[] schoolLevelNum;
    private double[] schoolLevelPro;


    private String[] politicalStatus;
    private Integer[] politicalNum;
    private double[] politicalPro;

    private String[] profession;
    private Integer[] professionNum;
    private double[] professionPro;


    public CountSignUp(String[] genderName, Integer[] genderNumber, double[] genderProportion) {
        this.genderName = genderName;
        this.genderNumber = genderNumber;
        this.genderProportion = genderProportion;
    }

    public CountSignUp() {
    }

    public CountSignUp(String[] genderName, Integer[] genderNumber, double[] genderProportion, String[] typeName, Integer[] typeNumber, double[] typeProportion, String[] gradeName, Integer[] gradeNumber, double[] gradeProportion, String[] schoolLevel, Integer[] schoolLevelNum, double[] schoolLevelPro, String[] politicalStatus, Integer[] politicalNum, double[] politicalPro, String[] profession, Integer[] professionNum, double[] professionPro) {
        this.genderName = genderName;
        this.genderNumber = genderNumber;
        this.genderProportion = genderProportion;
        this.typeName = typeName;
        this.typeNumber = typeNumber;
        this.typeProportion = typeProportion;
        this.gradeName = gradeName;
        this.gradeNumber = gradeNumber;
        this.gradeProportion = gradeProportion;
        this.schoolLevel = schoolLevel;
        this.schoolLevelNum = schoolLevelNum;
        this.schoolLevelPro = schoolLevelPro;
        this.politicalStatus = politicalStatus;
        this.politicalNum = politicalNum;
        this.politicalPro = politicalPro;
        this.profession = profession;
        this.professionNum = professionNum;
        this.professionPro = professionPro;
    }

    /**
     * 获取
     * @return genderName
     */
    public String[] getGenderName() {
        return genderName;
    }

    /**
     * 设置
     * @param genderName
     */
    public void setGenderName(String[] genderName) {
        this.genderName = genderName;
    }

    /**
     * 获取
     * @return genderNumber
     */
    public Integer[] getGenderNumber() {
        return genderNumber;
    }

    /**
     * 设置
     * @param genderNumber
     */
    public void setGenderNumber(Integer[] genderNumber) {
        this.genderNumber = genderNumber;
    }

    /**
     * 获取
     * @return genderProportion
     */
    public double[] getGenderProportion() {
        return genderProportion;
    }

    /**
     * 设置
     * @param genderProportion
     */
    public void setGenderProportion(double[] genderProportion) {
        this.genderProportion = genderProportion;
    }

    /**
     * 获取
     * @return typeName
     */
    public String[] getTypeName() {
        return typeName;
    }

    /**
     * 设置
     * @param typeName
     */
    public void setTypeName(String[] typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取
     * @return typeNumber
     */
    public Integer[] getTypeNumber() {
        return typeNumber;
    }

    /**
     * 设置
     * @param typeNumber
     */
    public void setTypeNumber(Integer[] typeNumber) {
        this.typeNumber = typeNumber;
    }

    /**
     * 获取
     * @return typeProportion
     */
    public double[] getTypeProportion() {
        return typeProportion;
    }

    /**
     * 设置
     * @param typeProportion
     */
    public void setTypeProportion(double[] typeProportion) {
        this.typeProportion = typeProportion;
    }

    /**
     * 获取
     * @return gradeName
     */
    public String[] getGradeName() {
        return gradeName;
    }

    /**
     * 设置
     * @param gradeName
     */
    public void setGradeName(String[] gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 获取
     * @return gradeNumber
     */
    public Integer[] getGradeNumber() {
        return gradeNumber;
    }

    /**
     * 设置
     * @param gradeNumber
     */
    public void setGradeNumber(Integer[] gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    /**
     * 获取
     * @return gradeProportion
     */
    public double[] getGradeProportion() {
        return gradeProportion;
    }

    /**
     * 设置
     * @param gradeProportion
     */
    public void setGradeProportion(double[] gradeProportion) {
        this.gradeProportion = gradeProportion;
    }

    /**
     * 获取
     * @return schoolLevel
     */
    public String[] getSchoolLevel() {
        return schoolLevel;
    }

    /**
     * 设置
     * @param schoolLevel
     */
    public void setSchoolLevel(String[] schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    /**
     * 获取
     * @return schoolLevelNum
     */
    public Integer[] getSchoolLevelNum() {
        return schoolLevelNum;
    }

    /**
     * 设置
     * @param schoolLevelNum
     */
    public void setSchoolLevelNum(Integer[] schoolLevelNum) {
        this.schoolLevelNum = schoolLevelNum;
    }

    /**
     * 获取
     * @return schoolLevelPro
     */
    public double[] getSchoolLevelPro() {
        return schoolLevelPro;
    }

    /**
     * 设置
     * @param schoolLevelPro
     */
    public void setSchoolLevelPro(double[] schoolLevelPro) {
        this.schoolLevelPro = schoolLevelPro;
    }

    /**
     * 获取
     * @return politicalStatus
     */
    public String[] getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置
     * @param politicalStatus
     */
    public void setPoliticalStatus(String[] politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取
     * @return politicalNum
     */
    public Integer[] getPoliticalNum() {
        return politicalNum;
    }

    /**
     * 设置
     * @param politicalNum
     */
    public void setPoliticalNum(Integer[] politicalNum) {
        this.politicalNum = politicalNum;
    }

    /**
     * 获取
     * @return politicalPro
     */
    public double[] getPoliticalPro() {
        return politicalPro;
    }

    /**
     * 设置
     * @param politicalPro
     */
    public void setPoliticalPro(double[] politicalPro) {
        this.politicalPro = politicalPro;
    }

    /**
     * 获取
     * @return profession
     */
    public String[] getProfession() {
        return profession;
    }

    /**
     * 设置
     * @param profession
     */
    public void setProfession(String[] profession) {
        this.profession = profession;
    }

    /**
     * 获取
     * @return professionNum
     */
    public Integer[] getProfessionNum() {
        return professionNum;
    }

    /**
     * 设置
     * @param professionNum
     */
    public void setProfessionNum(Integer[] professionNum) {
        this.professionNum = professionNum;
    }

    /**
     * 获取
     * @return professionPro
     */
    public double[] getProfessionPro() {
        return professionPro;
    }

    /**
     * 设置
     * @param professionPro
     */
    public void setProfessionPro(double[] professionPro) {
        this.professionPro = professionPro;
    }

    public String toString() {
        return "CountSignUp{genderName = " + genderName + ", genderNumber = " + genderNumber + ", genderProportion = " + genderProportion + ", typeName = " + typeName + ", typeNumber = " + typeNumber + ", typeProportion = " + typeProportion + ", gradeName = " + gradeName + ", gradeNumber = " + gradeNumber + ", gradeProportion = " + gradeProportion + ", schoolLevel = " + schoolLevel + ", schoolLevelNum = " + schoolLevelNum + ", schoolLevelPro = " + schoolLevelPro + ", politicalStatus = " + politicalStatus + ", politicalNum = " + politicalNum + ", politicalPro = " + politicalPro + ", profession = " + profession + ", professionNum = " + professionNum + ", professionPro = " + professionPro + "}";
    }
}
