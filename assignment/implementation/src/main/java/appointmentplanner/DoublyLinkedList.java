package appointmentplanner;

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
     * OLD CRAP
     *
     * @param item
     */
    //add a node to the list
    public void addNode(T item) {
        //Create a new node containing the item
        AllocationNode<T> newNode = new AllocationNode<>(item);
        //if list is empty, head and tail points to newNode
        if (this.head == null) {
            //this.head = this.tail = newNode;
            this.tail = newNode;
            this.head = this.tail;
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
        this.size++;
    }

    public int getSize() {
        return size;
    }
}

    /*    //print all the nodes of the doubly linked list
        public void printNodes() {
            //Node current will point to head
            AllocationNode<T> current = this.head;
            if (this.head == null) {
                System.out.println("Doubly linked list is empty");
                return;
            }
            System.out.println("Nodes of doubly linked list: ");
            while (current != null) {
                //Print each node and then go to next.

                System.out.println(current.item + " ");
                current = current.next;
            }
        }*/


/*
class Main {
    public static void main(String[] args) {
        //create a DoublyLinkedList object
        DoublyLinkedList dl_List = new DoublyLinkedList();
        //Add nodes to the list
        dl_List.addNode(10);
        dl_List.addNode(20);
        dl_List.addNode(30);
        dl_List.addNode(40);
        dl_List.addNode(50);

        //print the nodes of DoublyLinkedList
        dl_List.printNodes();
    }
}*/
