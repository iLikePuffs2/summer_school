package com.summer_school.pojo.dto;

import java.util.Date;

public class ParticipationHelper {
    /**
     * 名字（不变）
     * 学校名称（不变）
     * 学生id（不变）
     * 进入时间（不变）
     * 退出时间（不变）
     * 单次参会时长（不变）（分钟计）
     * 许可范围内的单次参会时长（每次循环变）
     * 主题的累计有效参会时长（每次循环累加）
     * 是否为有效参会数据（不变）
     * 是否为许可范围内的有效参会数据（每次循环变）
     * 累计的许可范围内的有效参会时长是否达标（每次循环变）
     * 被统计的类型type（每次循环变）（int，初始0，每次会在循环2改变，在循环3被重新归0）（自己是主体就被标记1，自己被主体统计过就被标记2）
     * 积极性评分（每次循环变）
     * 主题的积极性评分（每次循环取均值）
     * 主题的累计积极参与次数（每次循环累加）
     * 签到得分（每次循环变）
     * 主题的签到评分（每次循环取均值）
     * 研究热点学习投入度（每次循环变）
     * 主题的学习投入度（每次循环取均值）
     */
    private String name;
    private String schoolName;
    private Integer studentId;
    private Date enterTime;
    private Date quitTime;
    private Integer onceLength;
    private Integer onceRestrictedLength;
    private Integer sumEffectiveLength;
    private boolean isAllEffective;
    private boolean isRestrictedEffective;
    private boolean cumulativeTimeQualified;
    private Integer type;
    private Integer activeScore;
    private Integer activeScoreTopic;
    private Integer activeSumTimesTopic;
    private Integer signInScore;
    private Integer signInScoreTopic;
    private Integer commitmentScore;
    private Integer commitmentScoreTopic;


    public ParticipationHelper() {
    }

    public ParticipationHelper(String name, String schoolName, Integer studentId, Date enterTime, Date quitTime, Integer onceLength, Integer onceRestrictedLength, Integer sumEffectiveLength, boolean isAllEffective, boolean isRestrictedEffective, boolean cumulativeTimeQualified, Integer type, Integer activeScore, Integer activeScoreTopic, Integer activeSumTimesTopic, Integer signInScore, Integer signInScoreTopic, Integer commitmentScore, Integer commitmentScoreTopic) {
        this.name = name;
        this.schoolName = schoolName;
        this.studentId = studentId;
        this.enterTime = enterTime;
        this.quitTime = quitTime;
        this.onceLength = onceLength;
        this.onceRestrictedLength = onceRestrictedLength;
        this.sumEffectiveLength = sumEffectiveLength;
        this.isAllEffective = isAllEffective;
        this.isRestrictedEffective = isRestrictedEffective;
        this.cumulativeTimeQualified = cumulativeTimeQualified;
        this.type = type;
        this.activeScore = activeScore;
        this.activeScoreTopic = activeScoreTopic;
        this.activeSumTimesTopic = activeSumTimesTopic;
        this.signInScore = signInScore;
        this.signInScoreTopic = signInScoreTopic;
        this.commitmentScore = commitmentScore;
        this.commitmentScoreTopic = commitmentScoreTopic;
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
     * @return onceRestrictedLength
     */
    public Integer getOnceRestrictedLength() {
        return onceRestrictedLength;
    }

    /**
     * 设置
     * @param onceRestrictedLength
     */
    public void setOnceRestrictedLength(Integer onceRestrictedLength) {
        this.onceRestrictedLength = onceRestrictedLength;
    }

    /**
     * 获取
     * @return sumEffectiveLength
     */
    public Integer getSumEffectiveLength() {
        return sumEffectiveLength;
    }

    /**
     * 设置
     * @param sumEffectiveLength
     */
    public void setSumEffectiveLength(Integer sumEffectiveLength) {
        this.sumEffectiveLength = sumEffectiveLength;
    }

    /**
     * 获取
     * @return isAllEffective
     */
    public boolean isIsAllEffective() {
        return isAllEffective;
    }

    /**
     * 设置
     * @param isAllEffective
     */
    public void setIsAllEffective(boolean isAllEffective) {
        this.isAllEffective = isAllEffective;
    }

    /**
     * 获取
     * @return isRestrictedEffective
     */
    public boolean isIsRestrictedEffective() {
        return isRestrictedEffective;
    }

    /**
     * 设置
     * @param isRestrictedEffective
     */
    public void setIsRestrictedEffective(boolean isRestrictedEffective) {
        this.isRestrictedEffective = isRestrictedEffective;
    }

    /**
     * 获取
     * @return cumulativeTimeQualified
     */
    public boolean isCumulativeTimeQualified() {
        return cumulativeTimeQualified;
    }

    /**
     * 设置
     * @param cumulativeTimeQualified
     */
    public void setCumulativeTimeQualified(boolean cumulativeTimeQualified) {
        this.cumulativeTimeQualified = cumulativeTimeQualified;
    }

    /**
     * 获取
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return activeScoreTopic
     */
    public Integer getActiveScoreTopic() {
        return activeScoreTopic;
    }

    /**
     * 设置
     * @param activeScoreTopic
     */
    public void setActiveScoreTopic(Integer activeScoreTopic) {
        this.activeScoreTopic = activeScoreTopic;
    }

    /**
     * 获取
     * @return activeSumTimesTopic
     */
    public Integer getActiveSumTimesTopic() {
        return activeSumTimesTopic;
    }

    /**
     * 设置
     * @param activeSumTimesTopic
     */
    public void setActiveSumTimesTopic(Integer activeSumTimesTopic) {
        this.activeSumTimesTopic = activeSumTimesTopic;
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
     * @return signInScoreTopic
     */
    public Integer getSignInScoreTopic() {
        return signInScoreTopic;
    }

    /**
     * 设置
     * @param signInScoreTopic
     */
    public void setSignInScoreTopic(Integer signInScoreTopic) {
        this.signInScoreTopic = signInScoreTopic;
    }

    /**
     * 获取
     * @return commitmentScore
     */
    public Integer getCommitmentScore() {
        return commitmentScore;
    }

    /**
     * 设置
     * @param commitmentScore
     */
    public void setCommitmentScore(Integer commitmentScore) {
        this.commitmentScore = commitmentScore;
    }

    /**
     * 获取
     * @return commitmentScoreTopic
     */
    public Integer getCommitmentScoreTopic() {
        return commitmentScoreTopic;
    }

    /**
     * 设置
     * @param commitmentScoreTopic
     */
    public void setCommitmentScoreTopic(Integer commitmentScoreTopic) {
        this.commitmentScoreTopic = commitmentScoreTopic;
    }

    public String toString() {
        return "ParticipationHelper{name = " + name + ", schoolName = " + schoolName + ", studentId = " + studentId + ", enterTime = " + enterTime + ", quitTime = " + quitTime + ", onceLength = " + onceLength + ", onceRestrictedLength = " + onceRestrictedLength + ", sumEffectiveLength = " + sumEffectiveLength + ", isAllEffective = " + isAllEffective + ", isRestrictedEffective = " + isRestrictedEffective + ", cumulativeTimeQualified = " + cumulativeTimeQualified + ", type = " + type + ", activeScore = " + activeScore + ", activeScoreTopic = " + activeScoreTopic + ", activeSumTimesTopic = " + activeSumTimesTopic + ", signInScore = " + signInScore + ", signInScoreTopic = " + signInScoreTopic + ", commitmentScore = " + commitmentScore + ", commitmentScoreTopic = " + commitmentScoreTopic + "}";
    }
}
