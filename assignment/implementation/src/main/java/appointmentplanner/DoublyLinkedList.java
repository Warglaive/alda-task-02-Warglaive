package appointmentplanner;

public class DoublyLinkedList<T> {
    AllocationNode<T> head;
    AllocationNode<T> tail;
    private int size;


    /**
     * Initially, head and tail is set to null
     */
    public DoublyLinkedList() {
        this.head = new AllocationNode<>(null);
        this.tail = new AllocationNode<>(null);

        this.head.next = this.tail;
        this.tail.previous = this.head;

        this.head.previous = null;
        this.tail.next = null;
        this.size = 0;
    }

    public AllocationNode<T> getHead() {
        return this.head;
    }

    public AllocationNode<T> getTail() {
        return this.tail;
    }

    /**
     * add item at start of the LinkedList
     *
     * @param item
     */
    public void addHead(T item) {
        addBefore(item, this.head);
    }

    /**
     * add item at the end of the LinkedList
     *
     * @param item
     */
    public void addTail(T item) {
        addBefore(item, this.tail);
    }

    /**
     * @param item
     * @return
     */
    private AllocationNode<T> searchItemNode(T item) {
        AllocationNode<T> currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            currentNode = currentNode.next;
            //TODO: Check if item is null
            if (currentNode.getItem().equals(item)) {
                return currentNode;
            }
        }
        return null;
    }

    /**
     * find item and add nextItem after it in the LinkedList
     *
     * @param item
     * @param nextItem
     */
    private void addBefore(T item, T nextItem) {
        var nextNode = searchItemNode(nextItem);
        addBefore(item, nextNode);
    }

    public void addBefore(T item, AllocationNode nextNode) {
    }
}