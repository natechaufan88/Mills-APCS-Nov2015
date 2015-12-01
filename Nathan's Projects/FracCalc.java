/* Nathan Chau
  * November 30, 2015
  * Fractional Calculator
  */

import java.util.*; 
 
public class FracCalc {
    public static void main(String[] args) {
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
        int firstImproperNumerator = convertToImproperFractionNumerator(firstWhole, firstNumerator, firstDenominator);
        int secondImproperNumerator = convertToImproperFractionNumerator(secondWhole, secondNumerator, secondDenominator);
        //int commonDenominator = getCommonDenominator(firstDenominator, secondDenominator);
        int adjustedFirstImproperNumerator = adjustImproperNumerator(firstNumerator, secondDenominator);
        int adjustedSecondImproperNumerator = adjustImproperNumerator(secondNumerator, firstDenominator);
        int finalImproperNumerator = mathNumerators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        int finalDenominator = mathDenominators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        int finalWhole;
        if (finalImproperNumerator != 0 && finalImproperNumerator > finalDenominator) {
            finalWhole = getWholeFromImproper(finalImproperNumerator, finalDenominator);
        } else {
            finalWhole = 0;
        }
        String finalNumerator = String.valueOf(getFinalNumerator(finalImproperNumerator, finalDenominator));
        if (Integer.parseInt(finalNumerator) != 0) {
            return String.valueOf(finalWhole) + "_" + finalNumerator + "/" + finalDenominator;
        } else {
            return String.valueOf(finalWhole);
        }
    }
    
    /*
    public static String simplifyNumerator(int numerator, int denominator) {
        while (numerator % 2 == 0 && denominator % 2 == 0) { // i.e. 4/6
            numerator /= 2;
            denominator /= 2;
        }
        if (numerator % 2 != 0 && denominator % 2 != 0) { // i.e. 3/9, 15/25
            if (denominator % numerator == 0) { // for fractions 1/#
                numerator /= numerator;
                denominator /= numerator;
            } 
        }
        if (numerator % 2 != 0 && denominator % 2 == 0) { // i.e. 3/6
            if (denominator % numerator == 0) { // for fractions 1/#
                numerator /= numerator;
                denominator /= numerator;
            }
        }
        if (numerator % 2 == 0 && denominator % 2 != 0) { //i.e. 6/9, 18/27
            
        }
        return String.valueOf(numerator);
    }
    
    public static String simplifyDenominator(int numerator, int denominator) {
        while (numerator % 2 == 0 && denominator % 2 == 0) { // i.e. 4/6
            numerator /= 2;
            denominator /= 2;
        }
        if (numerator % 2 != 0 && denominator % 2 != 0) { // i.e. 3/9, 15/25
            if (denominator % numerator == 0) { // for fractions 1/#
                numerator /= numerator;
                denominator /= numerator;
            }
        }
        if (numerator % 2 != 0 && denominator % 2 == 0) { // i.e. 3/6
            if (denominator % numerator == 0) { // for fractions 1/#
                numerator /= numerator;
                denominator /= numerator;
            }
        }
        if (numerator % 2 == 0 && denominator % 2 != 0) { //i.e. 6/9, 18/27
            
        }
        return String.valueOf(denominator);
    }
    */
   
    public static int getCommonDenominator(int firstDenominator, int secondDenominator) {
        return firstDenominator * secondDenominator;
    }
    
    public static int adjustImproperNumerator(int numerator, int otherDenominator) {
        return numerator * otherDenominator;
    }
    
    public static int getFinalNumerator(int improperNumerator, int denominator) {
        return improperNumerator % denominator;
    }
    
    public static int convertToImproperFractionNumerator(int whole, int numerator, int denominator) {
        numerator = (denominator * whole) + numerator;
        return numerator;
    }
    
    public static int getWholeFromImproper(int improperNumerator, int denominator) {
        return improperNumerator/denominator;
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