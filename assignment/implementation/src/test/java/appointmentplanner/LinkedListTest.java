package appointmentplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LinkedListTest {
    private LinkedListImpl.Node<String> node;

    @Test
    @BeforeEach
    void constructorTest() {
        this.node = new LinkedListImpl.Node<>("test");
        assertThat(node).isExactlyInstanceOf(LinkedListImpl.Node.class);
    }

    @Test
    void nextTest() {
        LinkedListImpl.Node<String> newValue = new LinkedListImpl.Node<>("newValue");
        this.node.next(newValue);
        LinkedListImpl.Node<String> actual = this.node.getNext();
        LinkedListImpl.Node<String> expected = new LinkedListImpl.Node<>("newValue");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getPrevNullTest() {
        LinkedListImpl.Node<String> newValue = null;
        this.node.prev(newValue);
        LinkedListImpl.Node<String> actual = this.node.getPrev();
        assertThat(actual).usingRecursiveComparison().isNull();
    }


    @Test
    void getPrevNotNullTest() {
        LinkedListImpl.Node<String> newValue = new LinkedListImpl.Node<>("newValue");
        this.node.prev(newValue);
        LinkedListImpl.Node<String> actual = this.node.getPrev();
        LinkedListImpl.Node<String> expected = new LinkedListImpl.Node<>("newValue");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getDataTest() {
        String expectedData = "test";
        assertThat(this.node.getData()).isEqualTo(expectedData);
    }
}
