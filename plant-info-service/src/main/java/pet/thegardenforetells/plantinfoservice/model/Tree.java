package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private TreeGrowthStage treeGrowthStage;
    private boolean requireCrossPollination;

    @Getter
    public enum TreeGrowthStage {
        SEED("description here"),
        SPROUT("description here"),
        SEEDLING("description here"),
        SAPLING("description here"),
        MATURE("description here");

        private TreeGrowthStage(String description) {
        }
    }
}
