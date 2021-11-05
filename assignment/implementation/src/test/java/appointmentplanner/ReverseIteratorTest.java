package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseIteratorTest {
    @Mock
    TimeSlot entry1, entry2, entry3, entry4;

    private DoublyLinkedList<TimeSlot> doublyLinkedList;
    private ReverseIterator iterator;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
        doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addFront(entry4);
        doublyLinkedList.addFront(entry3);
        doublyLinkedList.addFront(entry2);
        doublyLinkedList.addFront(entry1);

        iterator = new ReverseIterator(doublyLinkedList.getHead(), doublyLinkedList.getTail());
    }

    @Test
    public void hasNextTrue() {
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    public void next() {
        var returnedNode = (DoublyLinkedList.Node) iterator.next();
        var secondReturnedNode = (DoublyLinkedList.Node) iterator.next();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(returnedNode.getItem()).isEqualTo(entry4);
            softly.assertThat(secondReturnedNode.getItem()).isEqualTo(entry3);
        });
    }

    @Test
    public void hasNextFalse() {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }
}
