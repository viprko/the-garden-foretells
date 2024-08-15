package pet.soilplotservice.service;

import pet.soilplotservice.model.LandPlot;

public interface LandPlotFormValidationService {
    boolean isFormValid(LandPlot landPlot);
}
