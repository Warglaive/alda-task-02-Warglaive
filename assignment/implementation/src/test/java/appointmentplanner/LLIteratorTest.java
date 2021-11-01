package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class LLIteratorTest {
    @Mock
    TimeSlot firstTimeSlot;
    @Mock
    TimeSlot secondTimeSlot;
    @Mock
    TimeSlot entry3;
    @Mock
    TimeSlot entry4;


    private DoublyLinkedList<TimeSlot> doublyLinkedList;
    private LLIterator iterator;

    @BeforeEach
    private void setUp() {
        //TODO
        this.secondTimeSlot =Mockito.mock(TimeSlot.class);
        doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addHead(entry4);
        doublyLinkedList.addHead(entry3);
        doublyLinkedList.addHead(secondTimeSlot);
        doublyLinkedList.addHead(firstTimeSlot);

        iterator = new LLIterator(doublyLinkedList.getHead(), doublyLinkedList.getTail());
    }

    @Test
    public void hasNextTrue() {
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    public void next() {
        //TODO
        var returnedNode = (DoublyLinkedList.AllocationNode) iterator.next();
        var secondReturnedNode = (DoublyLinkedList.AllocationNode) iterator.next();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(returnedNode.getItem()).isEqualTo(firstTimeSlot);
            softly.assertThat(secondReturnedNode).isNull();
        });
    }


    @Test
    public void hasNextFalse() {
        //TODO
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }
}
