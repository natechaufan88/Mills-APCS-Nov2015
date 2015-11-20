/* Nathan Chau
 * APCS 1st period
 * 11/19/15
 * Fractional Calculator, Checkpoint 1
 */

import java.util.*;

public class Checkpoint1 {
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);
        System.out.print("What is your expression? "); // prompt user for input
        String expression = userinput.nextLine(); // user types in input
        System.out.println(produceAnswer(expression));
    }
    
    public static String produceAnswer(String expression) {
        // assign first operand, operator, and second operand to variables
        String firstFraction = parseExpressionForFirstFraction(expression);
        String operator = parseExpressionForOperator(expression);
        String secondFraction = parseExpressionForSecondFraction(expression);
        return secondFraction;
    }
    
    public static String parseExpressionForFirstFraction(String expression) {
        // method returns String containing first fraction
        int space = expression.indexOf(" ");
        String frac1 = expression.substring(0, space);
        return frac1;
    }
    
    public static String parseExpressionForOperator(String expression) {
        // method returns String containing operator
        int space = expression.indexOf(" ");
        String frac1 = expression.substring(0, space);
        String operator = expression.substring(space + 1, space + 2);
        return operator;
    }
    
    public static String parseExpressionForSecondFraction(String expression) {
        // method returns String contatining second fraction
        int space = expression.indexOf(" ");
        String frac1 = expression.substring(0, space);
        String operator = expression.substring(space + 1, space + 2);
        int length = expression.length();
        String frac2 = expression.substring(space + 3, length);
        return frac2;
    }
}