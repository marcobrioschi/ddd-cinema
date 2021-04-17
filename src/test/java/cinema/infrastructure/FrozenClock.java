package cinema.infrastructure;

import java.time.LocalDateTime;

public class FrozenClock implements LocalClock {

    private final LocalDateTime frozenNow;

    public FrozenClock(LocalDateTime frozenNow) {
        this.frozenNow = frozenNow;
    }

    @Override
    public LocalDateTime now() {
        return frozenNow;
    }

}
