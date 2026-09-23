package com.cper.AspectOrientedProgramming.aspect;

import org.apache.catalina.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class dtoAuditLogAspect {

    @Before("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.classLevelAnnotationPointCut()")
    public void beforeAuditLog(JoinPoint joinPoint) {
        System.out.println("Class      : " + joinPoint.getTarget().getClass().getName());
        System.out.println("Method     : " + joinPoint.getSignature().getName());
        System.out.println("Arguments  : " + Arrays.stream(joinPoint.getArgs()).map(args -> args == null? null : (User)args).toList());
    }

    @Around("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.classLevelAnnotationPointCut()")
    public void executionAuditLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========== AUDIT ==========");
        long startTime = System.currentTimeMillis();

        joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Execution  : " + duration + " ms");
        System.out.println("===========================");
    }
}
