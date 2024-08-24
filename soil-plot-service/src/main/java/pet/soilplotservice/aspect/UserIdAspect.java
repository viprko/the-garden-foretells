package pet.soilplotservice.aspect;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pet.soilplotservice.util.JwtUtil;

@Aspect
@Component
@RequiredArgsConstructor
public class UserIdAspect {
    private final JwtUtil jwtUtil;

    @Around("@annotation(pet.soilplotservice.annotation.UserId))")
    public Object injectUserId(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = jwtUtil.extractUserId((String) authentication.getCredentials());
        Object[] args = Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg instanceof String ? userId : arg)
                .toArray();
        return joinPoint.proceed(args);
    }
}
