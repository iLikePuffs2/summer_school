package com.summer_school.dao;

import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
