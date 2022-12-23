package com.summer_school.controller.signup_and_examine;

import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.service.signup_and_examine.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/signup")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    /**
     * 在对应的注册表中插入一条数据
     * @param signUpInfo
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody SignUpInfo signUpInfo){
        boolean flag = signUpService.signup(signUpInfo);
        return new Result(flag);
    }



}
