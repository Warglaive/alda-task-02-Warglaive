package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class LLIteratorTest {
    @Mock
    TimeSlot firstTimeSlot;
    @Mock
    TimeSlot secondTimeSlot;
    @Mock
    TimeSlot third;
    @Mock
    TimeSlot fourth;


    private DoublyLinkedList<TimeSlot> doublyLinkedList;
    private LLIterator iterator;

    @BeforeEach
    private void setUp() {
        //TODO

        this.secondTimeSlot = Mockito.mock(TimeSlot.class);

        //MockitoAnnotations.initMocks(this);
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.doublyLinkedList.addHead(this.fourth);
        this.doublyLinkedList.addHead(this.third);
        this.doublyLinkedList.addHead(this.secondTimeSlot);
        this.doublyLinkedList.addHead(this.firstTimeSlot);

        this.iterator = new LLIterator(this.doublyLinkedList.getHead(), this.doublyLinkedList.getTail());
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
       var a=  iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }
}
