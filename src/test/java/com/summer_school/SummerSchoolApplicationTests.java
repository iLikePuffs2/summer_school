package com.summer_school;

import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.service.data_cleaning.FillInDataService;
import com.summer_school.service.data_cleaning.FormCleaningService;
import com.summer_school.service.data_cleaning.impl.ParticipationFormImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;
import java.util.Date;

//    @Qualifier("participationDetailFormImpl")
//    @Qualifier("signInFormImpl")

@SpringBootTest
@EnableTransactionManagement
class SummerSchoolApplicationTests {

    @Qualifier("participationFormImpl")
//    @Qualifier("signInFormImpl")
//    @Qualifier("signUpFormImpl")
    @Autowired
    FormCleaningService formCleaningService;

    @Autowired
    FillInDataService fillInDataService;

    @Test
    void test() {
        try {
            CleanInfo cleanInfo = new CleanInfo(1,"D:\\Idea project\\summer_school\\src\\main\\java\\com\\summer_school\\file\\ParticipationDetail_13.xlsx",1, new Integer[]{1, 2, 3, 4});
            formCleaningService.execute(cleanInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



//        fillInDataService.save(null);
    }

}
