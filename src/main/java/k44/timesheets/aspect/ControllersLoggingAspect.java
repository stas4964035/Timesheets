package k44.timesheets.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class ControllersLoggingAspect {
    @Pointcut("execution(* k44.timesheets.controller.TimesheetController.*(..))")
    public void callAtControllerMethods(){}

    // Вывод в лог типа и имени метода при вызове
    @Before("callAtControllerMethods()")
    public void loggingMethodStart(JoinPoint jp){
        log.info("call {} {}", jp.getKind(), jp.getSignature().toString());
    }

    // Вывод в лог времени выполнения метода при вызове
    @Around("callAtControllerMethods()")
    public Object methodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        try {
            return pjp.proceed();
        }finally {
            long duration = System.currentTimeMillis() - begin;
            log.info("finished in {} ms {} {}", duration, pjp.getKind(), pjp.getSignature().toString());
        }
    }


    // Вывод в лог информации о вызываемом методе: имя класса и метода, типа и значения аргументов, если таковые имеются
    @Before("callAtControllerMethods()")
    public void methodArgsData(JoinPoint jp){
        StringBuilder argsStr = new StringBuilder();
        Object[] args = jp.getArgs();
        if(args.length > 0){
            Arrays.stream(args).forEach(o -> argsStr.append(o != null ? o.getClass().getSimpleName() + " " + o : null).append(", "));

        }
        log.info("{}.{}({})",
                jp.getSignature().getDeclaringType().getSimpleName(),
                jp.getSignature().getName(),
                argsStr.substring(0, argsStr.length() - 2));
    }
}
