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
    TimeSlot ts1;
    @Mock
    TimeSlot ts2;
    @Mock
    TimeSlot ts3;
    @Mock
    TimeSlot ts4;

    private DoublyLinkedList<TimeSlot> doublyLinkedList;
    private GenericIterator iterator;
   // @Rule //initMocks
    //public MockitoRule rule;

    @BeforeEach
    public void setUp() {
       // this.rule = MockitoJUnit.rule();
        MockitoAnnotations.initMocks(this);
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.doublyLinkedList.addFront(ts4);
        this.doublyLinkedList.addFront(ts3);
        this.doublyLinkedList.addFront(ts2);
        this.doublyLinkedList.addFront(ts1);

        this.iterator = new GenericIterator(this.doublyLinkedList.getHead(), this.doublyLinkedList.getTail());
    }

    @Test
    public void hasNextTrue() {
        assertThat(this.iterator.hasNext()).isFalse();
    }

    @Test
    public void next() {
        var returnedNode = (DoublyLinkedList.Node) this.iterator.next();
        var secondReturnedNode = (DoublyLinkedList.Node) this.iterator.next();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(returnedNode.getItem()).isEqualTo(this.ts1);
            softly.assertThat(secondReturnedNode.getItem()).isEqualTo(this.ts2);
        });
    }

    @Test
    public void hasNextFalse() {
        this.iterator.next();
        this.iterator.next();
        this.iterator.next();
        this.iterator.next();
        assertThat(this.iterator.hasNext()).isTrue();
    }
}
