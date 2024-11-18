import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Scanner para capturar entrada do usuário
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, insira uma expressão matemática:");
        String expression = scanner.nextLine(); // Lê a expressão do terminal

        // Converte a expressão fornecida em notação pós-fixa
        ExpressionConverter converter = new ExpressionConverter(expression);
        CircularQueue postfixExpression = converter.toPostfix();

        // Calcula o resultado da expressão pós-fixa
        RPNCalculator calculator = new RPNCalculator(postfixExpression);
        System.out.println("Resultado: " + calculator.calculate());
    }
}
