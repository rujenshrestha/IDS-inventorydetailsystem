package com.inventory.detail.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.inventory.detail.service.*.*(..)) ")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("Method {} executed in {} ms", joinPoint.getSignature().toShortString(), elapsedTime);
		return result;
	}

	@Before("execution(* com.inventory.detail.service.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Entering method: {}", joinPoint.getSignature().toShortString());
		logger.info("Arguments list: {}", joinPoint.getArgs());
	}
}
