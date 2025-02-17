package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * **LoggingAspect**
 *
 * This aspect logs the execution of service methods in the application.
 * It uses Spring AOP to intercept method calls in the `com.example.demo.service` package,
 * providing logging before and after method execution, upon successful return, and when exceptions are thrown.
 *
 * By centralizing logging logic, it separates cross-cutting concerns from business logic,
 * promoting cleaner and more maintainable code.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Logger instance for logging messages.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods in the service package.
     *
     * This pointcut expression targets the execution of any method within
     * the `com.example.demo.service` package.
     */
    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    private void serviceMethods() {
        // Pointcut signature method, intentionally left empty.
    }

    /**
     * Advice that logs a message **before** the execution of matched methods.
     *
     * @param joinPoint provides reflective access to both the state available at a join point and static information about it.
     */
    @Before("serviceMethods()")
    public void logMethodCallBefore(JoinPoint joinPoint) {
        LOGGER.info("Before Method called: {}", joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs a message **after** the execution of matched methods, regardless of their outcome.
     *
     * @param joinPoint provides reflective access to the method being executed.
     */
    @After("serviceMethods()")
    public void logMethodCallAfter(JoinPoint joinPoint) {
        LOGGER.info("After Method called: {}", joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs a message **after successful completion** of matched methods.
     *
     * @param joinPoint provides access to the method execution.
     */
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logMethodCallAfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("After Returning from Method: {} with result: {}", joinPoint.getSignature().getName(), result);
    }

    /**
     * Advice that logs a message **when a matched method throws an exception**.
     *
     * @param joinPoint provides access to the method execution.
     * @param exception the exception thrown by the method.
     */
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logMethodCallAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LOGGER.error("Exception in Method: {} with message: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }
}
