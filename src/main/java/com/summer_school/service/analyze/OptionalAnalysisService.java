package com.summer_school.service.analyze;

import com.summer_school.pojo.dto.back.AnalyseBackDto;
import com.summer_school.pojo.dto.into.SolidStudentIntoDto;
import com.summer_school.pojo.dto.into.FreeStudentIntoDto;

/**
 * 签到统计——反应报名学生的签到情况
 * 积极性分析——反应报名学生参与的积极情况
 * 缺课统计——反应报名学生的缺课情况
 * 有效参会时长分析——反应报名学生的平均有效参与时长
 * 学习投入度分析——反应报名学生在参与过程中的认真程度
 *
 * 每个功能对应6种情况：
 */
public interface OptionalAnalysisService {


    /**
     * 指定研究热点,学生群体可变
     */
    AnalyseBackDto solidHotSpot(FreeStudentIntoDto freeInfo);

    /**
     * 指定主题,学生群体可变
     */
    AnalyseBackDto solidTopic(FreeStudentIntoDto freeInfo);

    /**
     * 指定天数,学生群体可变
     */
    AnalyseBackDto solidDay(FreeStudentIntoDto freeInfo);


    /**
     * 指定学生群体,研究热点可变
     */
    AnalyseBackDto variableHotSpot(SolidStudentIntoDto solidInfo);

    /**
     * 指定学生群体,主题可变
     */
    AnalyseBackDto variableTopic(SolidStudentIntoDto solidInfo);

    /**
     * 指定学生群体,天数可变
     */
    AnalyseBackDto variableDay(SolidStudentIntoDto solidInfo);


}
