package com.summer_school.pojo.dto;

import org.apache.ibatis.annotations.Select;

public class TopicDto {
    private String name;
    private Integer participantNum;
    private double activeRate;
    private Integer heatScore;

    public TopicDto() {
    }

    public TopicDto(String name, Integer participantNum, double activeRate, Integer heatScore) {
        this.name = name;
        this.participantNum = participantNum;
        this.activeRate = activeRate;
        this.heatScore = heatScore;
    }


    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return participantNum
     */
    public Integer getParticipantNum() {
        return participantNum;
    }

    /**
     * 设置
     * @param participantNum
     */
    public void setParticipantNum(Integer participantNum) {
        this.participantNum = participantNum;
    }

    /**
     * 获取
     * @return activeRate
     */
    public double getActiveRate() {
        return activeRate;
    }

    /**
     * 设置
     * @param activeRate
     */
    public void setActiveRate(double activeRate) {
        this.activeRate = activeRate;
    }

    public String toString() {
        return "TopicDto{name = " + name + ", participantNum = " + participantNum + ", activeRate = " + activeRate + "}";
    }

    /**
     * 获取
     * @return heatScore
     */
    public Integer getHeatScore() {
        return heatScore;
    }

    /**
     * 设置
     * @param heatScore
     */
    public void setHeatScore(Integer heatScore) {
        this.heatScore = heatScore;
    }
}
