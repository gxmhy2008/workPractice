package com.gxm.workPractice.cms.util.db;

import com.gxm.workPractice.common.db.DataSource;

import java.lang.reflect.Method;

public class DBAspect {
    public void before() {
        DatabaseContextHolder.setCustomerType("testDB2");
/*        if(method.isAnnotationPresent(DataSource.class)){
            DataSource dataSource = method.getAnnotation(DataSource.class);
            DatabaseContextHolder.setCustomerType(dataSource.value());
        }*/
    }

    public void after() {

    }
}
