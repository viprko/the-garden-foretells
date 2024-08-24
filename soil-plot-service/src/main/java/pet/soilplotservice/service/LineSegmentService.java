package pet.soilplotservice.service;

import java.util.List;
import pet.soilplotservice.model.Coordinate;
import pet.soilplotservice.model.LineSegment;

public interface LineSegmentService {

    List<LineSegment> createLineSegments(List<Coordinate> coordinates);
}
