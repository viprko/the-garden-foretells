package pet.soilplotservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LineSegment {
    private Coordinate start;
    private Coordinate end;
}
