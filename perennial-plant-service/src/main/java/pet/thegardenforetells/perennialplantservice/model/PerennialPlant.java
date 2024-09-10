package pet.thegardenforetells.perennialplantservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import pet.thegardenforetells.perennialplantservice.model.timeinterval.TimeInterval;

@MappedSuperclass
public abstract class PerennialPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personalTitle;
    private UUID userId;
    private Coordinate position;
    private LocalDate plantedIn;
    private Short age;
    private Float wateringLevel;
    private List<LocalDate> wateringDates;
    private boolean isFertilized;
    private Map<TimeInterval, String> fertilizingAdvice;
    private List<LocalDate> fertilizingDates;
}
