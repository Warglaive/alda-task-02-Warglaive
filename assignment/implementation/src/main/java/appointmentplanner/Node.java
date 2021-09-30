package appointmentplanner;

public class Node<E> {
    Node prev;
    private E data;
    Node next;

    public Node(E data) {
        this.data = data;
    }

    void next(Node next) {
        this.next = next;
    }
}
