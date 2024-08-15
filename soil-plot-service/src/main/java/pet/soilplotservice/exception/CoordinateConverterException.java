package pet.soilplotservice.exception;

public class CoordinateConverterException extends RuntimeException {
    public CoordinateConverterException(String message, Exception e) {
        super(message);
    };
}
