public class LinkedListQueue<T> {
    private SinglyLinkedList<T> queue = new SinglyLinkedList<T>();

    public void enqueue(T element) {
        queue.addAtEnd(element);
    }

    public T dequeue() {
        T head = queue.getFirst();
        queue.remove(0);
        return head;
    }

    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
