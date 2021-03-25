package cinema;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationCommandTest {

    @Test
    void successfulReservation() {
        
    	ScreeningTime expected = new ScreeningTime();
    	
    	ReservationCommand reservationCommand = new ReservationCommand();
        CommandHandler commandHandler = new CommandHandler();

        commandHandler.handle(reservationCommand);

        ScreeningTime actual = readScreeningTime();
        
        //assertTrue(actual,expected);
    }
    
    public ScreeningTime readScreeningTime() {
    	return null;
    }
}
