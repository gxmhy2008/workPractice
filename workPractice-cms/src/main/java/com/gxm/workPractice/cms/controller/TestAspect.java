package com.gxm.workPractice.cms.controller;

import com.gxm.workPractice.service.entity.Test;
import com.gxm.workPractice.service.service.ITestDB1Service;
import com.gxm.workPractice.service.service.ITestDB2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class TestAspect {

    @Autowired
    private ITestDB1Service testDB1Service;

    @Autowired
    private ITestDB2Service testDB2Service;

    @RequestMapping("testDb1")
    @ResponseBody
    public String testDB1() {
        List<Test>  tests = testDB1Service.queryEntity();
        if (tests != null && tests.size() > 0) {
            System.out.println(tests.get(0).getName());
            return tests.get(0).getName();
        }
        return "test db1 fail";
    }

    @RequestMapping("testDb2")
    @ResponseBody
    public String testDB2() {
        List<Test>  tests = testDB2Service.queryEntity();
        if (tests != null && tests.size() > 0) {
            System.out.println(tests.get(0).getName());
            return tests.get(0).getName();
        }
        return "test db2 fail";
    }
}
