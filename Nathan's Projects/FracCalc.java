/* Nathan Chau
  * December 6, 2015
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
<<<<<<< HEAD
        int firstImproperNumerator = convertToImproperFractionNumerator(firstWhole, firstNumerator, firstDenominator);
        int secondImproperNumerator = convertToImproperFractionNumerator(secondWhole, secondNumerator, secondDenominator);
        int adjustedFirstImproperNumerator = adjustImproperNumerator(operator, firstImproperNumerator, secondDenominator);
        int adjustedSecondImproperNumerator = adjustImproperNumerator(operator, secondImproperNumerator, firstDenominator);
        int finalImproperNumerator = mathNumerators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        int finalDenominator = mathDenominators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
=======
        /*System.out.println(firstWhole);
        System.out.println(firstNumerator);
        System.out.println(firstDenominator);
        System.out.println(secondWhole);
        System.out.println(secondNumerator);
        System.out.println(secondDenominator);*/
        int firstImproperNumerator = convertToImproperFractionNumerator(firstWhole, firstNumerator, firstDenominator);
        int secondImproperNumerator = convertToImproperFractionNumerator(secondWhole, secondNumerator, secondDenominator);
        //int commonDenominator = getCommonDenominator(firstDenominator, secondDenominator);
        int adjustedFirstImproperNumerator = adjustImproperNumerator(operator, firstImproperNumerator, secondDenominator);
        //System.out.println(adjustedFirstImproperNumerator);
        int adjustedSecondImproperNumerator = adjustImproperNumerator(operator, secondImproperNumerator, firstDenominator);
        //System.out.println(adjustedSecondImproperNumerator);
        int finalImproperNumerator = mathNumerators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        //System.out.println(finalImproperNumerator);
        int finalDenominator = mathDenominators(adjustedFirstImproperNumerator, adjustedSecondImproperNumerator,
            firstDenominator, secondDenominator, operator);
        //System.out.println(finalDenominator);
>>>>>>> origin/master
        int finalWhole;
        if (finalImproperNumerator != 0) { //&& finalImproperNumerator > finalDenominator) {
            finalWhole = getWholeFromImproper(finalImproperNumerator, finalDenominator);
        } else {
            finalWhole = 0;
<<<<<<< HEAD
        }
        //System.out.println(finalWhole);
        String stringNumerator = String.valueOf(getFinalNumerator(finalImproperNumerator, finalDenominator));
        int finalNumerator = Integer.parseInt(stringNumerator);
        //System.out.println(finalNumerator);
        int numerator = reduceNumerator(finalNumerator, finalDenominator);
        //System.out.println(numerator);
        int denominator = reduceDenominator(finalNumerator, finalDenominator);
        //System.out.println(denominator);
        if (numerator < 0 && denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        if (numerator != 0) {
            if (finalWhole != 0) {
                return String.valueOf(finalWhole) + "_" + numerator + "/" + denominator;
            } else {
                return numerator + "/" + denominator;
=======
        }
        String finalNumerator = String.valueOf(getFinalNumerator(finalImproperNumerator, finalDenominator));
        if (Integer.parseInt(finalNumerator) != 0) {
            if (finalWhole != 0) {
                return String.valueOf(finalWhole) + "_" + finalNumerator + "/" + finalDenominator;
            } else {
                return finalNumerator + "/" + finalDenominator;
>>>>>>> origin/master
            }
        } else {
            return String.valueOf(finalWhole);
        }
    }
   
<<<<<<< HEAD
    public static int reduceNumerator(int numerator, int denominator) {
        int start;
        int greatestCommonFactor = 1;
        if (numerator > denominator) {
            start = numerator;
        } else {
            start = denominator;
        }
        while (greatestCommonFactor != start) {
            if (numerator % start == 0 && denominator % start == 0) {
                greatestCommonFactor = start;
                start++;
            }
            start--;
        }
        return numerator / greatestCommonFactor;
    }
    
    public static int reduceDenominator(int numerator, int denominator) {
        int start;
        int greatestCommonFactor = 1;
        if (numerator > denominator) {
            start = numerator;
        } else {
            start = denominator;
        }
        while (greatestCommonFactor != start) {
            if (numerator % start == 0 && denominator % start == 0) {
                greatestCommonFactor = start;
                start++;
            }
            start--;
        }
        return denominator / greatestCommonFactor;
    }    
=======
    public static int getCommonDenominator(int firstDenominator, int secondDenominator) {
        return firstDenominator * secondDenominator;
    }
    
    public static int adjustImproperNumerator(String operator, int numerator, int otherDenominator) {
        if (operator.equals("+") || operator.equals("-")) {
            return numerator * otherDenominator;
        } else {
            return numerator;
        }
    }
    
    public static int getFinalNumerator(int improperNumerator, int denominator) {
        return improperNumerator % denominator;
    }
>>>>>>> origin/master
    
    public static int convertToImproperFractionNumerator(int whole, int numerator, int denominator) {
        numerator = (denominator * whole) + numerator;
        return numerator;
    }
<<<<<<< HEAD
    
    public static int adjustImproperNumerator(String operator, int numerator, int otherDenominator) {
        if (operator.equals("+") || operator.equals("-")) {
            return numerator * otherDenominator;
        } else {
            return numerator;
        }
    }    
=======

    public static int getWholeFromImproper(int improperNumerator, int denominator) {
        return improperNumerator/denominator;
    }
>>>>>>> origin/master

    public static int mathNumerators(int firstNumerator, int secondNumerator, int firstDenominator,
        int secondDenominator, String operator) {
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
        //firstNumerator *= secondDenominator;
        //secondNumerator *= firstDenominator;
        if (operator.equals("+") || operator.equals("-") || operator.equals("*")) {
            denominator = firstDenominator * secondDenominator;
        } else {
            denominator = firstDenominator * secondNumerator;
        }
        return denominator;
    }
    
    public static int getWholeFromImproper(int improperNumerator, int denominator) {
        return improperNumerator/denominator;
    }    
    
    public static int getFinalNumerator(int improperNumerator, int denominator) {
        if (denominator < 0 && improperNumerator > 0 || denominator > 0 && improperNumerator < 0) {
            improperNumerator = -improperNumerator;
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