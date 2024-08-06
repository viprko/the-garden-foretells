package pet.authservice.exception;

public class InvalidJwtTokenAuthenticationException extends RuntimeException {
    public InvalidJwtTokenAuthenticationException(String message, Throwable cause) {
        super(message);
    }
}
