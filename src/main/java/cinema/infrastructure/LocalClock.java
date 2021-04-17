package cinema.infrastructure;

import java.time.LocalDateTime;

public interface LocalClock {

    LocalDateTime now();

}
