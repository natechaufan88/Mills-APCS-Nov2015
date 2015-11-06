/* Nathan Chau
 * November 5, 2015
 * Progressive Numbers
 */

import java.util.*;

public class ProgressiveNumbers {
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);
        System.out.print("How many numbers are there? ");
        int count = userinput.nextInt();
        while (count < 1) {
            System.out.println("Please enter a positive integer greater than zero. ");
            System.out.print("How many numbers are there? ");
            count = userinput.nextInt();
        } 
        System.out.print("What is your first number? ");
        int first = userinput.nextInt();
        count--; // first number is already obtained
        minAndMax(userinput, first, count);
    }
    
    public static void minAndMax(Scanner userinput, int first, int count) {
        int max = first; // max number is by default the first number
        int min = first; // min number is by default the first number
        int evenMax;
        if (first % 2 == 0) {
            evenMax = first;
        } else {
            evenMax = -999999999; // hopefully all numbers are >-999999999
        }
        int sum = 0;
        int i = 1;
        while (i <= count) {
            System.out.print("What's your next number? ");
            int next = userinput.nextInt();
            if (next > max) {
                max = next;
            } else if (next < min) {
                min = next;
            }
            if (next % 2 == 0) { // number entered is even
                sum += next; // add number to the total sum
                if (first % 2 != 0) { // only executes body if no even number has been entered yet
                    if (next > evenMax) {
                        evenMax = next; // even if numbers entered are negative, all long as they
                                        // are >-999999999, then evenMax can still be the largest
                                        // negative number entered
                    }
                }
            }
            i++;
        }
        System.out.println("Largest number: " + max);
        System.out.println("Smallest number: " + min);
        if (evenMax > -999999999) {
            System.out.println("Largest even number: " + evenMax);
        } else {
            System.out.println("No even numbers were entered.");
        }
        System.out.println("Sum of even numbers: " + sum);
    }
}