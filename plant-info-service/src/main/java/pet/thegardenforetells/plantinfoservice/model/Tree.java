package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tree extends Plant {
    private boolean requireCrossPollination;
    private boolean provideFruit;
    private TreeType treeType;

    public enum TreeType {
        DECIDUOUS,
        CONIFEROUS;
    }
}
