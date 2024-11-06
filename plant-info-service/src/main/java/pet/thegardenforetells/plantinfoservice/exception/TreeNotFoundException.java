package pet.thegardenforetells.plantinfoservice.exception;

import java.util.function.Supplier;

public class TreeNotFoundException extends RuntimeException {
    public TreeNotFoundException(String message) {
        super(message);
    }
}

