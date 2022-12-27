package com.summer_school.pojo.po;

public class TopicAttendance {

    /**
     * 主题编号
     * 学生id
     * 有效参会时长
     * 积极性评分
     * 学习投入度
     * 签到评分
     */
    private int topicId;
    private int studentId;
    private int effectiveAttendanceTime;
    private int activeScore;
    private int commitmentIndex;
    private int signInScore;


    public TopicAttendance() {
    }

    public TopicAttendance(int topicId, int studentId, int effectiveAttendanceTime, int activeScore, int commitmentIndex, int signInScore) {
        this.topicId = topicId;
        this.studentId = studentId;
        this.effectiveAttendanceTime = effectiveAttendanceTime;
        this.activeScore = activeScore;
        this.commitmentIndex = commitmentIndex;
        this.signInScore = signInScore;
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
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * 设置
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取
     * @return effectiveAttendanceTime
     */
    public int getEffectiveAttendanceTime() {
        return effectiveAttendanceTime;
    }

    /**
     * 设置
     * @param effectiveAttendanceTime
     */
    public void setEffectiveAttendanceTime(int effectiveAttendanceTime) {
        this.effectiveAttendanceTime = effectiveAttendanceTime;
    }

    /**
     * 获取
     * @return activeScore
     */
    public int getActiveScore() {
        return activeScore;
    }

    /**
     * 设置
     * @param activeScore
     */
    public void setActiveScore(int activeScore) {
        this.activeScore = activeScore;
    }

    /**
     * 获取
     * @return commitmentIndex
     */
    public int getCommitmentIndex() {
        return commitmentIndex;
    }

    /**
     * 设置
     * @param commitmentIndex
     */
    public void setCommitmentIndex(int commitmentIndex) {
        this.commitmentIndex = commitmentIndex;
    }

    /**
     * 获取
     * @return signInScore
     */
    public int getSignInScore() {
        return signInScore;
    }

    /**
     * 设置
     * @param signInScore
     */
    public void setSignInScore(int signInScore) {
        this.signInScore = signInScore;
    }

    public String toString() {
        return "TopicAttendance{topicId = " + topicId + ", studentId = " + studentId + ", effectiveAttendanceTime = " + effectiveAttendanceTime + ", activeScore = " + activeScore + ", commitmentIndex = " + commitmentIndex + ", signInScore = " + signInScore + "}";
    }
}
