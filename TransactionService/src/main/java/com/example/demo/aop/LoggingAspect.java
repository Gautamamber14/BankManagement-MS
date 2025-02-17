package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * **LoggingAspect**
 *
 * This aspect is responsible for logging the execution of all service methods within
 * the `com.example.demo.service` package. It utilizes Spring AOP (Aspect-Oriented Programming)
 * to intercept and log method calls, providing insights into the application's behavior.
 *
 * By centralizing the logging logic, it promotes cleaner code and separates cross-cutting concerns
 * from the business logic.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Logger instance for logging messages.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods within the service package.
     *
     * The pointcut expression targets the execution of any method within
     * `com.example.demo.service` and its sub-packages.
     */
    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    private void serviceMethods() {
        // Pointcut signature method - no implementation required.
    }

    /**
     * Advice that logs a message **before** the execution of matched methods.
     *
     * @param joinPoint provides reflective access to both the state available at
     *                  a join point and static information about it.
     */
    @Before("serviceMethods()")
    public void logMethodCallBefore(JoinPoint joinPoint) {
        LOGGER.info("Before Method called: {}", joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs a message **after** the execution of matched methods,
     * regardless of their outcome (whether they returned normally or threw an exception).
     *
     * @param joinPoint provides access to the method being executed.
     */
    @After("serviceMethods()")
    public void logMethodCallAfter(JoinPoint joinPoint) {
        LOGGER.info("After Method called: {}", joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs a message **after** a matched method returns successfully.
     *
     * @param joinPoint provides access to the method being executed.
     */
    @AfterReturning("serviceMethods()")
    public void logMethodCallAfterReturning(JoinPoint joinPoint) {
        LOGGER.info("After Returning from Method: {}", joinPoint.getSignature().getName());
    }

    /**
     * Advice that logs a message **if** a matched method throws an exception.
     *
     * @param joinPoint provides access to the method being executed.
     * @param exception the exception thrown by the method.
     */
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logMethodCallAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LOGGER.error("Exception in Method: {} with message: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }
}
