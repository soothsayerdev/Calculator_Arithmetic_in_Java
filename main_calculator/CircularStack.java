public class CircularStack {
    private final String[] elements; // Array que armazena os elementos da pilha
    private final int capacity;      // Capacidade máxima da pilha
    private int topIndex;            // Índice do elemento no topo da pilha

    // Construtor para inicializar a pilha com a capacidade fornecida
    public CircularStack(int capacity) throws Exception {
        if (capacity <= 0) throw new Exception("A pilha deve ser inicializada com uma capacidade maior que zero.");
        this.capacity = capacity;
        this.elements = new String[capacity];
        this.topIndex = -1; // Pilha inicializada como vazia
    }

    // Método para adicionar um elemento ao topo da pilha
    public void push(String item) throws Exception {
        if (topIndex + 1 == capacity) throw new Exception("A pilha está cheia."); // Verifica se há espaço disponível
        elements[++topIndex] = item; // Incrementa o índice do topo e adiciona o elemento
    }

    // Método para remover e retornar o elemento do topo da pilha
    public String pop() throws Exception {
        if (isEmpty()) throw new Exception("Não há elementos para remover."); // Verifica se a pilha está vazia
        String removedElement = elements[topIndex]; // Armazena o elemento do topo
        elements[topIndex--] = null; // Remove o elemento e decrementa o índice do topo
        return removedElement; // Retorna o elemento removido
    }

    // Método para retornar o elemento no topo da pilha sem removê-lo
    public String peek() throws Exception {
        if (isEmpty()) throw new Exception("A pilha está vazia."); // Verifica se a pilha está vazia
        return elements[topIndex]; // Retorna o elemento no topo
    }

    // Método para verificar se a pilha está vazia
    public boolean isEmpty() {
        return topIndex == -1; // Retorna verdadeiro se não houver elementos
    }
}
