package com.summer_school.service.analyze;

/**
 * 暂时只有热度算法
 */
public interface FixedAnalysisService {
    /**
     * 选择按主题呈现
     */
    void showTopic();

    /**
     * 选择按研究热点呈现
     */
    void showHotSpot();
}
