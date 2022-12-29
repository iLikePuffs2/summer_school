package com.summer_school.mapper;

import java.util.List;

public interface HotSpotMapper {

    //指定研究热点,查符合条件学生群体的积极性均值
    Integer countHotSpotActiveGroupAvg(Integer researchHotSpotId, List<Integer> studentIdList);

    //指定研究热点,查符合条件学生群体的学习投入度均值
    Integer countHotSpotCommitGroupAvg(Integer researchHotSpotId, List<Integer> studentIdList);

    //指定研究热点,查符合条件学生群体的有效参会时长均值
    Integer countHotSpotEffectiveLengthAvg(Integer researchHotSpotId, List<Integer> studentIdList);

    //指定研究热点,查符合条件学生群体的有效参与人数(出去再除以符合条件的总人数，得到课率）
    Integer countSumEffectiveNum(Integer researchHotSpotId, List<Integer> studentIdList);
}
