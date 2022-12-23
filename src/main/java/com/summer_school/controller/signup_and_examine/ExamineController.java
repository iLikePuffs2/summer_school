package com.summer_school.controller.signup_and_examine;

import com.summer_school.controller.tool.Result;
import com.summer_school.pojo.dto.ExamineResultInfo;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.service.signup_and_examine.SignUpService;
import com.summer_school.service.signup_and_examine.UserExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school/examine")
public class ExamineController {

    @Autowired
    UserExamineService userExamineService;

    /**
     * 查询要审核的数据
     * @param examineResultInfo (审核者和被审核者的身份)
     * @return 显示要审核的数据
     */
    @GetMapping
    public Result select(@RequestBody ExamineResultInfo examineResultInfo){
        List<List> userList = userExamineService.showSignUpInfo(examineResultInfo);
        return new Result(userList != null ? true : false,userList);
    }

    /**
     *
     * @param examineResultInfo (被审核者的身份,被审核者的账号,审核结果)
     * @return 审核结果boolean
     */
    @PostMapping
    public Result examine(@RequestBody ExamineResultInfo examineResultInfo){
        boolean flag = userExamineService.examine(examineResultInfo);
        return new Result(flag);
    }
}
