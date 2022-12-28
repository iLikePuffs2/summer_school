package com.summer_school.pojo.dto;

public class GradeDto {
    private String grade;
    private Integer num;


    public GradeDto() {
    }

    public GradeDto(String grade, Integer num) {
        this.grade = grade;
        this.num = num;
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
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    public String toString() {
        return "GradeDto{grade = " + grade + ", num = " + num + "}";
    }
}
