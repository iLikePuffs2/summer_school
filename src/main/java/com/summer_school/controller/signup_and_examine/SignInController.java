package com.summer_school.controller.signup_and_examine;

import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.dto.SignIn;
import com.summer_school.service.signup_and_examine.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/signin")
public class SignInController {

    @Autowired
    UserSignInService userSignInService;

    @PostMapping
    public Result signin(@RequestBody SignIn signIn){
        String identity = userSignInService.signIn(signIn);
        return new Result((userSignInService.signIn(signIn) != null) ? true : false,identity);
    }



}
