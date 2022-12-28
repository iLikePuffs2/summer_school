package com.summer_school.controller.analyze.signup;

import com.summer_school.controller.tool.Result;
import com.summer_school.service.analyze.CountSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/analyze/signup")
public class AnalyzeSignUpController {

    @Autowired
    CountSignUpService countSignUpService;

    @GetMapping("/{choice}")
    public Result Count(@PathVariable int choice){

        Result result = new Result();
        switch (choice){
            //性别比例
            case 1:
                result.setSuccess(true);
                result.setData(countSignUpService.gender());
                break;
                //学生类别
            case 2:
                result.setSuccess(true);
                result.setData(countSignUpService.studentType());
                break;
                //就读年级
            case 3:
                result.setSuccess(true);
                result.setData(countSignUpService.grade());
                break;
                //学校层次
            case 4:
                result.setSuccess(true);
                result.setData(countSignUpService.schoolLevel());
                break;
            //政治面貌
            case 5:
                result.setSuccess(true);
                result.setData(countSignUpService.politicalStatus());
                break;

            //专业名称
            case 6:
                result.setSuccess(true);
                result.setData(countSignUpService.profession());
                break;


            default:
                break;
        }
        return result;
    }


}