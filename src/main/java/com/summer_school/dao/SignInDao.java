package com.summer_school.dao;

import com.summer_school.pojo.po.HotSpotSignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SignInDao {

    /**
     * researchHotSpotId int null comment '研究热点id',
     * studentId         int null comment '学生id',
     * signInScore       int null comment '签到评分',
     */

    /**
     * 向签到表里插入数据
     * @param hotSpotSignIn
     * @return
     */
    @Insert("insert into sign_in_table(researchHotSpotId, studentId, signInScore) values (#{researchHotSpotId},#{studentId},#{signInScore})")
    public int addSignIn(HotSpotSignIn hotSpotSignIn);

    /**
     * 查询签到表里的全部数据
     */
    @Select("select * from sign_in_table")
    public List<HotSpotSignIn> selectAll();

    /**
     * 查询签到表里的全部研究热点编号（不重复）
     */
    @Select("select distinct researchHotSpotId from sign_in_table")
    public List<Integer> selectHotSpotId();

    /**
     * 用1个研究热点id和1个学生id找出这个学生的签到评分
     */
    @Select("select signInScore from sign_in_table where researchHotSpotId = #{researchHotSpotId} and studentId = #{studentId}")
    public Integer selectSignInScore(Integer researchHotSpotId,Integer studentId);


//    /**
//     * 用1个研究热点id和多个个学生id找出这个学生群体的签到评分的平均值
//     */
//    @Select("select AVG(signInScore) from sign_in_table where researchHotSpotId = #{HotSpotId} and studentId in (226,58,48,139)")
//    public Integer selectGroupSignInScore(Integer researchHotSpotId,Integer[] studentIdArr);


}
