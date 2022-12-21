package com.summer_school.domain.summer_school;

public class SummerSchool {
    private String summerSchoolName;
    private Integer year;


    public SummerSchool() {
    }

    public SummerSchool(String summerSchoolName, Integer year) {
        this.summerSchoolName = summerSchoolName;
        this.year = year;
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

    public String toString() {
        return "SummerSchool{summerSchoolName = " + summerSchoolName + ", year = " + year + "}";
    }
}
