package com.example.code.class10;


import com.geek.starter.MySchool;
import com.geek.starter.SchoolAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
*给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
*/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SchoolAutoConfiguration.class)
public class MySchoolAutoStarterlTest {

    @Autowired
    MySchool mySchool;

    @Test
    public void test() {
        System.out.println(mySchool.toString());
    }
}
