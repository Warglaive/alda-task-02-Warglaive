package appointmentplanner;

/**
 * A node class for doubly linked list
 *
 * @param <T>
 */
public class AllocationNode<T> {
    T item;
    public AllocationNode<T> previous;
    public AllocationNode<T> next;

    public AllocationNode(T item) {
        this.item = item;
    }
}