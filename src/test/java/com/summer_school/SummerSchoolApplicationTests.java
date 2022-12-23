package com.summer_school;

import com.summer_school.dao.EducationalAdministartorDao;
import com.summer_school.pojo.po.SignUpAdminTeacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummerSchoolApplicationTests {

    @Autowired
    private EducationalAdministartorDao educationalAdministartorDao;
    @Test
    void testSignIn() {
        SignUpAdminTeacher signUpAdminTeacher = new SignUpAdminTeacher();
        signUpAdminTeacher.setYear(2022);
        educationalAdministartorDao.signup(signUpAdminTeacher);
    }

}
