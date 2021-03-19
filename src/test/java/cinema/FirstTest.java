package cinema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    public void firstTest() {
        Assertions.assertEquals(1, 1);
    }
    
    @Test
    public void anotherTest() {
        Assertions.assertTrue(()->true);
    }
}
