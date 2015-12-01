/* Nathan Chau
  * November 30, 2015
  * Fractional Calculator
  */

import java.util.*; 
 
public class FracCalc {
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner userinput = new Scanner(System.in);
        System.out.print("What is your expression? (\"quit\" to quit) "); // prompt user for input
        String expression = userinput.nextLine(); // user types in input
        while (!(expression.equals("quit"))) { // while user input is not "quit"
            System.out.println(produceAnswer(expression));
            System.out.print("What is your expression? (\"quit\" to quit) "); // prompt user for input
            expression = userinput.nextLine(); // user types in input
        }
        System.out.println("You may now exit.");
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    
    public static String produceAnswer(String expression) {   
        // assign first operand, operator, and second operand to variables
        String firstFraction = parseExpressionForFirstFraction(expression);
        String operator = parseExpressionForOperator(expression);
        String secondFraction = parseExpressionForSecondFraction(expression);
        int firstWhole = parseFractionForWholeNumber(firstFraction);
        int firstNumerator = parseFractionForNumerator(firstFraction);
        int firstDenominator = parseFractionForDenominator(firstFraction);
        int secondWhole = parseFractionForWholeNumber(secondFraction);
        int secondNumerator = parseFractionForNumerator(secondFraction);
        int secondDenominator = parseFractionForDenominator(secondFraction);
        int whole = mathWholes(firstWhole, secondWhole, operator);
        int numerator = mathNumerators(firstNumerator, secondNumerator, firstDenominator, secondDenominator, operator);
        int denominator = mathDenominators(firstNumerator, secondNumerator, firstDenominator, secondDenominator, operator);
        int improperNumerator = convertToImproperFractionNumerator(whole, numerator, denominator);
        int newWhole;
        if (numerator != 0 && numerator > denominator) {
            newWhole = getWholeFromImproper(improperNumerator, denominator);
        } else {
            newWhole = whole;
        }
        String newNumerator = String.valueOf(getNewNumerator(newWhole, improperNumerator, denominator));
        if (Integer.parseInt(newNumerator) != 0) {
            return String.valueOf(newWhole) + "_" + newNumerator + "/" + denominator;
        } else {
            return String.valueOf(newWhole);
        }
    }
    
    /*
    public static int leastCommonDenominator(int numerator, int denominator) {
        int i;
        if (denominator > numerator) {
            i = numerator;
        } else {
            i = denominator;
        }
        for (int j = i; j > 0; j--) {
            if (numerator % j == 0 && denominator % j == 0) {
                return j;
            }
        }
        return -1;
    }
    */
    
    public static int getWholeFromImproper(int numerator, int denominator) {
        int whole = 0;
        if (numerator > denominator) {
            while (numerator % denominator > 1) {
                whole++;
                numerator -= denominator;
            }
            whole -= 1;
        } else  if (numerator == denominator) {
            whole = 1;
        }
        return whole;
    }
    
    public static int getNewNumerator(int whole, int numerator, int denominator) {
        int a = whole * denominator;
        numerator -= a;
        return numerator;
    }
    
    public static int convertToImproperFractionNumerator(int whole, int numerator, int denominator) {
        numerator = (denominator * whole) + numerator;
        return numerator;
    }
    
        public static int mathWholes(int firstWhole, int secondWhole, String operator) {
        // method adds/subtracts/multiplies/divides whole numbers based on input operator
        int whole;
        if (operator.equals("+")) {
            whole = firstWhole + secondWhole;
        } else if (operator.equals("-")) {
            whole = firstWhole - secondWhole;
        } else if (operator.equals("*")) {
            whole = firstWhole * secondWhole;
        } else {
            if (firstWhole == 0 && secondWhole == 0) {
                whole = 0;
            } else {
                whole = firstWhole / secondWhole;
            }
        }
        return whole;
    }
    
    public static int mathNumerators(int firstNumerator, int secondNumerator, int firstDenominator,
        int secondDenominator, String operator) {
        firstNumerator *= secondDenominator;
        secondNumerator *= firstDenominator;
        int numerator;
        if (operator.equals("+")) {
            numerator = firstNumerator + secondNumerator;
        } else if (operator.equals("-")) {
            numerator = firstNumerator - secondNumerator;
        } else if (operator.equals("*")) {
            numerator = firstNumerator * secondNumerator;
        } else {
            numerator = firstNumerator * secondDenominator;
        }
        return numerator;
    }
    
    public static int mathDenominators(int firstNumerator, int secondNumerator, int firstDenominator,
        int secondDenominator, String operator) {
        int denominator;
        firstNumerator *= secondDenominator;
        secondNumerator *= firstDenominator;
        if (operator.equals("+") || operator.equals("-") || operator.equals("*")) {
            denominator = firstDenominator * secondDenominator;
        } else {
            denominator = firstDenominator * secondNumerator;
        }
        return denominator;
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
    
    public static int parseFractionForWholeNumber(String fraction) {
        // method returns integer of the whole number
        int length = fraction.length();
        int underscore = fraction.indexOf("_");
        int slash = fraction.indexOf("/");
        String whole = "0"; // default
        if (underscore != -1) { // underscore will equal -1 if the String is not found, 
                               // so this tests that an underscore exists in the String
            whole = fraction.substring(0, underscore);
        } else {
            if (slash != -1) { // no underscore and a slash ==> fraction
                whole = "0";
            } else { // no underscore and no slash ==> whole number
                whole = fraction.substring(0, length);
            }
        }
        int wholeNumber = Integer.parseInt(whole);
        return wholeNumber;
    }
    
    public static int parseFractionForNumerator(String fraction) {
        // method returns integer of numerator of a fraction
        int length = fraction.length();
        int underscore = fraction.indexOf("_");
        int slash = fraction.indexOf("/");
        String numerator = "0"; // default
        if (underscore != -1) { // underscore will equal -1 if the String is not found, 
                               // so this tests that an underscore exists in the String
            if (slash != -1) { // underscore and a slash ==> mixed number
                numerator = fraction.substring(underscore + 1, slash);
            }
        } else {
            if (slash != -1) { // no underscore and a slash ==> fraction
                numerator = fraction.substring(0, slash);
            } else { // no underscore and no slash ==> whole number
                numerator = "0";
            }
        }
        int numeratorNumber = Integer.parseInt(numerator);
        String whole = "0"; // default
        if (underscore != -1) { // underscore will equal -1 if the String is not found, 
                               // so this tests that an underscore exists in the String
            whole = fraction.substring(0, underscore);
        } else {
            if (slash != -1) { // no underscore and a slash ==> fraction
                whole = "0";
            } else { // no underscore and no slash ==> whole number
                whole = fraction.substring(0, length);
            }
        }
        int wholeNumber = Integer.parseInt(whole);
        if (wholeNumber < 0) {
            numeratorNumber = -numeratorNumber;
        }
        return numeratorNumber;
    }
    
    public static int parseFractionForDenominator(String fraction) {
        int length = fraction.length();
        int underscore = fraction.indexOf("_");
        int slash = fraction.indexOf("/");
        String denominator = "1"; // default
        if (underscore != -1) { // underscore will equal -1 if the String is not found, 
                               // so this tests that an underscore exists in the String
            if (slash != -1) { // underscore and a slash ==> mixed number
                denominator = fraction.substring(slash + 1, length);
            }
        } else {
            if (slash != -1) { // no underscore and a slash ==> fraction
                denominator = fraction.substring(slash + 1, length);
            } else { // no underscore and no slash ==> whole number
                denominator = "1";
            }
        }
        int denominatorNumber = Integer.parseInt(denominator);
        return denominatorNumber;
    }
}