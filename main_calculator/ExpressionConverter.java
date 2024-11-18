import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ExpressionConverter {
    private final CircularStack operatorStack; // Pilha para armazenar operadores
    private final CircularQueue outputQueue;   // Fila para armazenar a saída em notação pós-fixa
    private final String[] tokens;          // Tokens da expressão de entrada

    // Construtor que inicializa as estruturas e divide a expressão em tokens
    public ExpressionConverter(String expression) throws Exception {
        tokens = tokenizeExpression(expression); // Divide a expressão em partes
        int capacity = tokens.length; // Determina a capacidade necessária para as estruturas
        operatorStack = new CircularStack(capacity); // Inicializa a pilha
        outputQueue = new CircularQueue(capacity);   // Inicializa a fila
    }

    // Método que verifica precedência entre operadores
    private static boolean hasHigherPrecedence(String top, String current) {
        // Define precedência dos operadores
        int topPrecedence = getPrecedence(top);
        int currentPrecedence = getPrecedence(current);

        // Operadores no topo da pilha têm maior ou igual precedência que o operador atual
        return topPrecedence >= currentPrecedence;
    }

    // Retorna o nível de precedência dos operadores
    private static int getPrecedence(String operator) {
        return switch (operator) {
            case "^" -> 3; // Potência
            case "*", "/" -> 2; // Multiplicação e divisão
            case "+", "-" -> 1; // Soma e subtração
            default -> 0; // Qualquer outro caso (por exemplo, parênteses)
        };
    }

    // Divide a expressão em tokens (números, operadores, parênteses)
    private static String[] tokenizeExpression(String expression) {
        String cleanedExpression = expression.replaceAll("\\s+", ""); // Remove espaços
        StringTokenizer tokenizer = new StringTokenizer(cleanedExpression, "+-*/^()", true);
        String[] result = new String[cleanedExpression.length()];

        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            result[index++] = tokenizer.nextToken();
        }

        return result;
    }

    // Método que converte a expressão para a notação pós-fixa (RPN)
    public CircularQueue toPostfix() throws Exception {
        Pattern numberPattern = Pattern.compile("\\d+"); // Reconhece números
        Pattern operatorPattern = Pattern.compile("[+\\-*/^]"); // Reconhece operadores

        for (String token : tokens) {
            if (token == null) break;

            // Caso o token seja um número, adiciona diretamente na fila de saída
            if (numberPattern.matcher(token).matches()) {
                outputQueue.enqueue(token);
                continue;
            }

            // Caso o token seja um operador
            if (operatorPattern.matcher(token).matches()) {
                // Enquanto o topo da pilha tiver operadores de maior ou igual precedência,
                // move-os para a fila de saída
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), token)) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.push(token); // Adiciona o operador atual na pilha
                continue;
            }

            // Caso o token seja um parêntese de abertura
            if (token.equals("(")) {
                operatorStack.push(token); // Apenas coloca na pilha
                continue;
            }

            // Caso o token seja um parêntese de fechamento
            if (token.equals(")")) {
                // Move operadores para a fila de saída até encontrar o parêntese de abertura
                while (!operatorStack.peek().equals("(")) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.pop(); // Remove o parêntese de abertura da pilha
            }
        }

        // Após processar todos os tokens, esvazia a pilha de operadores na fila de saída
        while (!operatorStack.isEmpty()) {
            outputQueue.enqueue(operatorStack.pop());
        }

        return outputQueue; // Retorna a fila com a expressão em notação pós-fixa
    }
}
