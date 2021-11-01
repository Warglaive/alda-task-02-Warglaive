package appointmentplanner;

import java.util.Iterator;

public class LLReverseIterator<T> implements Iterator {
    private DoublyLinkedList.AllocationNode currentNode;
    private DoublyLinkedList.AllocationNode head;

    public LLReverseIterator(DoublyLinkedList.AllocationNode head, DoublyLinkedList.AllocationNode tail) {
        this.head = head;
        this.currentNode = tail;
    }

    @Override
    public boolean hasNext() {
        return this.currentNode.getPrevious().equals(this.head);
    }

    @Override
    public Object next() {
        this.currentNode = currentNode.getPrevious();
        return this.currentNode;
    }
}
