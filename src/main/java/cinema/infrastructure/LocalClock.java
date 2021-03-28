package cinema.infrastructure;

import cinema.domain.Now;

// TODO: is the right approach to decide the time of reservation? Or the reservation time must included in the reservationcommand?
public interface LocalClock {

    Now now();

}
