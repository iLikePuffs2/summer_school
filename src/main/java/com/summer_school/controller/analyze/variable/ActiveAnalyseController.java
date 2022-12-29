package com.summer_school.controller.analyze.variable;

import com.summer_school.controller.tool.Result;
import com.summer_school.service.analyze.OptionalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze/variable/active")
public class ActiveAnalyseController {

    @Qualifier("activeAnalyseImpl")
    @Autowired
    OptionalAnalysisService optionalAnalysisService;

    @GetMapping("/{choice}")
    public Result show(@PathVariable int choice){
/*
        Result result = new Result();
        switch (choice){
            //性别比例
            case 1:
                result.setSuccess(true);
                result.setData(optionalAnalysisService.solidHotSpot());
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
        return result;*/
        return null;
    }
}
