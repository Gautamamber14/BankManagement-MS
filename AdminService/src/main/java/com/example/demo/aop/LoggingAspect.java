package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * **LoggingAspect**
 *
 * This aspect is responsible for logging the execution of all service methods within the application.
 * It utilizes Spring AOP to intercept method calls in the `com.example.demo.service` package,
 * providing insights before and after method execution, upon successful completion, and when exceptions are thrown.
 *
 * By centralizing logging logic, it promotes cleaner code and separates cross-cutting concerns from business logic.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Logger instance for logging informational messages.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods in the service package.
     *
     * This pointcut expression targets the execution of any method within
     * `com.example.demo.service` package and its sub-packages.
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
    @AfterReturning("serviceMethods()")
    public void logMethodCallAfterReturning(JoinPoint joinPoint) {
        LOGGER.info("After Returning from Method: {}", joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs a message **when a matched method throws an exception**.
     *
     * @param joinPoint provides access to the method execution.
     * @param exception the exception thrown by the method.
     */
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logMethodCallAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LOGGER.error("After Throwing in Method: {} with Exception: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }
}
