package com.cper.AspectOrientedProgramming.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.cper.AspectOrientedProgramming.service.UserService.createUser())")
    public void beforeCreateUser(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName());
        System.out.println("MethodName : " + joinPoint.getSignature().getName());
        System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()) + "\n");
    }

    @Before("execution(* com.cper.AspectOrientedProgramming.service.UserService.deleteUser())")
    public void beforeDeleteUser(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName());
        System.out.println("MethodName : " + joinPoint.getSignature().getName());
        System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()) + "\n");
    }

    @AfterReturning(
            pointcut = "execution(* com.cper.AspectOrientedProgramming.service.UserService.createUser())",
            returning = "result"
    )
    public void afterSuccessfullyCreatedUser(String result) {
        System.out.println("Successfully User Creation Done : " + result + "\n");
    }

    @AfterReturning(
            pointcut = "execution(* com.cper.AspectOrientedProgramming.service.UserService.deleteUser())",
            returning = "result"
    )
    public void afterSuccessfullyDeletingUser(String result) {
        System.out.println("Successfully User Deletion Done : " + result + "\n");
    }

    @AfterThrowing(
            pointcut = "execution(* com.cper.AspectOrientedProgramming.service.UserService.createUser())",
            throwing = "e"
    )
    public void afterThrowingCreateUser(JoinPoint joinPoint, Exception e) {
        System.out.println("Exception Method Name : " +
                joinPoint.getSignature().getName());

        System.out.println("Exception : " +
                e.getMessage() + "\n");
    }

    @AfterThrowing(
            pointcut = "execution(* com.cper.AspectOrientedProgramming.service.UserService.deleteUser())",
            throwing = "e"
    )
    public void afterThrowingdeleteUser(JoinPoint joinPoint, Exception e) {
        System.out.println("Exception Method Name : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " +  e.getMessage() + "\n");
    }

    @After("execution(* com.cper.AspectOrientedProgramming.service.UserService.createUser())")
    public void resourceRelesedCreateUser(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName() + " : Resource released");
    }

    @After("execution(* com.cper.AspectOrientedProgramming.service.UserService.deleteUser())")
    public void resourceRelesedDeleteUser(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName() + " : Resource released");
    }

    @Around("execution(* com.cper.AspectOrientedProgramming.service.UserService.createUser())")
    public String aroundCreateUser(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String ans = (String) joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time : " + (endTime - startTime) + "ms\n");

        return ans;
    }

    @Around("execution(* com.cper.AspectOrientedProgramming.service.UserService.deleteUser())")
    public String aroundDeleteUser(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String ans = (String) joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time : " + (endTime - startTime) + "ms\n");

        return ans;
    }

}
