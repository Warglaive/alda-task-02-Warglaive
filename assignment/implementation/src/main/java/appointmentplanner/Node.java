package appointmentplanner;

public class Node<E> {

    private E data;
    Node prev;
    Node next;

    public Node(E data) {
        this.data = data;
    }
}
