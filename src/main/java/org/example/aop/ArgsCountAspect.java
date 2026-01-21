package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class ArgsCountAspect {
    @Before("@annotation(requiredArgsCount)")
    public void validateArguments(JoinPoint joinPoint, RequiredArgsCount requiredArgsCount) {
        Object[] args = joinPoint.getArgs();
        String[] stringArgs = (String[]) args[0];

        if (stringArgs.length != requiredArgsCount.requiredCount()) {
            throw new IllegalArgumentException(requiredArgsCount.errorMessage());
        }
    }
}
