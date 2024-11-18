public class CircularQueue {
    private final String[] elements; // Array para armazenar os elementos da fila
    private final int capacity;      // Tamanho físico máximo da fila
    private int currentSize;         // Quantidade atual de elementos na fila
    private int front;               // Índice do primeiro elemento
    private int rear;                // Índice do último elemento

    // Construtor que inicializa a fila com a capacidade fornecida
    public CircularQueue(int capacity) throws Exception {
        if (capacity <= 0) throw new Exception("A fila deve ser inicializada com uma capacidade maior que zero.");
        this.capacity = capacity;
        this.currentSize = 0;
        this.elements = new String[capacity];
        this.front = -1; // Inicialmente, a fila está vazia
        this.rear = -1;
    }

    // Método para adicionar um elemento à fila
    public void enqueue(String item) throws Exception {
        if (currentSize == capacity) throw new Exception("A fila está cheia."); // Verifica se há espaço disponível

        // Se a fila estiver vazia, inicializa os ponteiros de frente e trás
        if (front == -1 && rear == -1) {
            front = rear = 0;
            elements[rear] = item;
            currentSize++;
            return;
        }

        // Incrementa o índice de trás (com comportamento circular)
        rear = (rear + 1) % capacity;
        elements[rear] = item; // Armazena o elemento no índice calculado
        currentSize++; // Atualiza o tamanho lógico da fila
    }

    // Método para remover e retornar o elemento da frente da fila
    public String dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Não há elementos para remover."); // Verifica se a fila está vazia

        String removedElement = elements[front]; // Armazena o elemento que será removido

        // Incrementa o índice da frente (com comportamento circular)
        front = (front + 1) % capacity;
        currentSize--; // Atualiza o tamanho lógico da fila

        return removedElement; // Retorna o elemento removido
    }

    // Retorna a capacidade total da fila
    public int getCapacity() {
        return capacity;
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return currentSize == 0;
    }
}
