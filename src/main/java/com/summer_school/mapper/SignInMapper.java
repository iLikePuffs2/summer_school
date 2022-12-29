package com.summer_school.mapper;

import com.summer_school.pojo.po.Student;

import java.util.List;

public interface SignInMapper {

    Integer countGroupAvg(Integer researchHotSpotId,List<Integer> studentIdList);

}
