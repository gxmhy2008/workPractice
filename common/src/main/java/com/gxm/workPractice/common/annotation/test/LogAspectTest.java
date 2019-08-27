package com.gxm.workPractice.common.annotation.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect    //该注解标示该类为切面类
@Component
public class LogAspectTest {
    @AfterReturning("within(com.gxm.workPractice.common.annotation.test..*) && @annotation(rl)")
    public void addLogSuccess(JoinPoint jp, LogAnnotation rl) {
        Object[] params = jp.getArgs();
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i]);
        }

        System.out.println(jp.getSignature().getName());
        String className = jp.getTarget().getClass().toString();

        System.out.println("className:" + className);

        String signature = jp.getSignature().toString(); //获取目标方法签名
        System.out.println("signature:" + signature);
    }

}
