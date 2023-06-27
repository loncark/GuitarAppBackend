package com.loncark.guitarapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.loncark.guitarapp.GuitarController.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().toShortString() + " invoked with input argument of " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.loncark.guitarapp.GuitarController.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        System.out.println("Returned result: " + result.toString());
    }
}
