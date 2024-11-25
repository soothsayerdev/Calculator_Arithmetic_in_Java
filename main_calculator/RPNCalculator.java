public class RPNCalculator {
    private final LinkedListQueue<CharSequence> outputQueue;
    private final LinkedListStack<CharSequence> resultStack = new LinkedListStack<>();

    public RPNCalculator(LinkedListQueue<CharSequence> outputQueue) {
        this.outputQueue = outputQueue;
    }

    public double getResult() throws Exception {
        double v1, v2 = 0;
        char operator;

        while (!outputQueue.isEmpty()) {
            CharSequence element = outputQueue.dequeue();

            // Try to parse the dequeued element as a number and push it to the stack
            try {
                Double.parseDouble(element.toString());
                resultStack.push(element);
            }
            // If the dequeued element is an operator, perform the calculation
            catch (NumberFormatException e) {
                operator = element.charAt(0);
                v2 = Double.parseDouble(resultStack.pop().toString());
                v1 = Double.parseDouble(resultStack.pop().toString());
                Double result = switch (operator) {
                    case '+' -> v1 + v2;
                    case '-' -> v1 - v2;
                    case '*' -> v1 * v2;
                    case '/' -> v1 / v2;
                    default -> Math.pow(v1, v2);
                };
                // Add the calculated value back to the result stack
                resultStack.push(String.valueOf(result));
            }
        }

        // Return the final result (top of the stack) as a double
        return Double.parseDouble(resultStack.pop().toString());
    }
}
