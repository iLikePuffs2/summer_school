package com.summer_school.controller.signup_and_examine.create_and_relate;

import com.summer_school.controller.tool.Code;
import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.summer_school.SummerSchool;
import com.summer_school.service.signup_and_examine.CreateSummerSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup_and_examine/create_and_relate/InitSummerSchoolController")
public class InitSummerSchoolController {

    @Autowired
    private CreateSummerSchoolService createSummerSchoolService;

    /**
     * 输入暑期学校名字和年份，生成暑期学校(插入1条数据)
     * @param summerSchool
     * @return
     */
    @PostMapping
    public Result addSummerSchool(@RequestBody SummerSchool summerSchool){
        boolean flag = createSummerSchoolService.createSummerSchool(summerSchool);
        return new Result(flag ? Code.INSERT_OK : Code.INSERT_ERR,null);
    }



}
