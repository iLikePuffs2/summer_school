package com.summer_school.controller.data_cleaning;


import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.dto.CleanSignUp;
import com.summer_school.pojo.dto.ExamineResultInfo;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.service.data_cleaning.FormCleaningService;
import com.summer_school.service.signup_and_examine.SignUpService;
import com.summer_school.service.signup_and_examine.UserExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clean/signup")
public class SignUpFormController {

    @Qualifier("signUpFormImpl")
    @Autowired
    FormCleaningService formCleaningService;


    @PostMapping
    public Result cleanSignUp(@RequestBody CleanSignUp cleanSignUp){
        boolean flag = formCleaningService.execute(cleanSignUp);
        return new Result(flag);
    }
}
