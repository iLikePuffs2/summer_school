package com.summer_school.service.signup_and_examine;

import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpAdminTeacher;

/**
 * 此接口对应2个实现类（教务管理员、老师）
 */
public interface TeacherOrAdminSignUpService {
    public boolean signup(SignUpAdminTeacher signUpAdminTeacher);
}
