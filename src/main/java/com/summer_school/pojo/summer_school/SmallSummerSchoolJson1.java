package com.summer_school.pojo.summer_school;

public class SmallSummerSchoolJson1 {
    private String unrelatedName;
    private String year;


    public SmallSummerSchoolJson1() {
    }

    public SmallSummerSchoolJson1(String unrelatedName, String year) {
        this.unrelatedName = unrelatedName;
        this.year = year;
    }

    /**
     * 获取
     * @return unrelatedName
     */
    public String getUnrelatedName() {
        return unrelatedName;
    }

    /**
     * 设置
     * @param unrelatedName
     */
    public void setUnrelatedName(String unrelatedName) {
        this.unrelatedName = unrelatedName;
    }

    /**
     * 获取
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * 设置
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        return "SmallSummerSchoolJson1{unrelatedName = " + unrelatedName + ", year = " + year + "}";
    }
}
