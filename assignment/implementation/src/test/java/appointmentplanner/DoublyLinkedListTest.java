package appointmentplanner;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
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
    @CsvSource({"0, 'test'", "2, 'xaxa'"})
    void insertAtTest(int index, String value) {
        doublyLinkedList.insertAt(index, value);
        var a = doublyLinkedList.getElementAt(index);
        SoftAssertions.assertSoftly(s -> {
            assertThat(doublyLinkedList.getElementAt(index)).isEqualTo(value);
        });
    }
}
