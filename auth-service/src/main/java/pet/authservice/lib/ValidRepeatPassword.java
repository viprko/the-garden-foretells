package pet.authservice.lib;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RepeatPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRepeatPassword {
    String message() default "Passwords doesn't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
