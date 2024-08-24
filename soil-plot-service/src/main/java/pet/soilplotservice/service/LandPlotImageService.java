package pet.soilplotservice.service;

import java.io.IOException;
import pet.soilplotservice.model.LandPlot;

public interface LandPlotImageService {

    void saveImageFile(LandPlot landPlot) throws IOException;
}
