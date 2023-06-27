package com.loncark.guitarapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static boolean append = false;
    private static final String OUTPUT_FILE = "log.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* com.loncark.guitarapp.GuitarController.*(..))")
    public void before(JoinPoint joinPoint) {
        String logMessage = joinPoint.getSignature().toShortString() + " invoked with input argument of " + Arrays.toString(joinPoint.getArgs());
        writeLogToFile(logMessage, append);
        if (!append) { append = true; }
    }

    @AfterReturning(pointcut = "execution(* com.loncark.guitarapp.GuitarController.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        String logMessage = "Returned result: " + (result != null ? result.toString() : "null");
        writeLogToFile(logMessage, append);
    }

    private void writeLogToFile(String logMessage, boolean append) {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE, append)) {
            String formattedDateTime = LocalDateTime.now().format(FORMATTER);
            fileWriter.write(formattedDateTime + " - " + logMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
