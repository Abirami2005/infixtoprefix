import java.util.Stack;

public class InfixToPostfix {
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

    // Method to check the associativity of an operator
    private static boolean isRightAssociative(char c) {
        return c == '^'; // '^' is right-associative
    }

    // Method to convert infix to postfix
    public static String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // If the character is an operand, add it to the result
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            }
            // If the character is '(', push it to the stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the character is ')', pop and append until '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Pop '('
            }
            // If the character is an operator
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    if (precedence(stack.peek()) == precedence(c) && isRightAssociative(c)) {
                        break;
                    } else {
                        postfix.append(stack.pop());
                    }
                }
                stack.push(c);
            }
        }

        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        String infix = "A+B*(C^D-E)^(F+G*H)-I";
        String postfix = infixToPostfix(infix);
        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + postfix);
    }
}
