package pet.authservice.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pet.authservice.dto.UserRegistrationDto;

@Component
public class RepeatPasswordValidator implements ConstraintValidator<RepeatPasswordValidator,
        UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRegistrationDto.getPassword() != null && userRegistrationDto.getPassword()
                .equals(userRegistrationDto.getRepeatPassword());
    }
}
