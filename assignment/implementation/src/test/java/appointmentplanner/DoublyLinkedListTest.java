package appointmentplanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DoublyLinkedListTest {

    private static DoublyLinkedList<String> doublyLinkedList;

    @Test
    @BeforeAll
    static void setUp() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    @Test
    void constructorTest() {
        assertThat(doublyLinkedList).isExactlyInstanceOf(DoublyLinkedList.class);
    }

    @ParameterizedTest
    @CsvSource({"1, 'test'", "2, 'xaxa'"})
    void insertFrontTest(int index, String value) {
        int size = doublyLinkedList.getSize();
        doublyLinkedList.insertAt(index, value);
    }
}
