package com.summer_school.controller.signup_and_examine.signup;

import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.po.SignUpStudent;
import com.summer_school.service.signup_and_examine.StudentSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/signup")
public class StudentSignUpController {

    @Autowired
    StudentSignUpService studentSignUpService;


    /**
     * 在学生注册表中插入一条数据
     * @param signUpStudent
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody SignUpStudent signUpStudent){
        boolean flag = studentSignUpService.signup(signUpStudent);
        return new Result(flag);
    }



}
