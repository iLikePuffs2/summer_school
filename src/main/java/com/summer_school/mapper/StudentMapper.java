package com.summer_school.mapper;

import com.summer_school.pojo.po.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> selectByDifferent(Student student);

    List<Integer> selectBySolidDifferent(Student student);
}
