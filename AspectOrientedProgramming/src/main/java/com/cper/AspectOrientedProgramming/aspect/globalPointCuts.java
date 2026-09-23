package com.cper.AspectOrientedProgramming.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class globalPointCuts {

    @Pointcut("within(com.cper.AspectOrientedProgramming.service.UserService)")
    public void userServicePointCut() {}

    @Pointcut("execution(* com.cper.AspectOrientedProgramming.controller.UserController.*(..))")
    public void userControllerPointCut() {}

    @Pointcut("@annotation(com.cper.AspectOrientedProgramming.customAnnotation.AuditLog)")
    public void mathodLevelAnnotationPointCut() {}

    @Pointcut("@within(com.cper.AspectOrientedProgramming.customAnnotation.AuditLog)")
    public void classLevelAnnotationPointCut() {}

}