package com.summer_school.pojo.dto.back;

public class AnalyseBackDto {

    //返回值的横坐标（数组）
    private String[] horizontalValue;

    //返回值的横坐标对应纵坐标的值（数组）
    private Integer[] verticalValue;


    public AnalyseBackDto() {
    }

    public AnalyseBackDto(String[] horizontalValue, Integer[] verticalValue) {
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;
    }

    /**
     * 获取
     * @return horizontalValue
     */
    public String[] getHorizontalValue() {
        return horizontalValue;
    }

    /**
     * 设置
     * @param horizontalValue
     */
    public void setHorizontalValue(String[] horizontalValue) {
        this.horizontalValue = horizontalValue;
    }

    /**
     * 获取
     * @return verticalValue
     */
    public Integer[] getVerticalValue() {
        return verticalValue;
    }

    /**
     * 设置
     * @param verticalValue
     */
    public void setVerticalValue(Integer[] verticalValue) {
        this.verticalValue = verticalValue;
    }

    public String toString() {
        return "AnalyseBackDto{horizontalValue = " + horizontalValue + ", verticalValue = " + verticalValue + "}";
    }
}
