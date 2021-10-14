package appointmentplanner;

public class DoublyLinkedList<T> {
    //Initially, head and tail is set to null
    AllocationNode<T> head = null;
    AllocationNode<T> tail = null;

    //add a node to the list
    public void addNode(T item) {
        //Create a new node containing the item
        AllocationNode<T> newNode = new AllocationNode<>(item);
        //if list is empty, head and tail points to newNode
        if (this.head == null) {
            this.head = this.tail;
            this.tail = newNode;
            //head's previous will be null
            this.head.previous = null;
            //tail's next will be null
            this.tail.next = null;
        } else {
            //add newNode to the end of list. tail->next set to newNode
            this.tail.next = newNode;
            //newNode->previous set to tail
            newNode.previous = this.tail;
            //newNode becomes new tail
            this.tail = newNode;
            //tail's next points to null
            this.tail.next = null;
        }
    }

}