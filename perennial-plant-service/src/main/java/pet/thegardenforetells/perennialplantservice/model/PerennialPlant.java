package pet.thegardenforetells.perennialplantservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perennial_plants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerennialPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID userId;
    private Coordinate position;
    private Type type;

    public enum Type {
        TREE,
        BUSH
    }
}
