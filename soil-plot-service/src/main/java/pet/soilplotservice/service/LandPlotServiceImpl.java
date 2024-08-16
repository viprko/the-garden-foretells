package pet.soilplotservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.soilplotservice.exception.InvalidLandPlotFormException;
import pet.soilplotservice.exception.LandPlotNotFoundException;
import pet.soilplotservice.model.Coordinate;
import pet.soilplotservice.model.LandPlot;
import pet.soilplotservice.repository.LandPlotRepository;

@Service
@RequiredArgsConstructor
public class LandPlotServiceImpl implements LandPlotService {
    private final LandPlotFormValidationService landPlotFormValidationService;
    private final LandPlotRepository landPlotRepository;

    @Override
    public LandPlot save(LandPlot landPlot) {
        if (landPlotFormValidationService.isFormValid(landPlot)) {
            throw new InvalidLandPlotFormException("Invalid land plot form. Check the entered "
                    + "coordinates");
        }
        landPlot.setArea(calculateArea(landPlot.getVertices()));
        return landPlotRepository.save(landPlot);
    }

    @Override
    public LandPlot update(Long id, LandPlot landPlot) {
        LandPlot landPlotFromDb = findById(id);
        landPlotFromDb.setVertices(landPlot.getVertices());
        return save(landPlotFromDb);
    }

    @Override
    public void delete(Long id) {
        landPlotRepository.delete(findById(id));
    }

    @Override
    public LandPlot findById(Long id) {
        return landPlotRepository.findById(id).orElseThrow(() -> new LandPlotNotFoundException(
                String.format("Land plot with id: {%d} not found", id)));
    }

    @Override
    public List<LandPlot> findAllByUser(String userId) {
        return landPlotRepository.findAllByUser(userId);
    }

    private double calculateArea(List<Coordinate> vertices) {
        double area = 0;
        for (int i = 0; i < vertices.size(); i++) {
            area += vertices.get(i).getX() * vertices.get(i + 1).getY();
            area += vertices.get(i).getY() * vertices.get(i + 1).getX();
        }
        area += vertices.getLast().getX() * vertices.getFirst().getY();
        area -= vertices.getLast().getY() * vertices.getFirst().getX();
        area = Math.abs(area) / 2;
        return area;
    }
}
