package appointmentplanner;

public class Node<E> {
    private Node<E> prev;
    private E data;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
    }

    void next(Node<E> next) {
        this.next = next;
    }

    /**
     * Getters for test purposes
     */
    public Node<E> getPrev() {
        return prev;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }
}
