/**
 * Nathan Chau
 * APCS
 * 2.1.16
 */

import java.util.*;

public class PlayACSLLand {
    public static void main(String[] args) {
        ScoreCard play = new ScoreCard();
        for (int i = 1; i <= 5; i++) {
            play.input();
            System.out.print("Line #" + i + ": ");
        }
    }
}

class ScoreCard {
    int a = 0;
    int b = 0;
    void input() {
        Scanner userinput = new Scanner(System.in);
        String numbers = userinput.nextLine();
        char aNum;
        int aCounter = 0;
        for (int i = 0; i < numbers.length(); i += 2) {
            aNum = numbers.charAt(i);
            int x = Character.getNumericValue(aNum);
            aCounter += x;
        }
        System.out.print(aCounter);
    }
}
