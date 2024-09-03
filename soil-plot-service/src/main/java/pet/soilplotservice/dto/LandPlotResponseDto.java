package pet.soilplotservice.dto;

import java.util.List;
import lombok.Data;
import pet.soilplotservice.model.Coordinate;

@Data
public class LandPlotResponseDto {
    private Long id;
    private List<Coordinate> vertices;
    private Double area;
}
