package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plant_diseases")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlantDisease {
    @Id
    private Long id;
    private String name;
    private String symptoms;
    private String treatment;
}
