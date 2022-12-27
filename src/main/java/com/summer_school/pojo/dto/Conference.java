package com.summer_school.pojo.dto;

import java.util.Date;

public class Conference {
    private Date startTime;
    private Date endTime;
    private int length;


    public Conference() {
    }

    public Conference(Date startTime, Date endTime, int length) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.length = length;
    }

    /**
     * 获取
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * 设置
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }


}
