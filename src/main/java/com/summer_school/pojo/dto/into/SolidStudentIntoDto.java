package com.summer_school.pojo.dto.into;

public class SolidStudentIntoDto {

    /**
     *这是指定学生群体的Dto
     */

    //学生群体固定项编号（学校层次1，学生类别2，就读年级3，所学专业4，性别5）
    private Integer[] studentSolidNum;

    //学生群体固定项的值
    private String[] studentSolidValue;


    public SolidStudentIntoDto() {
    }

    public SolidStudentIntoDto(Integer[] studentSolidNum, String[] studentSolidValue) {
        this.studentSolidNum = studentSolidNum;
        this.studentSolidValue = studentSolidValue;
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

    public String toString() {
        return "SolidStudentIntoDto{studentSolidNum = " + studentSolidNum + ", studentSolidValue = " + studentSolidValue + "}";
    }
}
