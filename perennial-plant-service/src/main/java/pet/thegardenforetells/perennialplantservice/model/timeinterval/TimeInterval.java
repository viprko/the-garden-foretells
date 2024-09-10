package pet.thegardenforetells.perennialplantservice.model.timeinterval;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum TimeInterval {

    EARLY_SPRING(3),
    MID_SPRING(4),
    LATE_SPRING(5),

    EARLY_SUMMER(6),
    MID_SUMMER(7),
    LATE_SUMMER(8),

    EARLY_AUTUMN(9),
    MID_AUTUMN(10),
    LATE_AUTUMN(11),

    EARLY_WINTER(12),
    MID_WINTER(1),
    LATE_WINTER(2);

    private final int monthValue;

    private static final Map<Integer, TimeInterval> timeIntervals =
            Stream.of(values())
                    .collect(
                            Collectors.toMap(TimeInterval::getMonthValue,
                                    timeInterval -> timeInterval));

    private TimeInterval(int monthValue) {
        this.monthValue = monthValue;
    }

    public static TimeInterval of(int month) {
        TimeInterval interval = timeIntervals.get(month);
        if (interval == null) {
            throw new IllegalStateException("Invalid month: " + month);
        }
        return interval;
    }
}
