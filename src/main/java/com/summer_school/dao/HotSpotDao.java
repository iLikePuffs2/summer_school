package com.summer_school.dao;

import com.summer_school.pojo.dto.SummerSchool;
import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.HotSpotSignIn;
import com.summer_school.pojo.po.SchoolLevelContrast;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotSpotDao {

    /**
     * 根据研究热点名字找到研究热点id
     * @return
     */
    @Select("select researchHotSpotId from research_hot_spot_table where name = #{hotSpotName}")
    public int selectIdByName(String hotSpotName);


    /**
     * 新增研究热点数据
     */
    @Insert("insert into research_hot_spot_table (name, topicId, startTime, endTime, meetingImg) VALUES (#{name},#{topicId},#{startTime},#{endTime},#{meetingImg})")
    public int add(HotSpot hotSpot);
}
