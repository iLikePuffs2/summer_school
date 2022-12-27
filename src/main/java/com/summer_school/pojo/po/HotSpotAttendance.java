package com.summer_school.pojo.po;

public class HotSpotAttendance {
    /**
     * 研究热点编号
     * 学生id
     * 有效参会总时长
     * 积极性评分
     * 学习投入度
     */

    private Integer researchHotSpotId;
    private Integer studentId;
    private Integer effectiveAttendanceTime;
    private Integer activeScore;
    private Integer commitmentIndex;


    public HotSpotAttendance() {
    }

    public HotSpotAttendance(Integer researchHotSpotId, Integer studentId, Integer effectiveAttendanceTime, Integer activeScore, Integer commitmentIndex) {
        this.researchHotSpotId = researchHotSpotId;
        this.studentId = studentId;
        this.effectiveAttendanceTime = effectiveAttendanceTime;
        this.activeScore = activeScore;
        this.commitmentIndex = commitmentIndex;
    }

    /**
     * 获取
     * @return researchHotSpotId
     */
    public Integer getResearchHotSpotId() {
        return researchHotSpotId;
    }

    /**
     * 设置
     * @param researchHotSpotId
     */
    public void setResearchHotSpotId(Integer researchHotSpotId) {
        this.researchHotSpotId = researchHotSpotId;
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
     * @return effectiveAttendanceTime
     */
    public Integer getEffectiveAttendanceTime() {
        return effectiveAttendanceTime;
    }

    /**
     * 设置
     * @param effectiveAttendanceTime
     */
    public void setEffectiveAttendanceTime(Integer effectiveAttendanceTime) {
        this.effectiveAttendanceTime = effectiveAttendanceTime;
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
     * @return commitmentIndex
     */
    public Integer getCommitmentIndex() {
        return commitmentIndex;
    }

    /**
     * 设置
     * @param commitmentIndex
     */
    public void setCommitmentIndex(Integer commitmentIndex) {
        this.commitmentIndex = commitmentIndex;
    }

    public String toString() {
        return "HotSpotAttendance{researchHotSpotId = " + researchHotSpotId + ", studentId = " + studentId + ", effectiveAttendanceTime = " + effectiveAttendanceTime + ", activeScore = " + activeScore + ", commitmentIndex = " + commitmentIndex + "}";
    }
}
