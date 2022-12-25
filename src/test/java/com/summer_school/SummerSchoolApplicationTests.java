package com.summer_school;

import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.service.data_cleaning.FillInDataService;
import com.summer_school.service.data_cleaning.FormCleaningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//    @Qualifier("participationDetailFormImpl")
//    @Qualifier("signInFormImpl")

@SpringBootTest
@EnableTransactionManagement
class SummerSchoolApplicationTests {

    @Qualifier("signInFormImpl")
//    @Qualifier("signUpFormImpl")
    @Autowired
    FormCleaningService formCleaningService;

    @Autowired
    FillInDataService fillInDataService;

    @Test
    void testSignUp() {
/*        try {
            CleanInfo cleanInfo = new CleanInfo(1,"D:\\Idea project\\summer_school\\src\\main\\java\\com\\summer_school\\file\\SignIn.xlsx","北京邮电大学杜军平教授：科技大数据理论与技术");
            formCleaningService.execute(cleanInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/



        fillInDataService.save(null);
    }

}
