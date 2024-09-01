package pet.soilplotservice.aspect;

import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class UserIdAspect {

    @Around("execution(* *(.., @pet.soilplotservice.annotation.InjectUserId (*), ..))")
    public Object injectUserId(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getCredentials().toString());
        Signature signature = joinPoint.getSignature();
        signature.
        Object[] args = Arrays.stream(joinPoint.getArgs())
                .map(arg -> )
                .toArray();
        return joinPoint.proceed(args);
    }
}
