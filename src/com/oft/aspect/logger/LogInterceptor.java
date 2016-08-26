package com.oft.aspect.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class LogInterceptor {

	 private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	@Before(value = "com.oft.aspect.logger.LogManager.auditLog()" + "&& target(bean) "
			+ "&& @annotation(com.oft.aspect.logger.Loggable)" + "&& @annotation(logme)", argNames = "bean,logme")
	public void log(JoinPoint jp, Object bean, Loggable logme) {

		logger.debug(String.format("Log Message: %s", logme.message()));
		logger.debug(String.format("Bean Called: %s", bean.getClass().getName()));
		logger.debug(String.format("Method Called: %s", jp.getSignature().getName()));

	}

	@Around(value = "com.oft.aspect.logger.LogManager.performanceLog()" + "&& target(bean) "
			+ "&& @annotation(com.oft.aspect.logger.Loggable)" + "&& @annotation(logme)", argNames = "bean,logme")
	public Object performanceLog(ProceedingJoinPoint joinPoint, Object bean,Loggable logme) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object ret = joinPoint.proceed();

		stopWatch.stop();

		StringBuffer logMessage = new StringBuffer();
		logMessage.append(joinPoint.getTarget().getClass().getName());
		logMessage.append(".");
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("(");
		// append args
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logMessage.append(args[i]).append(",");
		}
		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}

		logMessage.append(")");
		logMessage.append(" execution time: ");
		logMessage.append(stopWatch.getTotalTimeMillis());
		logMessage.append(" ms");
		logger.debug(logMessage.toString());

		return ret;
	}
}