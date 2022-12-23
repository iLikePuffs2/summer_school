package com.summer_school.service.signup_and_examine;

import com.summer_school.pojo.dto.ExamineResultInfo;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserExamineService {
    /**
     * 显示要审核的数据
     * @param examineResultInfo
     * @return
     */
    public List<List> showSignUpInfo(ExamineResultInfo examineResultInfo);

    /**
     * 进行审核,告诉用户操作成功与否(事务保证insert和delete都执行）
     * @param examineResultInfo
     * @return
     */
    @Transactional
    public boolean examine(ExamineResultInfo examineResultInfo);
}
