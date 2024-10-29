package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String family;
    private String description;
    @Column(name = "temperature_min_c")
    private Float temperatureMinC;
    @Column(name = "temperature_max_c")
    private Float temperatureMaxC;
    @Column(name = "min_height_c")
    private Float minHeightCm;
    @Column(name = "max_height_c")
    private Float maxHeightCm;
}
