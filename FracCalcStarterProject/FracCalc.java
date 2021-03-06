/* Nathan Chau
  * December 6, 2015
  * Fractional Calculator
  */

import java.util.*; 
 
public class FracCalc {
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in); // use scanner for user input
        System.out.print("What is your expression? (\"quit\" to quit) "); // prompt user for input
        String expression = userinput.nextLine(); // user types in input
        while (!(expression.equals("quit"))) { // while user input is not "quit"
            System.out.println(produceAnswer(expression));
            System.out.print("What is your expression? (\"quit\" to quit) "); // prompt user for input
            expression = userinput.nextLine(); // user types in input
        }
        System.out.println("You may now exit.");
    }
    
    public static String produceAnswer(String expression) {   
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
        int adjustedFirstImproperNumerator = adjustImproperNumerator(operator, firstImproperNumerator, secondDenominator);
        int adjustedSecondImproperNumerator = adjustImproperNumerator(operator, secondImproperNumerator, firstDenominator);
        int finalImproperNumerator = mathNumerators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        int finalDenominator = mathDenominators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        if (finalDenominator < 0 && finalImproperNumerator > 0) {
            finalImproperNumerator = -finalImproperNumerator;
            finalDenominator = -finalDenominator;
        }
        System.out.println(finalImproperNumerator);
        System.out.println(finalDenominator);
        int finalWhole;
        if (finalImproperNumerator != 0) { //&& finalImproperNumerator > finalDenominator) {
            finalWhole = getWholeFromImproper(finalImproperNumerator, finalDenominator);
        } else {
            finalWhole = 0;
        }
        String stringNumerator = String.valueOf(getFinalNumerator(finalWhole, finalImproperNumerator, finalDenominator));
        int finalNumerator = Integer.parseInt(stringNumerator);
        int numerator = reduceNumerator(finalNumerator, finalDenominator);
        int denominator = reduceDenominator(finalNumerator, finalDenominator);
        if (numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        if (numerator != 0) {
            if (finalWhole != 0) {
                return String.valueOf(finalWhole) + "_" + numerator + "/" + denominator;
            } else {
                return numerator + "/" + denominator;
            }
        } else {
            return String.valueOf(finalWhole);
        }
    }
   
    public static int reduceNumerator(int numerator, int denominator) {
        // method reduces numerator if applicable
        int start;
        int greatestCommonFactor = 1;
        if (numerator > denominator) {
            start = numerator;
        } else {
            start = denominator;
        }
        if (start != 0) {
            while (greatestCommonFactor != start) {
                if (numerator % start == 0 && denominator % start == 0) {
                    greatestCommonFactor = start;
                    start++;
                }
                start--;
            }
        }
        return numerator / greatestCommonFactor;
    }
    
    public static int reduceDenominator(int numerator, int denominator) {
        // method reduces denominator if applicable
        int start;
        int greatestCommonFactor = 1;
        if (numerator > denominator) {
            start = numerator;
        } else {
            start = denominator;
        }
        if (start != 0) {
            while (greatestCommonFactor != start) {
                if (numerator % start == 0 && denominator % start == 0) {
                    greatestCommonFactor = start;
                    start++;
                }
                start--;
            }
        }
        return denominator / greatestCommonFactor;
    }    
    
    public static int convertToImproperFractionNumerator(int whole, int numerator, int denominator) {
        // method converts numerator to a new numerator accounting for the whole numbers
        numerator = (denominator * whole) + numerator;
        return numerator;
    }
    
    public static int adjustImproperNumerator(String operator, int numerator, int otherDenominator) {
        if (operator.equals("+") || operator.equals("-")) {
            return numerator * otherDenominator;
        } else {
            return numerator;
        }
    }    

    public static int mathNumerators(int firstNumerator, int secondNumerator, int firstDenominator,
        int secondDenominator, String operator) {
        // method performs operations on numerators of both improper fractions
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
        // method performs operations on denominators of both improper fractions
        int denominator;
        if (operator.equals("+") || operator.equals("-") || operator.equals("*")) {
            denominator = firstDenominator * secondDenominator;
        } else {
            denominator = firstDenominator * secondNumerator;
        }
        return denominator;
    }
    
    public static int getWholeFromImproper(int improperNumerator, int denominator) {
        // method returns final whole number
        return improperNumerator/denominator;
    }    
    
    public static int getFinalNumerator(int whole, int improperNumerator, int denominator) {
        // method converts improper numerator to final numerator after extracting whole numbers
        if (whole <= 0) {
            if (denominator < 0 && improperNumerator > 0 || denominator > 0 && improperNumerator < 0) {
                improperNumerator = -improperNumerator;
            }
        }
        return improperNumerator % denominator;
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