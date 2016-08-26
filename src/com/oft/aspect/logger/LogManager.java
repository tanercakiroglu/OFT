package com.oft.aspect.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LogManager {

       @Pointcut("execution(* com.oft.service..*(..))")
       public void auditLog() {}

       @Pointcut("execution(* com.oft.service..*(..))")
       public void performanceLog(){}

}