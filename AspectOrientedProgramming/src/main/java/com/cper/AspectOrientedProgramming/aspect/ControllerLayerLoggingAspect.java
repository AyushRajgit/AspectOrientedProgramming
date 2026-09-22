package com.cper.AspectOrientedProgramming.aspect;

import com.cper.AspectOrientedProgramming.dto.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLayerLoggingAspect {

    @Before("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userControllerPointCut()")
    public void beforeRun(JoinPoint joinPoint) {
        System.out.println("ClassName : " + joinPoint.getTarget().getClass().getName());
        System.out.println("MethodName : " + joinPoint.getSignature().getName());
        System.out.println("Arguments : " + Arrays.stream(joinPoint.getArgs()).map(args -> args == null? null : args.getClass().getName()).toList() + "\n");
    }

    @AfterReturning("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userControllerPointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+ " Controller successfully executed\n");
    }

    @AfterThrowing(
            pointcut = "com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userControllerPointCut()",
            throwing = "e"
    )
    public void afterThrowingCreateUser(JoinPoint joinPoint, Exception e) {
        System.out.println("Exception Http Method Name : " +
                joinPoint.getSignature().getName());

        System.out.println("Exception : " +
                e.getMessage() + "\n");
    }


    @After("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userControllerPointCut()")
    public void resourceReleasedCreateUser(JoinPoint joinPoint) {
        System.out.println("Controller Name : " + joinPoint.getTarget().getClass().getName() + " : Resource released");
    }

    @Around("com.cper.AspectOrientedProgramming.aspect.globalPointCuts.userControllerPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("............Interception of Controller Layer starts here............\n");

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time : " + executionTime + "ms");
        System.out.println("............Interception of Controller Layer ends here............\n");

        return result;
    }
}
