package appointmentplanner;

import java.util.Iterator;

public class ReverseIterator<T> implements Iterator {
    private DoublyLinkedList<T>.Node<T> head, currentNode;

    public ReverseIterator(DoublyLinkedList<T>.Node<T> head, DoublyLinkedList<T>.Node<T> tail) {
        this.head = head;
        currentNode = tail;
    }

    @Override
    public boolean hasNext() {
        return (currentNode.getPrevious() != head);
    }

    @Override
    public Object next() {
        currentNode = currentNode.getPrevious();
        return currentNode;
    }
}
