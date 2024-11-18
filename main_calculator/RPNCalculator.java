public class RPNCalculator {
    private final CircularQueue postfixQueue; // Fila contendo a expressão em notação pós-fixa
    private final CircularStack valueStack;  // Pilha para cálculo dos resultados

    // Construtor que inicializa a fila e a pilha
    public RPNCalculator(CircularQueue postfixQueue) throws Exception {
        this.postfixQueue = postfixQueue; // Recebe a fila com a notação pós-fixa
        valueStack = new CircularStack(postfixQueue.getCapacity()); // Pilha para armazenar valores durante os cálculos
    }

    // Método que calcula o resultado da expressão em notação pós-fixa
    public double calculate() throws Exception {
        double operand1, operand2 = 0; // Operandos usados nas operações
        char operator; // Operador atual

        // Processa cada elemento da fila
        while (!postfixQueue.isEmpty()) {
            String token = postfixQueue.dequeue(); // Remove o próximo elemento da fila

            try {
                // Tenta converter o token em um número
                Double.parseDouble(token);
                valueStack.push(token); // Se for número, empilha
            } catch (NumberFormatException e) {
                // Se não for número, trata como operador
                operator = token.charAt(0); // Identifica o operador
                operand2 = Double.parseDouble(valueStack.pop()); // Segundo operando (último empilhado)
                operand1 = Double.parseDouble(valueStack.pop()); // Primeiro operando (penúltimo empilhado)

                // Realiza a operação com base no operador
                Double result = switch (operator) {
                    case '+' -> operand1 + operand2;
                    case '-' -> operand1 - operand2;
                    case '*' -> operand1 * operand2;
                    case '/' -> operand1 / operand2;
                    case '^' -> Math.pow(operand1, operand2); // Potência
                    default -> throw new IllegalStateException("Operador inesperado: " + operator);
                };

                // Empilha o resultado da operação
                valueStack.push(String.valueOf(result));
            }
        }

        // Retorna o resultado final (último valor na pilha)
        return Double.parseDouble(valueStack.pop());
    }
}
