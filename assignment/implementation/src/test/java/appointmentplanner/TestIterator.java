package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIterator {
    @Mock
    TimeSlot ts, ts2, ts3, ts4;

    private DoublyLinkedList<TimeSlot> doublyLinkedList;
    private GenericIterator iterator;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.openMocks(this);
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.doublyLinkedList.addFront(this.ts4);
        this.doublyLinkedList.addFront(this.ts3);
        this.doublyLinkedList.addFront(this.ts2);
        this.doublyLinkedList.addFront(this.ts);

        iterator = new GenericIterator(this.doublyLinkedList.getHead(), this.doublyLinkedList.getTail());
    }

    @Test
    public void hasNextTrue() {
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    public void next() {
        var returnedNode = (DoublyLinkedList.Node) this.iterator.next();
        var secondReturnedNode = (DoublyLinkedList.Node) this.iterator.next();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(returnedNode.getItem()).isEqualTo(this.ts);
            softly.assertThat(secondReturnedNode.getItem()).isEqualTo(this.ts2);
        });
    }

    @Test
    public void hasNextFalse() {
        this.iterator.next();
        this.iterator.next();
        this.iterator.next();
        this.iterator.next();
        assertThat(this.iterator.hasNext()).isFalse();
    }
}
