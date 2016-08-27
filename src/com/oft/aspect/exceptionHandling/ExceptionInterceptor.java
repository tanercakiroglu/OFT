package com.oft.aspect.exceptionHandling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.oft.util.Util;

@Aspect
public class ExceptionInterceptor {

	
	@Around(value = "com.oft.aspect.exceptionHandling.ExceptionManager.handleExcepiton()" + "&& target(bean) "
			+ "&& @annotation(com.oft.aspect.exceptionHandling.HandleException)" + "&& @annotation(handleExcpetion)", argNames = "bean,handleExcpetion")
	public Object handleExcpetion(ProceedingJoinPoint joinPoint, Object bean,HandleException handleExcpetion) throws Throwable {
		
		Object ret = null;
		try {
			ret = joinPoint.proceed();
			return ret;
		} catch (Exception ex) {
		    return	Util.constructJSON("fail",ex.getStackTrace() ,false);
		}
		
	}
}
