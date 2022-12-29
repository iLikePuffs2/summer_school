package com.summer_school.controller.analyze.variable;

import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.dto.into.FreeStudentIntoDto;
import com.summer_school.pojo.dto.into.SolidStudentIntoDto;
import com.summer_school.service.analyze.OptionalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze/variable/effective")
public class EffectiveLengthAnalyseController {

    @Qualifier("effectiveLengthAnalyseImpl")
    @Autowired
    OptionalAnalysisService optionalAnalysisService;

    @PostMapping
    public Result show(@RequestBody FreeStudentIntoDto freeStu){
        Result result = new Result();


        SolidStudentIntoDto solidStu = new SolidStudentIntoDto(freeStu.getStudentSolidNum(), freeStu.getStudentSolidValue());
        switch (freeStu.getChoice()){
            //指定研究热点,学生群体可变
            case 1:
                result.setSuccess(true);
                result.setData(optionalAnalysisService.solidHotSpot(freeStu));
                break;
            //指定主题,学生群体可变
            case 2:
                result.setSuccess(true);
                result.setData((optionalAnalysisService.solidTopic(freeStu)));
                break;
            //指定天数,学生群体可变
            case 3:
                result.setSuccess(true);
                result.setData((optionalAnalysisService.solidDay(freeStu)));
                break;
            //指定学生群体,研究热点可变
            case 4:
                result.setSuccess(true);
                result.setData((optionalAnalysisService.variableHotSpot(solidStu)));
                break;
            //指定学生群体,主题可变
            case 5:
                result.setSuccess(true);
                result.setData(optionalAnalysisService.variableTopic(solidStu));
                break;

            //指定学生群体,天数可变
            case 6:
                result.setSuccess(true);
                result.setData(optionalAnalysisService.variableDay(solidStu));
                break;


            default:
                break;
        }
        return result;
    }
}
