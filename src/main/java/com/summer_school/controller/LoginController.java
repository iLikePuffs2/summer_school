package com.summer_school.controller;

import com.summer_school.domain.user.SystemAdministrator;
import com.summer_school.service.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signIn")
public class LoginController {

    @Autowired
    private UserSignInService userSignInService;

    //接收用户输入的账号和密码,返回查询结果和身份
    @GetMapping("/{account}/{password}")
    public Result signIn(@PathVariable String account,@PathVariable String password){
        String identity = userSignInService.signIn(account,password);
        System.out.println(identity);
        return new Result(identity != null ? Code.GET_OK : Code.GET_ERR,identity);
    }

}
