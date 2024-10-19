package k44.timesheets.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Slf4j
public class RecoveryAspect {
    @Around(value = "@annotation(Recover)")
    public Object methodRecovery(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Exception e) {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            String type = methodSignature.getReturnType().getSimpleName();
            log.error("@Recover: {} type mismatch", methodSignature.getReturnType());

            return switch (type) {
                case "int" -> 0;
                case "long" -> 0L;
                case "float" -> 0.0f;
                case "double" -> 0.0d;
                case "boolean" -> Boolean.FALSE;
                case "char" -> '\0';
                default -> null;
            };


        }
    }

    @Before(value = "@annotation(Recover)")
    public void recovery(JoinPoint jp) {
        log.info("Before recovery: {}", jp.getSignature().toShortString());
    }
}
