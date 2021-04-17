package cinema.domain;

import lombok.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Value
public class SchedulingTime {
    LocalDateTime localDateTime;

    public long remainingTimeBeforeStart(LocalDateTime frozenNow) {
        return ChronoUnit.MINUTES.between(frozenNow, localDateTime);
    }
}
