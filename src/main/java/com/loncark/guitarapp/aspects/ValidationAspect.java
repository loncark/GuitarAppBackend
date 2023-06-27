package com.loncark.guitarapp.aspects;

import com.loncark.guitarapp.model.guitar.Guitar;
import jakarta.validation.ValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* com.loncark.guitarapp.GuitarController.getByCode(..)) " +
            "|| execution(* com.loncark.guitarapp.GuitarController.delete(..))")
    public void beforeGetByCodeAndDelete(JoinPoint joinPoint) {
        if(!Alerts.isCodeOk(joinPoint.getArgs()[0].toString())) {
            throw new ValidationException("The code should have exactly 4 digits.");
        }
    }

    @Before("execution(* com.loncark.guitarapp.GuitarController.save(..)) " +
            "|| execution(* com.loncark.guitarapp.GuitarController.update(..))")
    public void beforeSaveAndUpdate(JoinPoint joinPoint) {
        Guitar guitar = (Guitar) joinPoint.getArgs()[0];

        if(!Alerts.isAllOk(guitar.getName(), guitar.getPrice().toString(), guitar.getStock().toString(),
                guitar.getNeck().toString(), guitar.getBody().toString(), guitar.getCode())) {

            System.out.println(Arrays.toString(joinPoint.getArgs()));
            throw new ValidationException("The Guitar argument violates validation rules.");
        }
    }

}
