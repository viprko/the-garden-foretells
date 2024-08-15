package pet.soilplotservice.exception;

public class LandPlotNotFoundException extends RuntimeException {
    public LandPlotNotFoundException(String message) {
        super(message);
    };
}
