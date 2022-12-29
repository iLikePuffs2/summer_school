package com.summer_school.dao;

import com.summer_school.pojo.dto.TopicDto;
import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.Topic;
import com.summer_school.pojo.po.TopicAttendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TopicDao {


    /**
     * 查询全部
     * @return
     */
    @Select("select * from topic_table")
    public List<Topic> selectAll();


    @Select("Select name,participantNum,activeRate from topic_table order by participantNum DESC")
    public List<TopicDto> selectHeat();


    /**
     * 根据暑期学校id和主题名字找到主题id
     * @param name
     * @param summerSchoolId
     * @return
     */
    @Select("select id from topic_table where name = #{name} and summerSchoolId = #{summerSchoolId}")
    public Integer selectId(String name,int summerSchoolId);

    /**
     * 根据暑期学校id和第几天找到主题信息
     * @param numDay
     * @param summerSchoolId
     * @return
     */
    @Select("select * from topic_table where numDay = #{numDay} and summerSchoolId = #{summerSchoolId}")
    public Topic selectBySummerIdAndNumDay(Integer summerSchoolId,Integer numDay);

    /**
     * 新增主题数据
     */
    @Insert("insert into topic_table (date, name, summerSchoolId, numDay) VALUES (#{date},#{name},#{summerSchoolId},#{numDay})")
    public Integer add(Topic topic);


    /**
     * 主题参与表
     * 分析参会详情表时插入数据
     * @param topicAttendance
     * @return
     */
    @Insert("insert into topic_attendance_table (topicId, studentId, effectiveAttendanceTime, activeScore, commitmentIndex, signInScore) " +
            "VALUES (#{topicId},#{studentId},#{effectiveAttendanceTime},#{activeScore},#{commitmentIndex},#{signInScore})")
    public Integer addTopicAttendance(TopicAttendance topicAttendance);

    /**
     * 主题表
     * 分析参会详情表得出：
     * 参与主题的有效人数
     * 积极参与率
     * 根据主题id补上这2个
     * @param id
     * @return
     */
    @Update("update topic_table set participantNum = #{participantNum},activeRate=#{activeRate} where id=#{id}")
    public Integer addTopicById(int id,int participantNum,double activeRate);

    @Select("select * from topic_attendance_table where topicId=#{topicId} and studentId=#{studentId}")
    public TopicAttendance selectByTopicIdAndStudentId(int topicId,int studentId);
}
