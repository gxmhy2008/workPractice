package com.gxm.workPractice.service.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gxm.workPractice.service.entity.Test;
import com.gxm.workPractice.service.mapper.TestDB1Mapper;
import com.gxm.workPractice.service.service.ITestDB2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TestDB2ServiceImpl extends ServiceImpl<TestDB1Mapper, Test> implements ITestDB2Service {

    @Override
    @Transactional
    public List<Test> queryEntity() {
        return baseMapper.queryEntity();
    }
}
