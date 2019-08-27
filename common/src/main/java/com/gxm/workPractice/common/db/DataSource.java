package com.gxm.workPractice.common.db;

import java.lang.annotation.*;

/**
 * 功能描述：定义注解，用于切换数据源
 * @author gxm
 * @since 2019/08/26
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value();
}
