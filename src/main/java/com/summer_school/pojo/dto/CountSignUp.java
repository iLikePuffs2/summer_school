package com.summer_school.pojo.dto;


public class CountSignUp {
    private String[] name;
    private Integer[] number;
    private double[] proportion;


    public CountSignUp() {
    }

    public CountSignUp(String[] name, Integer[] number, double[] proportion) {
        this.name = name;
        this.number = number;
        this.proportion = proportion;
    }

    /**
     * 获取
     * @return name
     */
    public String[] getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String[] name) {
        this.name = name;
    }

    /**
     * 获取
     * @return number
     */
    public Integer[] getNumber() {
        return number;
    }

    /**
     * 设置
     * @param number
     */
    public void setNumber(Integer[] number) {
        this.number = number;
    }

    /**
     * 获取
     * @return proportion
     */
    public double[] getProportion() {
        return proportion;
    }

    /**
     * 设置
     * @param proportion
     */
    public void setProportion(double[] proportion) {
        this.proportion = proportion;
    }

    public String toString() {
        return "CountSignUp{name = " + name + ", number = " + number + ", proportion = " + proportion + "}";
    }
}
