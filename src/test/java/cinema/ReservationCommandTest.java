package cinema;

import org.junit.jupiter.api.Test;

public class ReservationCommandTest {

    @Test
    void successfulReservation() {
        ReservationCommand reservationCommand = new ReservationCommand();
        CommandHandler commandHandler = new CommandHandler();

        commandHandler.handle(reservationCommand);

    }
}
