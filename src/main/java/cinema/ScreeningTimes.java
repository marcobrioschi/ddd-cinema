package cinema;

import cinema.domain.ScreeningTime;

import java.util.UUID;

public interface ScreeningTimes {

    public ScreeningTime findByUUID(UUID uuid);

    public void save(ScreeningTime screeningTime);

}
