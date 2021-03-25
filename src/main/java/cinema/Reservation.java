package cinema;

import java.util.Date;
import java.util.List;

public class Reservation {
    public Customer customer;
    public List<Seat> seats;
    public ScreeningTime screeningTime;
    public Date expirationTime;
}
