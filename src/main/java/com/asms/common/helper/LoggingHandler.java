package com.asms.common.helper;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(@org.springframework.stereotype.Component *)")
	public void component() {
	}

	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}

	@Pointcut("execution(public * *(..))")
	protected void loggingPublicOperation() {
	}

	@Pointcut("execution(* *.*(..))")
	protected void loggingAllOperation() {
	}

	@Pointcut("within(com.asms.log..*)")
	private void logAnyFunctionWithinResource() {
	}

	// before -> Any resource annotated with @Component annotation
	// and all method and function taking HttpServletRequest as first parameter
	// @Before("component() && allMethod() && args(..,request)")
	// public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {

	@Before("component() && allMethod() ")
	public void logBefore(JoinPoint joinPoint) {

		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String returnValue = this.getValue(Arrays.toString(joinPoint.getArgs()));
		log.debug("SessionId: " + MDC.get("sessionId") + "   " + "Method: " + className + methodName + "   "
				+ "Arguments : " + returnValue);

	}

	/*
	 * if (null != request) { log.debug("Start Header Section of request ");
	 * log.debug("Method Type : " + request.getMethod()); Enumeration
	 * headerNames = request.getHeaderNames(); while
	 * (headerNames.hasMoreElements()) { String headerName = (String)
	 * headerNames.nextElement(); String headerValue =
	 * request.getHeader(headerName); log.debug("Header Name: " + headerName +
	 * " Header Value : " + headerValue); } log.debug("Request Path info :" +
	 * request.getServletPath()); log.debug("End Header Section of request "); }
	 */
	// }
	// After -> All method within resource annotated with @Component annotation
	// and return a value
	@AfterReturning(pointcut = "component() && allMethod()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String returnValue = this.getValue(result);
		log.debug("SessionId: " + MDC.get("sessionId") + "   " + "Method: " + className + methodName + "   "
				+ "Return value : " + returnValue);
	}

	// After -> Any method within resource annotated with @Component annotation
	// throws an exception ...Log it
	@AfterThrowing(pointcut = "component() && allMethod()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {

		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String returnValue = this.getValue(Arrays.toString(joinPoint.getArgs()));
		log.error("SessionId: " + MDC.get("sessionId") + "   " + "Method: " + className + methodName + "   "
				+ "Exception : " + exception.getCause());

	}

	// Around -> Any method within resource annotated with @Component annotation
	@Around("component() && allMethod()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		try {

			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			//String returnValue = this.getValue(Arrays.toString(joinPoint.getArgs()));
			Object result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - start;
			log.debug("SessionId: " + MDC.get("sessionId") + "   " + "Method: " + className + methodName + "   "
					+ " execution time : " + elapsedTime + " ms");

			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}

	private String getValue(Object result) {
		String returnValue = null;
		if (null != result) {
			if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
				returnValue = ReflectionToStringBuilder.toString(result);
			} else {
				returnValue = result.toString();
			}
		}
		return returnValue;
	}
}