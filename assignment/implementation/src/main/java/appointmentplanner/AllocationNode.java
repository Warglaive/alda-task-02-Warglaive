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

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public AllocationNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(AllocationNode<T> previous) {
        this.previous = previous;
    }

    public AllocationNode<T> getNext() {
        return next;
    }

    public void setNext(AllocationNode<T> next) {
        this.next = next;
    }
}