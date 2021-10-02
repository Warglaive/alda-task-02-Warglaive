package appointmentplanner;

public class LinkedListImpl<E> {
    private Node<E> head;

    static class Node<E> {
        private Node<E> prev;
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        void next(Node<E> next) {
            this.next = next;
        }

        void prev(Node<E> prev) {
            this.prev = prev;
        }

        /**
         * Getters for test purposes
         */
        public Node<E> getPrev() {
            return prev;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
