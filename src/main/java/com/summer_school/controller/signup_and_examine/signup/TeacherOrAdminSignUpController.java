package com.summer_school.controller.signup_and_examine.signup;

import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.po.SignUpAdminTeacher;
import com.summer_school.service.signup_and_examine.TeacherOrAdminSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/teacher/signup")
public class TeacherOrAdminSignUpController {

    @Qualifier("signUpServiceImplAdmin")
    @Autowired
    TeacherOrAdminSignUpService signUpServiceImplAdmin;

    @Qualifier("signUpServiceImplTeacher")
    @Autowired
    TeacherOrAdminSignUpService signUpServiceImplTeacher;



    /**
     * 在老师和教务管理员注册表中插入一条数据
     * @param signUpAdminTeacher
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody SignUpAdminTeacher signUpAdminTeacher){
        boolean flag = false;

        if (signUpAdminTeacher.getIdentity().equals("教务管理员")){
            flag = signUpServiceImplAdmin.signup(signUpAdminTeacher);
        } else {
            flag = signUpServiceImplTeacher.signup(signUpAdminTeacher);
        }
        return new Result(flag);
    }



}
