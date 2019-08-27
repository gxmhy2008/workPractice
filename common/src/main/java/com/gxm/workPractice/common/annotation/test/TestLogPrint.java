package com.gxm.workPractice.common.annotation.test;

import org.springframework.stereotype.Component;

@Component
public class TestLogPrint {

    @LogAnnotation(desc = "通过测试查看切面注解是否成功")
    public String testLog() {
        System.out.println("test log print");
        return "OK";
    }
}
