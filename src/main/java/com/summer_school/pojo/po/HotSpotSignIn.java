package com.summer_school.pojo.po;

public class HotSpotSignIn {
    /**
     * 研究热点编号
     * 学生id
     * 签到评分
     */
    private Integer researchHotSpotId;
    private Integer studentId;
    private Integer signInScore;


    public HotSpotSignIn() {
    }

    public HotSpotSignIn(Integer researchHotSpotId, Integer studentId, Integer signInScore) {
        this.researchHotSpotId = researchHotSpotId;
        this.studentId = studentId;
        this.signInScore = signInScore;
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

    public String toString() {
        return "SignIn{researchHotSpotId = " + researchHotSpotId + ", studentId = " + studentId + ", signInScore = " + signInScore + "}";
    }
}
