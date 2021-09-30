package appointmentplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NodeTest {
    private Node<String> node;

    @Test
    @BeforeEach
    void constructorTest() {
        this.node = new Node<>("test");
        assertThat(node).isExactlyInstanceOf(Node.class);
    }

    @Test
    void nextTest() {
        Node<String> newValue = new Node<>("newValue");
        this.node.next(newValue);
        Node<String> actual = this.node.getNext();
        Node<String> expected = new Node<>("newValue");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getPrevNullTest() {
        Node<String> newValue = null;
        this.node.prev(newValue);
        Node<String> actual = this.node.getPrev();
        assertThat(actual).usingRecursiveComparison().isNull();
    }


    @Test
    void getPrevNotNullTest() {
        Node<String> newValue = new Node<>("newValue");
        this.node.prev(newValue);
        Node<String> actual = this.node.getPrev();
        Node<String> expected = new Node<>("newValue");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getDataTest() {
        String expectedData = "test";
        assertThat(this.node.getData()).isEqualTo(expectedData);
    }
}
