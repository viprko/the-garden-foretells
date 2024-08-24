package pet.soilplotservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.soilplotservice.model.Coordinate;
import pet.soilplotservice.model.LandPlot;
import pet.soilplotservice.model.LineSegment;

@Service
@RequiredArgsConstructor
public class LandPlotFormValidationServiceImpl implements LandPlotFormValidationService {
    private final LineSegmentService lineSegmentService;

    @Override
    public boolean isFormValid(LandPlot landPlot) {
        List<LineSegment> lineSegments =
                lineSegmentService.createLineSegments(landPlot.getVertices());
        return !areLinesIntersect(lineSegments);
    }

    private boolean areLinesIntersect(List<LineSegment> lineSegments) {
        int n = lineSegments.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j == i + 1 || (i == 0 && j == n - 1)) {
                    continue;
                }
                if (doIntersect(lineSegments.get(i), lineSegments.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doIntersect(LineSegment l1, LineSegment l2) {
        Coordinate p1 = l1.getStart(), q1 = l1.getEnd();
        Coordinate p2 = l2.getStart(), q2 = l2.getEnd();
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        return o1 == 0 && onSegment(p1, p2, q1)
                || o2 == 0 && onSegment(p1, q2, q1)
                || o3 == 0 && onSegment(p2, p1, q2)
                || o4 == 0 && onSegment(p2, q1, q2);
    }

    private int orientation(Coordinate p, Coordinate q, Coordinate r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0.0) {
            return 0;
        }
        return val > 0.0 ? 1 : 2;
    }

    private boolean onSegment(Coordinate p, Coordinate q, Coordinate r) {
        return q.getX() <= Math.max(p.getX(), r.getX())
                && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY())
                && q.getY() >= Math.min(p.getY(), r.getY());
    }
}
