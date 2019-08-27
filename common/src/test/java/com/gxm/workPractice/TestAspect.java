package com.gxm.workPractice;

import com.gxm.workPractice.common.annotation.test.TestLogPrint;
import org.junit.Test;

public class TestAspect {

    @Test
    public void test1() {
        TestLogPrint testLogPrint = new TestLogPrint();
        testLogPrint.testLog();
    }
}
