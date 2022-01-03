package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TestNode {
    private DoublyLinkedList<TimeSlot> doublyLinkedList;

    @BeforeEach
    private void setUp() {
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Test
    public void testSetPrevious() {
        DoublyLinkedList.Node mockedNode = mock(DoublyLinkedList.Node.class);

        this.doublyLinkedList.getTail().setPrevious(mockedNode);

        assertThat(this.doublyLinkedList.getTail().getPrevious())
                .isEqualTo(mockedNode);
    }

    @Test
    public void testSetNext() {
        DoublyLinkedList.Node mockedNode = mock(DoublyLinkedList.Node.class);

        this.doublyLinkedList.getHead().setNext(mockedNode);

        assertThat(this.doublyLinkedList.getHead().getNext())
                .isEqualTo(mockedNode);
    }

    @Test
    public void replaceItem() {
        var mockedTimeSlot = mock(TimeSlot.class);
        var replacedMockedTimeSlot = mock(TimeSlot.class);
        this.doublyLinkedList.addFront(mockedTimeSlot);

        var node = this.doublyLinkedList.getHead().getNext();
        node.setItem(replacedMockedTimeSlot);

        assertThat(node.getItem()).isEqualTo(replacedMockedTimeSlot);
    }
}
