package com.summer_school.controller.signup_and_examine.create_and_relate;

import com.summer_school.controller.tool.Code;
import com.summer_school.controller.tool.Result;
import com.summer_school.domain.summer_school.SummerSchool;
import com.summer_school.service.signup_and_examine.CreateSummerSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup_and_examine/create_and_relate/CreateSummerSchoolController")
public class CreateSummerSchoolController {

    @Autowired
    private CreateSummerSchoolService createSummerSchoolService;

    @PostMapping
    public Result addSummerSchool(@RequestBody SummerSchool summerSchool){
        boolean flag = createSummerSchoolService.createSummerSchool(summerSchool);
        return new Result(flag ? Code.INSERT_OK : Code.INSERT_ERR,null);
    }
}
