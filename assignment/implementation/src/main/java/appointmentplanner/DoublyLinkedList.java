package appointmentplanner;

import java.util.Objects;

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
     * find an item and add nextItem before it in the LinkedList
     *
     * @param item
     * @param nextItem
     */
    private void addBefore(T item, T nextItem) {
        AllocationNode<T> nextNode = searchItemNode(nextItem);
        addBefore(item, nextNode);
    }

    /**
     * Create new node from with item and set newNode.next to nextNode.
     *
     * @param item
     * @param nextNode
     */
    public void addBefore(T item, AllocationNode nextNode) {
        if (Objects.nonNull(nextNode) && Objects.nonNull(item)) {
            AllocationNode newNode = new AllocationNode(item);

            newNode.setNext(nextNode);
        }
    }

    /**
     * get previous item from node.
     * addAfter previous node
     * @param item
     * @param previousItem
     */
    public void addAfter(T item, T previousItem) {
        var previousNode = searchItemNode(previousItem);

        if (previousNode != null) {
            addAfter(item, previousNode);
        }
        throw new NullPointerException("AddAfter Node is null");
    }


    public void addAfter(T item, AllocationNode<T> beforeNode) {
        addBefore(item, beforeNode.next);
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


}