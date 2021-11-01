package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class LLIteratorTest {
    @Mock
    TimeSlot entry1, entry2, entry3, entry4;


    private DoublyLinkedList<TimeSlot> doublyLinkedList;
    private LLIterator iterator;

    @BeforeEach
    private void setUp() {
        //TODO
        MockitoAnnotations.initMocks(this);
        doublyLinkedList = new DoublyLinkedList<TimeSlot>();
        doublyLinkedList.addHead(entry4);
        doublyLinkedList.addHead(entry3);
        doublyLinkedList.addHead(entry2);
        doublyLinkedList.addHead(entry1);

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
       // var secondReturnedNode = (DoublyLinkedList.AllocationNode) iterator.next();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(returnedNode.getItem()).isEqualTo(entry1);
           // softly.assertThat(secondReturnedNode.getItem()).isEqualTo(entry2);
        });
    }


    @Test
    public void hasNextFalse() {
        //TODO
    }
}
