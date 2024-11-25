public class SinglyLinkedList<T> implements Cloneable {
    private class Node implements Cloneable {
        private T info;
        private Node next;

        public Node(T info, Node next) {
            this.info = info;
            this.next = next;
        }

        public Node(T info) {
            this.info = info;
            this.next = null;
        }

        public T getInfo() {
            return this.info;
        }

        public Node getNext() {
            return this.next;
        }

        public void setInfo(T info) {
            this.info = info;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node first;
    private Node last;

    public SinglyLinkedList() {
        this.first = null;
        this.last = null;
    }

    public void addAtBeginning(T element) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Missing information");

        T copy;
        if (element instanceof Cloneable) {
            copy = new Cloner<T>().clone(element);
        } else {
            copy = element;
        }

        this.first = new Node(copy, this.first);

        if (this.last == null) {
            this.last = this.first;
        }
    }

    public void addAtEnd(T element) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Missing information");

        T copy;
        if (element instanceof Cloneable) {
            copy = new Cloner<T>().clone(element);
        } else {
            copy = element;
        }

        if (this.first == null) {
            this.first = new Node(copy, this.first);
            this.last = this.first;
            return;
        }

        Node newLast = new Node(copy, null);
        this.last.setNext(newLast);
        this.last = newLast;
    }

    public T getFirst() throws NullPointerException {
        if (this.first == null) throw new NullPointerException("List is empty");

        T copy;
        if (this.first.getInfo() instanceof Cloneable) {
            copy = new Cloner<T>().clone(this.first.getInfo());
        } else {
            copy = this.first.getInfo();
        }

        return copy;
    }

    public T getLast() throws NullPointerException {
        if (this.last == null) throw new NullPointerException("List is empty");

        T copy;
        if (this.last.getInfo() instanceof Cloneable) {
            copy = new Cloner<T>().clone(this.last.getInfo());
        } else {
            copy = this.last.getInfo();
        }

        return copy;
    }

    public int getSize() {
        if (this.first == null) {
            return 0;
        }

        Node current = this.first;
        int size = 0;

        while (current != null) {
            current = current.getNext();
            size++;
        }

        return size;
    }

    public void remove(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position >= this.getSize()) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }

        if (position == 0) {
            this.first = this.first.getNext();
        } else {
            Node current = this.first;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());
        }
    }

    public boolean contains(T element) {
        Node current = this.first;

        while (current != null) {
            if (current.getInfo().equals(element)) return true;
            current = current.getNext();
        }

        return false;
    }

    public void rotate(int count) {
        Node current = first;
        Node previousLast = null;

        for (; count != 0; count--) {
            while (current.getNext() != null) {
                if (current.getNext().getNext() == null) {
                    previousLast = current;
                }
                current = current.getNext();
            }

            current.setNext(first);
            previousLast.setNext(null);
            first = current;
        }
    }

    @Override
    public String toString() {
        if (this.first == null) return "[]";

        StringBuilder result = new StringBuilder("[");
        result.append(this.first.getInfo());

        Node current = this.first.getNext();

        while (current != null) {
            result.append(", ").append(current.getInfo());
            current = current.getNext();
        }

        result.append("]");
        return result.toString();
    }
}
