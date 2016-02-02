/**
 * Nathan Chau
 * APCS 1st Period
 * 1/29/16
 */

import java.util.*;

public class PlayGolf {
    public static void main(String[] args) {
        Scorecard play = new Scorecard();
        for (int i = 1; i <= 4; i++) {
            play.input();
            System.out.print(i + ". ");
            play.accumulateScores();
            play.nameOfScore();
        }
        System.out.print("5. ");
        play.cumulativeScores();
    }
}

class Scorecard {
    int holeNumber;
    int par;
    int strokes;
    int cumulativePar = 0;
    int cumulativeStrokes = 0;
    void input() {
        Scanner userinput = new Scanner(System.in);
        System.out.print("Enter par, then strokes: ");
        par = userinput.nextInt();
        strokes = userinput.nextInt();
    }
    void nameOfScore() {
        if (par == strokes) {
            System.out.println("par");
        } else if (par - strokes == 1) {
            System.out.println("birdie");
        } else if (par - strokes == 2) {
            System.out.println("eagle");
        } else if (strokes - par == 1) {
            System.out.println("bogey");
        } else if (strokes - par == 2) {
            System.out.println("double bogey");
        } else if (par - strokes > 2) {
            System.out.println((par - strokes) + "below par");
        } else {
            System.out.println((strokes - par) + "above par");
        }
    }
    void accumulateScores() {
        cumulativePar += par;
        cumulativeStrokes += strokes;
    }
    void cumulativeScores() {
        if (cumulativePar > cumulativeStrokes) {
            System.out.println((cumulativePar - cumulativeStrokes) + " below par");
        } else if (cumulativePar < cumulativeStrokes) {
            System.out.println((cumulativeStrokes - cumulativePar) + " over par");
        } else {
            System.out.println("par");
        }
    }
}