package ru.gb.springbootlesson3.timerstarter;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
@Slf4j
public class TimerAspect {

    @Around("@annotation(ru.gb.springbootlesson3.aspect.Timer)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("{} executed in {}ms", joinPoint.getSignature(), (endTime - startTime));
        return result;
    }
}
