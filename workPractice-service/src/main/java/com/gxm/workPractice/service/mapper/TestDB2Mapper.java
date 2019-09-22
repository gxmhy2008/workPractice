package com.gxm.workPractice.service.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gxm.workPractice.common.db.DataSource;
import com.gxm.workPractice.service.entity.Test;

import java.util.List;


public interface TestDB2Mapper extends BaseMapper<Test> {
    @DataSource("testDB2")
    List<Test> queryEntity();
}
