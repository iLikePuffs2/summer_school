package com.summer_school.controller.data_cleaning;


import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clean/signup")
public class SignUpFormController {

    @Qualifier("signUpFormImpl")
    @Autowired
    FormCleaningService formCleaningService;


    @PostMapping
    public Result cleanSignUp(@RequestBody CleanInfo cleanInfo){
        boolean flag = formCleaningService.execute(cleanInfo);
        return new Result(flag);
    }
}
