/* Nathan Chau
 * APCS 1st period
 * 11/19/15
 * Fractional Calculator
 */

import java.util.*;

public class FractionalCalculator {
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);
        System.out.print("What is your expression? "); // prompt user for input
        String expression = userinput.nextLine(); // user types in input
        while (!(expression.equals("quit"))) { // while user input is not "quit"
            System.out.println(produceAnswer(expression));
            System.out.print("What is your expression? "); // prompt user for input
            expression = userinput.nextLine(); // user types in input
        }
        System.out.println("You may now exit.");
    }
    
    public static String produceAnswer(String expression) {
        // assign first operand, operator, and second operand to variables
        String firstFraction = parseExpressionForFirstFraction(expression);
        String operator = parseExpressionForOperator(expression);
        String secondFraction = parseExpressionForSecondFraction(expression);
        return parseFraction(secondFraction);
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
        // method returns String containing second fraction
        int space = expression.indexOf(" ");
        String frac1 = expression.substring(0, space);
        String operator = expression.substring(space + 1, space + 2);
        int length = expression.length();
        String frac2 = expression.substring(space + 3, length);
        return frac2;
    }
    
    public static String parseFraction(String fraction) {
        int length = fraction.length();
        int underscore = fraction.indexOf("_");
        int slash = fraction.indexOf("/");
        String whole = "0"; // default
        String numerator = "0"; // default
        String denominator = "1"; // default
        if (underscore >= 0) {
            whole = fraction.substring(0, underscore);
            if (slash >= 0) { // underscore and a slash ==> mixed number
                numerator = fraction.substring(underscore + 1, slash);
                denominator = fraction.substring(slash + 1, length);
            }
        } else {
            if (slash >=0) { // no underscore and a slash ==> fraction
                whole = "0";
                numerator = fraction.substring(0, slash);
                denominator = fraction.substring(slash + 1, length);
            } else { // no underscore and no slash ==> whole number
                whole = fraction.substring(0, length);
                numerator = "0";
                denominator = "1";
            }
        }
        return "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
    }
}