package com.summer_school.mapper;

import java.util.List;

public interface TopicMapper {

    //指定主题,查符合条件学生群体的签到评分均值
    Integer countTopicSignInAvg(Integer topicId, List<Integer> studentIdList);

    //指定主题,查符合条件学生群体的积极性均值
    Integer countTopicActiveGroupAvg(Integer topicId, List<Integer> studentIdList);

    //指定主题,查符合条件学生群体的学习投入度均值
    Integer countTopicCommitGroupAvg(Integer topicId, List<Integer> studentIdList);

    //指定主题,查符合条件学生群体的有效参会时长均值
    Integer countTopicEffectiveLengthAvg(Integer topicId, List<Integer> studentIdList);

    //指定主题,查符合条件学生群体的有效参与人数(出去再除以符合条件的总人数，得到课率）
    Integer countSumEffectiveNum(Integer topicId, List<Integer> studentIdList);
}
