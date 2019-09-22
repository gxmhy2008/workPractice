package com.gxm.workPractice.service.service;

import com.baomidou.mybatisplus.service.IService;
import com.gxm.workPractice.service.entity.Test;

import java.util.List;


public interface ITestDB2Service extends IService<Test> {

    List<Test> queryEntity();
}
