package com.summer_school;

import com.summer_school.service.signup_and_examine.UserSignInService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummerSchoolApplicationTests {

    @Autowired
    private UserSignInService userSignInService;
    @Test
    void testSignIn() {
        System.out.println(userSignInService.signIn("admin","a123456"));
    }

}
