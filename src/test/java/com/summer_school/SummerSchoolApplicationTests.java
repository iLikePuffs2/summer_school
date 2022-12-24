package com.summer_school;

import com.summer_school.pojo.dto.CleanSignUp;
import com.summer_school.service.data_cleaning.FormCleaningService;
import com.summer_school.service.data_cleaning.impl.ParticipationDetailFormImpl;
import com.summer_school.service.data_cleaning.impl.SignInFormImpl;
import com.summer_school.service.data_cleaning.impl.SignUpFormImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;

//    @Qualifier("participationDetailFormImpl")
//    @Qualifier("signInFormImpl")

@SpringBootTest
@EnableTransactionManagement
class SummerSchoolApplicationTests {

    @Qualifier("signUpFormImpl")
    @Autowired
    FormCleaningService formCleaningService;

    @Test
    void testSignUp() {
        try {
            CleanSignUp cleanSignUp = new CleanSignUp("D:\\Idea project\\summer_school\\src\\main\\java\\com\\summer_school\\file\\SignUp.csv", 1);
            formCleaningService.execute(cleanSignUp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
