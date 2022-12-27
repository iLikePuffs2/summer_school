package com.summer_school.pojo.po;

import java.util.Date;

public class Topic {
    /**
     * 主题编号
     * 日期（主题开始的日期，具体到天数，目前暂定一天一个主题）
     * 主题名字
     * 所属暑期学校id
     * 第几天（用户可能需要输入从第几天到第几天的学生学习投入度的变化情况，该属性用于此需求）
     * 热度
     */
    private Integer id;
    private String date;
    private String name;
    private Integer summerSchoolId;
    private Integer numDay;
    private Integer participantNum;


    public Topic() {
    }

    public Topic(Integer id, String date, String name, Integer summerSchoolId, Integer numDay, Integer participantNum) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.summerSchoolId = summerSchoolId;
        this.numDay = numDay;
        this.participantNum = participantNum;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
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
     * @return summerSchoolId
     */
    public Integer getSummerSchoolId() {
        return summerSchoolId;
    }

    /**
     * 设置
     * @param summerSchoolId
     */
    public void setSummerSchoolId(Integer summerSchoolId) {
        this.summerSchoolId = summerSchoolId;
    }

    /**
     * 获取
     * @return numDay
     */
    public Integer getNumDay() {
        return numDay;
    }

    /**
     * 设置
     * @param numDay
     */
    public void setNumDay(Integer numDay) {
        this.numDay = numDay;
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

    public String toString() {
        return "Topic{id = " + id + ", date = " + date + ", name = " + name + ", summerSchoolId = " + summerSchoolId + ", numDay = " + numDay + ", participantNum = " + participantNum + "}";
    }
}
