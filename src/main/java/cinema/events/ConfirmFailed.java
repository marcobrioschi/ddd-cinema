package cinema.events;

import cinema.domain.Customer;
import cinema.domain.RefusedConfirmationReasons;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class ConfirmFailed implements Event {

    UUID plannedScreenId;
    UUID reservationId;
    Customer customer;
    List<Seat> seats;
    RefusedConfirmationReasons reason;

    @Override
    public UUID getAggregateRootId() {
        return plannedScreenId;
    }

}
