package com.summer_school.pojo.dto.into;

public class FreeStudentIntoDto {

    /**
     *这是指定天数/主题/研究热点的Dto
     */
    private Integer choice;

    //固定项的值(天数/主题id/研究热点id)
    private Integer solidValue;

    //学生群体固定项编号（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
    private Integer[] studentSolidNum;

    //学生群体固定项的值
    private String[] studentSolidValue;

    //学生群体可变项编号（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
    private Integer studentVariableNum;


    public FreeStudentIntoDto() {
    }

    public FreeStudentIntoDto(Integer solidValue, Integer[] studentSolidNum, String[] studentSolidValue, Integer studentVariableNum) {
        this.solidValue = solidValue;
        this.studentSolidNum = studentSolidNum;
        this.studentSolidValue = studentSolidValue;
        this.studentVariableNum = studentVariableNum;
    }

    public FreeStudentIntoDto(Integer choice, Integer solidValue, Integer[] studentSolidNum, String[] studentSolidValue, Integer studentVariableNum) {
        this.choice = choice;
        this.solidValue = solidValue;
        this.studentSolidNum = studentSolidNum;
        this.studentSolidValue = studentSolidValue;
        this.studentVariableNum = studentVariableNum;
    }

    /**
     * 获取
     * @return solidValue
     */
    public Integer getSolidValue() {
        return solidValue;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    /**
     * 设置
     * @param solidValue
     */
    public void setSolidValue(Integer solidValue) {
        this.solidValue = solidValue;
    }

    /**
     * 获取
     * @return studentSolidNum
     */
    public Integer[] getStudentSolidNum() {
        return studentSolidNum;
    }

    /**
     * 设置
     * @param studentSolidNum
     */
    public void setStudentSolidNum(Integer[] studentSolidNum) {
        this.studentSolidNum = studentSolidNum;
    }

    /**
     * 获取
     * @return studentSolidValue
     */
    public String[] getStudentSolidValue() {
        return studentSolidValue;
    }

    /**
     * 设置
     * @param studentSolidValue
     */
    public void setStudentSolidValue(String[] studentSolidValue) {
        this.studentSolidValue = studentSolidValue;
    }

    /**
     * 获取
     * @return studentVariableNum
     */
    public Integer getStudentVariableNum() {
        return studentVariableNum;
    }

    /**
     * 设置
     * @param studentVariableNum
     */
    public void setStudentVariableNum(Integer studentVariableNum) {
        this.studentVariableNum = studentVariableNum;
    }

    public String toString() {
        return "freeStudentIntoDto{solidValue = " + solidValue + ", studentSolidNum = " + studentSolidNum + ", studentSolidValue = " + studentSolidValue + ", studentVariableNum = " + studentVariableNum + "}";
    }
}
