package pet.thegardenforetells.plantinfoservice.dto;

import java.util.List;
import lombok.Data;
import pet.thegardenforetells.plantinfoservice.model.Tree;

@Data
public class TreeRequestDto {
    private String name;
    private String family;
    private String description;
    private Float temperatureMinC;
    private Float temperatureMaxC;
    private Float minHeightCm;
    private Float maxHeightCm;
    private List<String> soilTypes;
    private boolean requireCrossPollination;
    private boolean provideFruit;
    private Tree.TreeType treeType;
}
