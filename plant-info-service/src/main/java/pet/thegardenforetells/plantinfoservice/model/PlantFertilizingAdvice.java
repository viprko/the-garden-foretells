package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PlantFertilizingAdvice {
    @Id
    private Long id;
    private SoilType soilType;
    private Float wateringNeedsInLitresPerWeek;
    private Float phMin;
    private Float phMax;
    private Float nitrogenContent;
    private Float phosphorusContent;
    private Float potassiumContent;
}
