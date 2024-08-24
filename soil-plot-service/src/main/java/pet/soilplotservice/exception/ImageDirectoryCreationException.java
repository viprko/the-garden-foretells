package pet.soilplotservice.exception;

public class ImageDirectoryCreationException extends RuntimeException {
    public ImageDirectoryCreationException(String message, Exception e) {
        super(message);
    };
}
