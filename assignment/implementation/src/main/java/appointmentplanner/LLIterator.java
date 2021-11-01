package appointmentplanner;

import java.util.Iterator;

public class LLIterator<T> implements Iterator {

    private DoublyLinkedList.AllocationNode currentNode;
    private DoublyLinkedList.AllocationNode tail;

    public LLIterator(DoublyLinkedList.AllocationNode head, DoublyLinkedList.AllocationNode tail) {
        this.currentNode = head;
        this.tail = tail;
    }

    @Override
    public boolean hasNext() {
        return this.currentNode.getNext().equals(this.tail);
    }

    @Override
    public Object next() {
        this.currentNode = currentNode.getNext();
        return this.currentNode;
    }
}
