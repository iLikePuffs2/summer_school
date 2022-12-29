package com.summer_school.controller.analyze.heat;

import com.summer_school.controller.tool.Result;
import com.summer_school.service.analyze.HeatAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/analyze/heat/hotspot")
public class AnalyzeHeatHotSpotController {

    @Qualifier("heatAnalysisHotSpotImpl")
    @Autowired
    HeatAnalysisService heatAnalysisService;

    @GetMapping
    public Result show(){
        Result result = new Result(true,heatAnalysisService.show());
        return result;
    }


}