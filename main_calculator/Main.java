import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicita a expressão matemática ao usuário
        System.out.println("Por favor, insira uma expressão matemática:");
        String expression = scanner.nextLine(); // Lê a expressão do terminal

        try {
            // Converte a expressão fornecida em notação pós-fixa
            ExpressionConverter converter = new ExpressionConverter(expression);
            LinkedListQueue<CharSequence> postfixExpression = converter.toPostfix();

            // Calcula o resultado da expressão pós-fixa
            RPNCalculator calculator = new RPNCalculator(postfixExpression);
            double result = calculator.getResult();

            // Exibe o resultado final
            System.out.println("Resultado: " + result);
        } catch (Exception e) {
            // Trata erros, como expressões inválidas ou divisões por zero
            System.out.println("Erro ao calcular a expressão: " + e.getMessage());
        }
    }
}
