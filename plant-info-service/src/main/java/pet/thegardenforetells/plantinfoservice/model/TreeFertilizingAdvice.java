package pet.thegardenforetells.plantinfoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tree_fertilizing_advices")
public class TreeFertilizingAdvice extends PlantFertilizingAdvice {
    private Tree.TreeGrowthStage treeGrowthStage;
}
