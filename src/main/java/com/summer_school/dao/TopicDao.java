package com.summer_school.dao;

import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.Topic;
import com.summer_school.pojo.po.TopicAttendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TopicDao {

    /**
     * 主题编号 Integer id
     * 日期（主题开始的日期，具体到天数，目前暂定一天一个主题） String date
     * 主题名字 String name
     * 所属暑期学校id Integer summerSchoolId
     * 第几天（用户可能需要输入从第几天到第几天的学生学习投入度的变化情况，该属性用于此需求） Integer numDay
     */


    /**
     * 根据暑期学校id和主题名字找到主题id
     * @param name
     * @param summerSchoolId
     * @return
     */
    @Select("select id from topic_table where name = #{name} and summerSchoolId = #{summerSchoolId}")
    public int selectId(String name,int summerSchoolId);


    /**
     * 新增主题数据
     */
    @Insert("insert into topic_table (date, name, summerSchoolId, numDay) VALUES (#{date},#{name},#{summerSchoolId},#{numDay})")
    public int add(Topic topic);


    /**
     * 主题参与表
     * 分析参会详情表时插入数据
     * @param topicAttendance
     * @return
     */
    @Insert("insert into topic_attendance_table (topicId, studentId, effectiveAttendanceTime, activeScore, commitmentIndex, signInScore) " +
            "VALUES (#{topicId},#{studentId},#{effectiveAttendanceTime},#{activeScore},#{commitmentIndex},#{signInScore})")
    public int addTopicAttendance(TopicAttendance topicAttendance);

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
    public int addTopicById(int id,int participantNum,double activeRate);

    @Select("select * from topic_attendance_table where topicId=#{topicId} and studentId=#{studentId}")
    public TopicAttendance selectByTopicIdAndStudentId(int topicId,int studentId);
}
