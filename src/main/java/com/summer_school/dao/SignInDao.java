package com.summer_school.dao;

import com.summer_school.pojo.po.HotSpotSignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignInDao {

    /**
     * researchHotSpotId int null comment '研究热点编号',
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
}
