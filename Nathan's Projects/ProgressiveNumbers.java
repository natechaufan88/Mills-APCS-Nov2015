/* Nathan Chau
 * November 4, 2015
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
        evenSum(userinput, first, count);
    }
    
    public static void minAndMax(Scanner userinput, int first, int count) {
        int max = first; // max number is by default the first number
        int min = first; // min number is by default the first number
        int i = 1;
        while (i <= count) {
            System.out.print("What's your next number? ");
            int next = userinput.nextInt();
            if (next > max) {
                max = next;
            } else if (next < min) {
                min = next;
            }
            i++;
        }
        System.out.println("Largest number: " + max);
        System.out.println("Smallest number: " + min);
    }
    
    public static void evenSum(Scanner userinput, int first, int count) {
        int sum = 0;
        int i = 1;
        while (i <= count) {
            if (next % 2 == 0) {
                sum += next;
                if (next > max) {
                    max = next;
                }
            }
            i++;
        }
        if (first % 2 == 0) {
            sum += first;
        }
    }
}