package appointmentplanner;

import java.util.Iterator;

public class LLIterator<T> implements Iterator<T> {

    private AllocationNode<T> currentNode;
    private AllocationNode<T> tail;

    public LLIterator(AllocationNode<T> head, AllocationNode<T> tail) {
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
        currentNode = currentNode.getNext();
        return currentNode;
    }
}
