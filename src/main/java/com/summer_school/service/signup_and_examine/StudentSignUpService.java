package com.summer_school.service.signup_and_examine;

import com.summer_school.pojo.po.SignUpAdminTeacher;
import com.summer_school.pojo.po.SignUpStudent;

/**
 * 学生注册
 */
public interface StudentSignUpService {
    public boolean signup(SignUpStudent signUpStudent);
}
