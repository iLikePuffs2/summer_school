package com.summer_school.dao;

import com.summer_school.pojo.dto.SummerSchool;
import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.HotSpotAttendance;
import com.summer_school.pojo.po.HotSpotSignIn;
import com.summer_school.pojo.po.SchoolLevelContrast;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface HotSpotDao {

    /**
     * 研究热点表
     * 根据研究热点名字找到研究热点id
     * @return
     */
    @Select("select researchHotSpotId from research_hot_spot_table where name = #{hotSpotName}")
    public int selectIdByName(String hotSpotName);

    /**
     * 研究热点表
     * 根据主题id,找到所有对应研究热点的开始和结束时间
     * @param topicID
     * @return
     */
    @Select("select startTime,endTime from research_hot_spot_table where topicID = #{topicID}")
    public List<HotSpot> selectTimeByTopicId(int topicID);

    /**
     * 研究热点表
     * 新增研究热点数据
     */
    @Insert("insert into research_hot_spot_table (name, topicId, startTime, endTime, meetingImg) VALUES (#{name},#{topicId},#{startTime},#{endTime},#{meetingImg})")
    public int add(HotSpot hotSpot);



    /**
     * 研究热点参与表
     * @param hotSpotAttendance
     * @return
     */
    @Insert("insert into research_hot_spot_attendance_table(researchHotSpotId, studentId, effectiveAttendanceTime, activeScore, commitmentIndex) VALUES (#{researchHotSpotId},#{studentId},#{effectiveAttendanceTime},#{activeScore},#{commitmentIndex})")
    public int addAttendanceInfo(HotSpotAttendance hotSpotAttendance);

    /**
     * 研究热点参与表
     * 根据研究热点id和学生id，找全部数据
     * @param researchHotSpotId
     * @param studentId
     * @return
     */
    @Select("select * from research_hot_spot_attendance_table where researchHotSpotId = #{researchHotSpotId} and studentId = #{studentId}")
    public HotSpotAttendance selectByHotSpotIdAndStudentId(Integer researchHotSpotId,Integer studentId);


    /**
     * 研究热点表
     * 根据研究热点改动有效人数和积极参与率
     * @param researchHotSpotId
     * @param participantNum
     * @param activeRate
     * @return
     */
    @Update("update research_hot_spot_table set participantNum = #{participantNum}, activeRate = #{activeRate} where researchHotSpotId = #{researchHotSpotId}")
    public int updateParticipation(Integer researchHotSpotId,Integer participantNum,double activeRate);


}
