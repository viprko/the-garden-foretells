package pet.soilplotservice.service;

import java.util.List;
import java.util.UUID;
import pet.soilplotservice.model.LandPlot;

public interface LandPlotService {
    LandPlot save(LandPlot landPlot, UUID userId);

    LandPlot update(Long id, LandPlot landPlot);

    void delete(Long id);

    LandPlot findById(Long id);

    List<LandPlot> findAllByUser(UUID userId);
}
