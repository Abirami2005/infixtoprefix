import java.util.Stack;

public class InfixToPrefix {
    // Method to check if a character is an operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Method to get the precedence of an operator
    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    // Method to reverse a string
    private static String reverse(String input) {
        StringBuilder reversed = new StringBuilder(input).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(') {
                reversed.setCharAt(i, ')');
            } else if (reversed.charAt(i) == ')') {
                reversed.setCharAt(i, '(');
            }
        }
        return reversed.toString();
    }

    // Method to convert infix to prefix
    public static String infixToPrefix(String infix) {
        // Reverse the infix expression
        String reversedInfix = reverse(infix);
        Stack<Character> stack = new Stack<>();
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);

            // If the character is an operand, add it to the result
            if (Character.isLetterOrDigit(c)) {
                prefix.append(c);
            }
            // If the character is '(', push it to the stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the character is ')', pop and append until '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    prefix.append(stack.pop());
                }
                stack.pop(); // Pop '('
            }
            // If the character is an operator
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    prefix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            prefix.append(stack.pop());
        }

        // Reverse the result to get the final prefix expression
        return prefix.reverse().toString();
    }

    public static void main(String[] args) {
        String infix = "A+B*(C^D-E)^(F+G*H)-I";
        String prefix = infixToPrefix(infix);
        System.out.println("Infix Expression: " + infix);
        System.out.println("Prefix Expression: " + prefix);
    }
}
