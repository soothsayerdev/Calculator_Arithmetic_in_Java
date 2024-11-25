import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ReversePolishNotationConverter {
    private final LinkedListStack<CharSequence> operators = new LinkedListStack<>();
    private final LinkedListQueue<CharSequence> output = new LinkedListQueue<>();
    private final CharSequence[] tokens;

    public ReversePolishNotationConverter(String expression) {
        tokens = getTokens(expression);
    }

    public LinkedListQueue<CharSequence> convert() throws Exception {
        Pattern numPattern = Pattern.compile("^[0-9]");
        Pattern opPattern = Pattern.compile("^[*/^+-]$");

        // 2.1.
        for (CharSequence token : tokens) {
            // Operators stack is not empty after getting the tokens
            if (token == null) {
                break;
            }

            // 2.1.1. Matches open parentheses '('
            if (token.charAt(0) == '(') {
                operators.push(token);
                continue;
            }

            // 2.1.2. Matches numbers
            if (numPattern.matcher(token).find()) {
                output.enqueue(token);
                continue;
            }

            // 2.1.3. Matches operators
            if (opPattern.matcher(token).find()) {
                // No need to check the table as the operators stack is empty
                if (operators.isEmpty()) {
                    operators.push(token);
                    continue;
                }

                // If matches 'T' on the table, enqueue the last operator from the operators stack to the output queue
                if (arithmeticOperatorTableCheck(
                        token /* operator taken from the sequence */,
                        operators.getTop()) /* last operator added to the operators stack */) {
                    output.enqueue(operators.pop());
                } else if (!operators.isEmpty() && operators.getTop().equals(token)) {
                    output.enqueue(operators.pop());
                    operators.push(token);
                    continue;
                }

                operators.push(token);
                continue;
            }

            // 2.1.4. Matches closing parenthesis ')'.
            // Enqueue operators from the operators stack until the opening parenthesis is found
            while (!operators.getTop().equals("(")) {
                output.enqueue(operators.pop());
            }
            // Discard opening parenthesis '(' from the operators stack
            operators.pop();
        }

        // 2.2. Enqueues operators from the stack until it is empty
        while (!operators.isEmpty()) {
            output.enqueue(operators.pop());
        }

        // 2.3.
        return output;
    }

    private static boolean arithmeticOperatorTableCheck(CharSequence token, CharSequence topOfStack) {
        Pattern timesDivisionPattern = Pattern.compile("^[*/^]$");
        Pattern plusMinusPattern = Pattern.compile("^[()]$");
        return switch (token.toString()) {
            // 'T' when token is '*', '/' or '^'
            case "*", "/" -> timesDivisionPattern.matcher(topOfStack).find();
            // 'T' for all cases, except when token is '(' or ')'
            case "+", "-" -> !plusMinusPattern.matcher(topOfStack).find();
            // Always 'T', unless the token is an opening parenthesis
            case ")" -> !topOfStack.equals(")");
            // Opening parenthesis '(' and exponentiation '^', the remaining possible operators, are always 'F'
            default -> false;
        };
    }

    private static CharSequence[] getTokens(String expression) {
        expression = expression.replaceAll("\\s+", "");

        StringTokenizer st = new StringTokenizer(expression, "+-*/^()", true);
        CharSequence[] tokens = new CharSequence[expression.length()];

        for (int i = 0; i < expression.length(); i++) {
            if (st.hasMoreTokens()) {
                tokens[i] = st.nextToken();
            }
        }

        return tokens;
    }
}