package com.testservice.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeasureQueryPeriodAspect {

    @Around("execution(* com.testservice.service.*.*(..))")
    public Object timeMeasuring(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        StringBuilder out = new StringBuilder();
        long b = System.currentTimeMillis();
        result = joinPoint.proceed();
        long e = System.currentTimeMillis() - b;
        out.append("time query: ");
        out.append(joinPoint.getSignature().getName());
        out.append("(");
        out.append(Arrays.toString(joinPoint.getArgs()));
        out.append("): ");
        out.append(e);
        out.append(" ms.");
        System.out.println(out);
        return result;
    }
}