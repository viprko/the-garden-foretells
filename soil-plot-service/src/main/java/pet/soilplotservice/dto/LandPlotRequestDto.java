package pet.soilplotservice.dto;

import java.util.List;
import lombok.Data;
import pet.soilplotservice.model.Coordinate;

@Data
public class LandPlotRequestDto {
    private List<Coordinate> vertices;
    private String title;
}
