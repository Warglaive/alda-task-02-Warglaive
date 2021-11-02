package appointmentplanner;

import java.util.Iterator;

public class ReverseIterator<T> implements Iterator {
    private DoublyLinkedList.Node currentNode;
    private DoublyLinkedList.Node head;

    public ReverseIterator(DoublyLinkedList.Node head, DoublyLinkedList.Node tail) {
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
