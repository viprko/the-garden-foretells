package pet.thegardenforetells.perennialplantservice.dto;

import lombok.Data;
import pet.thegardenforetells.perennialplantservice.model.Coordinate;
import pet.thegardenforetells.perennialplantservice.model.PerennialPlant;

@Data
public class PerennialPlantResponseDto {
    private Long id;
    private Coordinate position;
}
