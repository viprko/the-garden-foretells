package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class Plant {
    @Id
    private Long id;
    private String name;
    private String family;
    private String description;
    private List<SoilType> soilTypes;
    private Float temperatureMinC;
    private Float temperatureMaxC;
    private List<PlantDisease> diseases;

}
