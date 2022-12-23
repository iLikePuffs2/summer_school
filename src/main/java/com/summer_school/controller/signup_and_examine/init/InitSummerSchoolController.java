package com.summer_school.controller.signup_and_examine.init;

import com.alibaba.fastjson.JSON;
import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.dto.SummerSchool;
import com.summer_school.service.signup_and_examine.CreateSummerSchoolService;
import com.summer_school.service.signup_and_examine.SelectSummerSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school/init")
public class InitSummerSchoolController {

    @Autowired
    private CreateSummerSchoolService createSummerSchoolService;

    @Autowired
    SelectSummerSchoolService selectSummerSchoolService;

    //查询所有暑期学校名字和年份
    @GetMapping
    public Result selectAll() {
        //String summerSchool = JSON.toJSONString(selectSummerSchoolService.selectSummerSchool());
        List<SummerSchool> summerSchool = selectSummerSchoolService.selectSummerSchool();
        return new Result(summerSchool != null ? true : false, summerSchool);
    }

    /**
     * 输入暑期学校名字和年份，生成暑期学校(插入1条数据)
     *
     * @param summerSchool
     * @return
     */
    @PostMapping
    public Result addSummerSchool(@RequestBody SummerSchool summerSchool) {
        boolean flag = createSummerSchoolService.createSummerSchool(summerSchool);
        return new Result(flag, null);
    }


}
