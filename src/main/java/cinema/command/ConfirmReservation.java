package cinema.command;

import lombok.Value;

import java.util.UUID;

// TODO NOTE: not 'PayReservation': an other service do the payment and emit a 'ConfirmReservation' command
@Value
public class ConfirmReservation implements Command {
    UUID plannedScreenID;
    UUID reservationId;

    @Override
    public UUID getTargetEntityId() {
        return plannedScreenID;
    }

    // TODO: handle
}
