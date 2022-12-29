package com.summer_school.service.analyze;

import com.summer_school.pojo.dto.TopicDto;

import java.util.List;

/**
 * 暂时只有热度算法
 */
public interface HeatAnalysisService {

    //按主题/研究热点呈现
    List<TopicDto> show();

}
