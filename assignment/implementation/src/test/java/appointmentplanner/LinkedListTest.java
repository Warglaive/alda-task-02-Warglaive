package appointmentplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    private LinkedListImpl linkedList;

    @Test
    @BeforeEach
    void constructorTest() {
        this.linkedList = new LinkedListImpl();
    }
}
