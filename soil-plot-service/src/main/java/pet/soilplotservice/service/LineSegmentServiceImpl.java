package pet.soilplotservice.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import pet.soilplotservice.model.Coordinate;
import pet.soilplotservice.model.LineSegment;

@Service
public class LineSegmentServiceImpl implements LineSegmentService {
    @Override
    public List<LineSegment> createLineSegments(List<Coordinate> coordinates) {
        return IntStream.range(0, coordinates.size())
                .mapToObj(i -> new LineSegment(coordinates.get(i),
                        coordinates.get((i + 1) % coordinates.size())))
                .collect(Collectors.toList());
    }
}
