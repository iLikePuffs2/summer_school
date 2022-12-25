package com.summer_school.pojo.po;

public class HotSpot {
    /**
     * 研究热点编号
     * 研究热点名称
     * 研究热点所对应的主题的i
     * 研究热点开始时间
     * 研究热点结束时间
     * 会议图片（图片的url）
     */
    private int researchHotSpotId;
    private String name;
    private int topicId;
    private String startTime;
    private String endTime;
    private String meetingImg;


    public HotSpot() {
    }

    public HotSpot(int researchHotSpotId, String name, int topicId, String startTime, String endTime, String meetingImg) {
        this.researchHotSpotId = researchHotSpotId;
        this.name = name;
        this.topicId = topicId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingImg = meetingImg;
    }


    
    /**
     * 获取
     * @return researchHotSpotId
     */
    public int getResearchHotSpotId() {
        return researchHotSpotId;
    }

    /**
     * 设置
     * @param researchHotSpotId
     */
    public void setResearchHotSpotId(int researchHotSpotId) {
        this.researchHotSpotId = researchHotSpotId;
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
     * @return topicId
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * 设置
     * @param topicId
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取
     * @return meetingImg
     */
    public String getMeetingImg() {
        return meetingImg;
    }

    /**
     * 设置
     * @param meetingImg
     */
    public void setMeetingImg(String meetingImg) {
        this.meetingImg = meetingImg;
    }

    public String toString() {
        return "HotSpot{researchHotSpotId = " + researchHotSpotId + ", name = " + name + ", topicId = " + topicId + ", startTime = " + startTime + ", endTime = " + endTime + ", meetingImg = " + meetingImg + "}";
    }
}
