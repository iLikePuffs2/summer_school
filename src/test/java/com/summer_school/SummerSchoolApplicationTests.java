package com.summer_school;

import com.summer_school.dao.user_sign_in.SystemAdministratorDao;
import com.summer_school.domain.user.AbstractUser;
import com.summer_school.domain.user.SystemAdministrator;
import com.summer_school.service.UserSignInService;
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
