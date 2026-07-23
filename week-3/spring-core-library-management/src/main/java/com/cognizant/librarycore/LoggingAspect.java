package com.cognizant.librarycore;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.cognizant.librarycore.BookService.*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        LOGGER.info("Start {}", joinPoint.getSignature().getName());
        try {
            return joinPoint.proceed();
        } finally {
            LOGGER.info("End {} ({} ns)", joinPoint.getSignature().getName(), System.nanoTime() - start);
        }
    }

    @Before("execution(* com.cognizant.librarycore.BookService.findAll(..))")
    public void beforeFind() { LOGGER.debug("Before findAll"); }

    @After("execution(* com.cognizant.librarycore.BookService.findAll(..))")
    public void afterFind() { LOGGER.debug("After findAll"); }
}
