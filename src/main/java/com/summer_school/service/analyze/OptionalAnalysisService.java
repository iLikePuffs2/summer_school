package com.summer_school.service.analyze;

/**
 * 签到统计——反应报名学生的签到情况
 * 积极性分析——反应报名学生参与的积极情况
 * 缺课统计——反应报名学生的缺课情况
 * 有效参会时长分析——反应报名学生的平均有效参与时长
 * 学习投入度分析——反应报名学生在参与过程中的认真程度
 */
public interface OptionalAnalysisService {

    /**
     * 选择学生可变(学生群体为横坐标)
     */
    void studentVariable();

    /**
     * 选择天数/主题/研究热点可变(2者中的一个为横坐标)
     */
    void topicVariable();

    /**
     * 选择研究热点可变(研究热点名字为横坐标)
     */
    void hotSpotVariable();
}
