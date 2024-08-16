package pet.soilplotservice.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pet.soilplotservice.util.CoordinateConverter;

@Entity
@Getter
@Setter
@Table(name = "land_plots")
public class LandPlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @Convert(converter = CoordinateConverter.class)
    private List<Coordinate> vertices;
    private Double area;
}
