package cinema;

import cinema.domain.ScreeningTime;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class ScreeningTimeInMemoryStorage implements ScreeningTimes{

    Map<UUID, ScreeningTime> _screeningTimes = new Hashtable();

    @Override
    public ScreeningTime findByUUID(UUID uuid) {
        return _screeningTimes.get(uuid);
    }

    @Override
    public void save(ScreeningTime screeningTime) {
        _screeningTimes.put(screeningTime.getId(), screeningTime);
    }
}
