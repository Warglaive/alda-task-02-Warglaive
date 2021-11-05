package appointmentplanner;

public class GenericIterator<T> implements java.util.Iterator {
    private DoublyLinkedList<T>.Node<T> tail, currentNode;

    public GenericIterator(DoublyLinkedList<T>.Node<T> head, DoublyLinkedList<T>.Node<T> tail) {
        this.tail = tail;
        currentNode = head;
    }

    @Override
    public boolean hasNext() {
        return (currentNode.getNext() != tail);
    }

    @Override
    public Object next() {
        currentNode = currentNode.getNext();
        return currentNode;
    }
}
