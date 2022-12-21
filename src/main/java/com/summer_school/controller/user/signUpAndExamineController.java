package com.summer_school.controller.user;

import com.summer_school.controller.tool.Code;
import com.summer_school.controller.tool.Result;
import com.summer_school.service.signup_and_examine.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signUpAndExamine")
public class signUpAndExamineController {

    @Autowired
    private UserSignInService userSignInService;

    //接收用户输入的账号和密码,返回查询结果和身份
    @GetMapping("/{account}/{password}")
    public Result signIn(@PathVariable String account, @PathVariable String password){
        String identity = userSignInService.signIn(account,password);
        System.out.println(identity);
        return new Result(identity != null ? Code.GET_OK : Code.GET_ERR,identity);
    }

}
