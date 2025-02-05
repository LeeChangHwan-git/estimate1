package com.storelabs.mygage.estimate.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


@Aspect
@Component
@Profile("prod")
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {}

    @Around("controllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Method: {}.{} - Request Parameters: {}",
                className, methodName, mapper.writeValueAsString(args));

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Exception in {}.{}", className, methodName, e);
            throw e;
        }
    }
}
