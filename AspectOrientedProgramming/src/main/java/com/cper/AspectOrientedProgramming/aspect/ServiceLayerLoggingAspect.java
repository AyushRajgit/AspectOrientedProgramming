package com.cper.AspectOrientedProgramming.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLayerLoggingAspect {

    @Before("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userServicePointCut()")
    public void beforeCreateUser(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName());
        System.out.println("MethodName : " + joinPoint.getSignature().getName());
        System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()) + "\n");
    }


    @AfterReturning(
            pointcut = "com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userServicePointCut()",
            returning = "result"
    )
    public void afterSuccessfullyCreatedUser(String result) {
        System.out.println("Successfully User Creation Done : " + result + "\n");
    }


    @AfterThrowing(
            pointcut = "com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userServicePointCut()",
            throwing = "e"
    )
    public void afterThrowingCreateUser(JoinPoint joinPoint, Exception e) {
        System.out.println("Exception Method Name : " +
                joinPoint.getSignature().getName());

        System.out.println("Exception : " +
                e.getMessage() + "\n");
    }


    @After("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userServicePointCut()")
    public void resourceReleasedCreateUser(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName() + " : Resource released");
    }


    @Around("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userServicePointCut()")
    public String aroundCreateUser(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("............Interception of Service Layer starts here............\n");

        long startTime = System.currentTimeMillis();
        String ans = (String) joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time : " + (endTime - startTime) + "ms\n");

        System.out.println("............Interception of Service Layer ends here............\n");

        return ans;
    }

}
