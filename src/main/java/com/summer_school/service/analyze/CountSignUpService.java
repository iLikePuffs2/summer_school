package com.summer_school.service.analyze;

import com.summer_school.pojo.dto.CountSignUp;

/**
 * 统计报名表数据
 */
public interface CountSignUpService {

    /**
     * 计算学生表性别比例
     * @return 男生比例+女生比例
     */
    CountSignUp gender();

    /**
     *学生类别
     */
    CountSignUp studentType();
    /**
     *就读年级
     */
    CountSignUp grade();
    /**
     *学校层次
     */
    CountSignUp schoolLevel();
    /**
     *专业名称
     */
    CountSignUp profession();
    /**
     *政治面貌
     */
    CountSignUp politicalStatus();
}
