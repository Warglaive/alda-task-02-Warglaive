package appointmentplanner;

/**
 * A node class for doubly linked list
 *
 * @param <T>
 */
public class AllocationNode<T> {
    T item;
    AllocationNode<T> previous;
    AllocationNode<T> next;

    public AllocationNode(T item) {
        this.item = item;
    }
}