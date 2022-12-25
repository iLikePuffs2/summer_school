package com.summer_school.service.data_cleaning;

import com.summer_school.pojo.dto.CleanInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教务管理员填写一天的相关会议信息
 * 填写日期(后端自动生成)、第几天、主题名字、研究热点名字、研究热点会议时间、会议图片
 */
public interface FillInDataService {

    /**
     * 将数据存入数据库
     * @return
     */

    @Transactional
    boolean save(CleanInfo cleanInfo);

}
