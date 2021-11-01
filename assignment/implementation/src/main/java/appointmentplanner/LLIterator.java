package appointmentplanner;

import java.util.Iterator;

public class LLIterator<T> implements Iterator<T> {

    private DoublyLinkedList.AllocationNode currentNode;
    private DoublyLinkedList.AllocationNode tail;

    public LLIterator(DoublyLinkedList.AllocationNode head, DoublyLinkedList.AllocationNode tail) {
        this.currentNode = head;
        this.tail = tail;
    }

    @Override
    public boolean hasNext() {
        //TODO: Check later with test
        return !this.currentNode.getNext().equals(this.tail);
    }

    @Override
    public T next() {
        this.currentNode = currentNode.getNext();
        //TODO: May be buggy
        return (T) this.currentNode;
    }
}
