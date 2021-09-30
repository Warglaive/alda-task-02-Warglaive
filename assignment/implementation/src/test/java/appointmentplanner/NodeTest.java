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
        var expected = new Node<>("newValue");
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
