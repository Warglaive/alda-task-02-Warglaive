package appointmentplanner;

import java.util.Iterator;

public class GenericIterator<T> implements Iterator {

    private DoublyLinkedList.Node currentNode;
    private DoublyLinkedList.Node tail;

    public GenericIterator(DoublyLinkedList.Node head, DoublyLinkedList.Node tail) {
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
