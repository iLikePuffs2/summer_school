package com.summer_school;

import com.summer_school.service.data_cleaning.FormCleaningService;
import com.summer_school.service.data_cleaning.impl.SignUpFormImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummerSchoolApplicationTests {

    @Qualifier("signUpFormImpl")
    @Autowired
    private FormCleaningService formCleaningService;

    @Test
    void testSignUp() {
        try {
            formCleaningService.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
