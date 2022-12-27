package com.summer_school.pojo.dto;

import java.util.Date;

public class TopicHelper {

    private Integer id;
    private String name;
    private String schoolName;
    private Integer studentId;
    private Date enterTime;
    private Date quitTime;
    private Integer onceLength;
    private Integer[] eachLength;
    private Integer sumLength;
    private Integer activeScore;
    private Integer activeNumber;
    private Integer signInScore;
    private Integer commitment;


    public TopicHelper() {
    }

    public TopicHelper(Integer id, String name, String schoolName, Integer studentId, Date enterTime, Date quitTime, Integer onceLength, Integer[] eachLength, Integer sumLength, Integer activeScore, Integer activeNumber, Integer signInScore, Integer commitment) {
        this.id = id;
        this.name = name;
        this.schoolName = schoolName;
        this.studentId = studentId;
        this.enterTime = enterTime;
        this.quitTime = quitTime;
        this.onceLength = onceLength;
        this.eachLength = eachLength;
        this.sumLength = sumLength;
        this.activeScore = activeScore;
        this.activeNumber = activeNumber;
        this.signInScore = signInScore;
        this.commitment = commitment;
    }

    public TopicHelper(String name, String schoolName, Integer studentId, Date enterTime, Date quitTime, Integer onceLength) {
        this.name = name;
        this.schoolName = schoolName;
        this.studentId = studentId;
        this.enterTime = enterTime;
        this.quitTime = quitTime;
        this.onceLength = onceLength;


        this.id = 0;
        this.eachLength = eachLength;
        this.sumLength = sumLength;
        this.activeScore = activeScore;
        this.activeNumber = activeNumber;
        this.signInScore = signInScore;
        this.commitment = commitment;
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
     * @return schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置
     * @param schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取
     * @return studentId
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置
     * @param studentId
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取
     * @return enterTime
     */
    public Date getEnterTime() {
        return enterTime;
    }

    /**
     * 设置
     * @param enterTime
     */
    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    /**
     * 获取
     * @return quitTime
     */
    public Date getQuitTime() {
        return quitTime;
    }

    /**
     * 设置
     * @param quitTime
     */
    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    /**
     * 获取
     * @return onceLength
     */
    public Integer getOnceLength() {
        return onceLength;
    }

    /**
     * 设置
     * @param onceLength
     */
    public void setOnceLength(Integer onceLength) {
        this.onceLength = onceLength;
    }

    /**
     * 获取
     * @return eachLength
     */
    public Integer[] getEachLength() {
        return eachLength;
    }

    /**
     * 设置
     * @param eachLength
     */
    public void setEachLength(Integer[] eachLength) {
        this.eachLength = eachLength;
    }

    /**
     * 获取
     * @return sumLength
     */
    public Integer getSumLength() {
        return sumLength;
    }

    /**
     * 设置
     * @param sumLength
     */
    public void setSumLength(Integer sumLength) {
        this.sumLength = sumLength;
    }

    /**
     * 获取
     * @return activeScore
     */
    public Integer getActiveScore() {
        return activeScore;
    }

    /**
     * 设置
     * @param activeScore
     */
    public void setActiveScore(Integer activeScore) {
        this.activeScore = activeScore;
    }

    /**
     * 获取
     * @return activeNumber
     */
    public Integer getActiveNumber() {
        return activeNumber;
    }

    /**
     * 设置
     * @param activeNumber
     */
    public void setActiveNumber(Integer activeNumber) {
        this.activeNumber = activeNumber;
    }

    /**
     * 获取
     * @return signInScore
     */
    public Integer getSignInScore() {
        return signInScore;
    }

    /**
     * 设置
     * @param signInScore
     */
    public void setSignInScore(Integer signInScore) {
        this.signInScore = signInScore;
    }

    /**
     * 获取
     * @return commitment
     */
    public Integer getCommitment() {
        return commitment;
    }

    /**
     * 设置
     * @param commitment
     */
    public void setCommitment(Integer commitment) {
        this.commitment = commitment;
    }

    public String toString() {
        return "TopicHelper{id = " + id + ", name = " + name + ", schoolName = " + schoolName + ", studentId = " + studentId + ", enterTime = " + enterTime + ", quitTime = " + quitTime + ", onceLength = " + onceLength + ", eachLength = " + eachLength + ", sumLength = " + sumLength + ", activeScore = " + activeScore + ", activeNumber = " + activeNumber + ", signInScore = " + signInScore + ", commitment = " + commitment + "}";
    }
}
