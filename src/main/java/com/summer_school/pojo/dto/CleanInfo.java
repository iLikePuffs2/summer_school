package com.summer_school.pojo.dto;

import java.util.List;

public class CleanInfo {

    /**
     * 清洗学生报名表传入参数包括：
     * 暑期学校id
     * csv文档的url
     */

    /**
     * 清洗参会详情表传入参数包括：
     * 暑期学校id summerSchoolId
     * xlsx文档的url
     * 主题id topicId
     * 1个或多个研究热点id hotSpotId
     */


    /**
     * 清洗签到表传入参数包括：
     * 暑期学校id
     * 每个研究热点的名字
     * xlsx文档的url
     */

    private Integer summerSchoolId;
    private String fileURL;
    private Integer numDay;
    private String dateTime;
    private String topicName;
    private String hotSpotNameSingle;
    private String[] hotSpotName;
    private String[] hotSpotStartTime;
    private String[] hotSpotEndTime;
    private String[] picUrl;
    private Integer topicId;
    private Integer[] hotSpotId;


    public CleanInfo() {
    }

    public CleanInfo(Integer summerSchoolId, String fileURL) {
        this.summerSchoolId = summerSchoolId;
        this.fileURL = fileURL;
    }

    public CleanInfo(Integer summerSchoolId, String fileURL, Integer topicId, Integer[] hotSpotId) {
        this.summerSchoolId = summerSchoolId;
        this.fileURL = fileURL;
        this.topicId = topicId;
        this.hotSpotId = hotSpotId;
    }

    public CleanInfo(Integer summerSchoolId, String fileURL, String hotSpotNameSingle) {
        this.summerSchoolId = summerSchoolId;
        this.fileURL = fileURL;
        this.hotSpotNameSingle = hotSpotNameSingle;
    }

    public CleanInfo(Integer summerSchoolId, String fileURL, Integer numDay, String dateTime, String topicName, String[] hotSpotName, String[] hotSpotStartTime, String[] hotSpotEndTime, String[] picUrl) {
        this.summerSchoolId = summerSchoolId;
        this.fileURL = fileURL;
        this.numDay = numDay;
        this.dateTime = dateTime;
        this.topicName = topicName;
        this.hotSpotName = hotSpotName;
        this.hotSpotStartTime = hotSpotStartTime;
        this.hotSpotEndTime = hotSpotEndTime;
        this.picUrl = picUrl;
    }

    public CleanInfo(Integer summerSchoolId, String fileURL, Integer numDay, String dateTime, String topicName, String hotSpotNameSingle, String[] hotSpotName, String[] hotSpotStartTime, String[] hotSpotEndTime, String[] picUrl) {
        this.summerSchoolId = summerSchoolId;
        this.fileURL = fileURL;
        this.numDay = numDay;
        this.dateTime = dateTime;
        this.topicName = topicName;
        this.hotSpotNameSingle = hotSpotNameSingle;
        this.hotSpotName = hotSpotName;
        this.hotSpotStartTime = hotSpotStartTime;
        this.hotSpotEndTime = hotSpotEndTime;
        this.picUrl = picUrl;
    }


    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer[] getHotSpotId() {
        return hotSpotId;
    }

    public void setHotSpotId(Integer[] hotSpotId) {
        this.hotSpotId = hotSpotId;
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
     * @return fileURL
     */
    public String getFileURL() {
        return fileURL;
    }

    /**
     * 设置
     * @param fileURL
     */
    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
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
     * @return dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * 设置
     * @param dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取
     * @return topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * 设置
     * @param topicName
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * 获取
     * @return hotSpotNameSingle
     */
    public String getHotSpotNameSingle() {
        return hotSpotNameSingle;
    }

    /**
     * 设置
     * @param hotSpotNameSingle
     */
    public void setHotSpotNameSingle(String hotSpotNameSingle) {
        this.hotSpotNameSingle = hotSpotNameSingle;
    }

    /**
     * 获取
     * @return hotSpotName
     */
    public String[] getHotSpotName() {
        return hotSpotName;
    }

    /**
     * 设置
     * @param hotSpotName
     */
    public void setHotSpotName(String[] hotSpotName) {
        this.hotSpotName = hotSpotName;
    }

    /**
     * 获取
     * @return hotSpotStartTime
     */
    public String[] getHotSpotStartTime() {
        return hotSpotStartTime;
    }

    /**
     * 设置
     * @param hotSpotStartTime
     */
    public void setHotSpotStartTime(String[] hotSpotStartTime) {
        this.hotSpotStartTime = hotSpotStartTime;
    }

    /**
     * 获取
     * @return hotSpotEndTime
     */
    public String[] getHotSpotEndTime() {
        return hotSpotEndTime;
    }

    /**
     * 设置
     * @param hotSpotEndTime
     */
    public void setHotSpotEndTime(String[] hotSpotEndTime) {
        this.hotSpotEndTime = hotSpotEndTime;
    }

    /**
     * 获取
     * @return picUrl
     */
    public String[] getPicUrl() {
        return picUrl;
    }

    /**
     * 设置
     * @param picUrl
     */
    public void setPicUrl(String[] picUrl) {
        this.picUrl = picUrl;
    }

    public String toString() {
        return "CleanInfo{summerSchoolId = " + summerSchoolId + ", fileURL = " + fileURL + ", numDay = " + numDay + ", dateTime = " + dateTime + ", topicName = " + topicName + ", hotSpotNameSingle = " + hotSpotNameSingle + ", hotSpotName = " + hotSpotName + ", hotSpotStartTime = " + hotSpotStartTime + ", hotSpotEndTime = " + hotSpotEndTime + ", picUrl = " + picUrl + "}";
    }
}
