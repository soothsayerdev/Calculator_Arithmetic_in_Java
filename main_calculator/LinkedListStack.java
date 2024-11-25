public class LinkedListStack<T> {
    private SinglyLinkedList<T> stack = new SinglyLinkedList<>();

    public void push(T element) {
        stack.addAtBeginning(element);
    }

    public T pop() {
        T top = stack.getFirst();
        stack.remove(0);
        return top;
    }

    public T getTop() {
        return stack.getFirst();
    }

    public boolean isEmpty() {
        return stack.getSize() == 0;
    }

    @Override
    public String toString() {
        return stack.toString();
    }

}