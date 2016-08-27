package com.oft.aspect.exceptionHandling;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionManager {

	@Pointcut("execution(* com.oft.dao..*(..))")
    public void handleExcepiton() {}

    
}
