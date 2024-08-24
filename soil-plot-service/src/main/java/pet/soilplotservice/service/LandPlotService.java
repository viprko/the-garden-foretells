package pet.soilplotservice.service;

import java.util.List;
import pet.soilplotservice.model.LandPlot;

public interface LandPlotService {
    LandPlot save(LandPlot landPlot, String userId);

    LandPlot update(Long id, LandPlot landPlot);

    void delete(Long id);

    LandPlot findById(Long id);

    List<LandPlot> findAllByUser(String userId);
}
