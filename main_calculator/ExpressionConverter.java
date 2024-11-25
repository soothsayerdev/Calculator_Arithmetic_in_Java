public class ExpressionConverter {
    private final String expression;

    public ExpressionConverter(String expression) throws IllegalArgumentException {
        if (expression.isBlank()) throw new IllegalArgumentException("Expressão vazia! Forneça uma expressão válida.");
        this.expression = expression;
    }

    public LinkedListQueue<CharSequence> toPostfix() {
        validate();

        LinkedListQueue<CharSequence> outputQueue = new LinkedListQueue<>();  // Fila para a expressão pós-fixa
        LinkedListStack<CharSequence> operatorStack = new LinkedListStack<>(); // Pilha para os operadores

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            // Ignorar espaços em branco
            if (Character.isWhitespace(currentChar)) {
                continue;
            }

            // Se o caractere for um número (operando), adicione à fila
            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder operand = new StringBuilder();
                // Lê o número completo, incluindo decimais
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--; // Compensa o incremento extra do loop
                outputQueue.enqueue(operand.toString());
            }
            // Se o caractere for um operador
            else if (isOperator(currentChar)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.getTop().charAt(0)) >= precedence(currentChar)) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.push(String.valueOf(currentChar));
            }
            // Se o caractere for um parêntese de abertura
            else if (currentChar == '(') {
                operatorStack.push(String.valueOf(currentChar));
            }
            // Se o caractere for um parêntese de fechamento
            else if (currentChar == ')') {
                while (!operatorStack.isEmpty() && operatorStack.getTop().charAt(0) != '(') {
                    outputQueue.enqueue(operatorStack.pop());
                }
                if (operatorStack.isEmpty() || operatorStack.getTop().charAt(0) != '(') {
                    throw new IllegalArgumentException("Parênteses não balanceados!");
                }
                operatorStack.pop(); // Remove o parêntese de abertura da pilha
            }
        }

        // Desempilhar os operadores restantes
        while (!operatorStack.isEmpty()) {
            outputQueue.enqueue(operatorStack.pop());
        }

        return outputQueue;
    }

    // Método auxiliar para verificar se o caractere é um operador
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Método auxiliar para definir a precedência dos operadores
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    public void validate() {
        if (containsInvalidCharacters()) {
            throw new IllegalArgumentException("Valor e/ou operador inválidos!");
        }
        if (hasConsecutiveOperators()) {
            throw new IllegalArgumentException("A expressão não pode conter um operador seguido de outro!");
        }
        if (startsOrEndsWithArithmeticOperator()) {
            throw new IllegalArgumentException("A expressão não pode iniciar/terminar com um operador!");
        }
        if (!areParenthesesCorrectlyEnclosedAndBalanced()) {
            throw new IllegalArgumentException("Os parênteses na expressão não estão corretamente balanceados e/ou fechados!");
        }
    }

    private boolean containsInvalidCharacters() {
        return !expression.matches("^(\\s*[0-9]+(\\.[0-9]*)?|\\s*\\.[0-9]+|[+\\-*/^()\\s])*\\s*$");
    }

    private boolean hasConsecutiveOperators() {
        return expression.matches(".*[+\\-*/^]\\s*[+\\-*/^].*");
    }

    private boolean startsOrEndsWithArithmeticOperator() {
        return expression.matches("^[+\\-*/^].*") || expression.matches(".*[+\\-*/^]$");
    }

    private boolean areParenthesesCorrectlyEnclosedAndBalanced() {
        int balance = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }
}
