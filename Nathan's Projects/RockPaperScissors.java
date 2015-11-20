/*
 * Jacob Ng & Nathan Chau
 * APCS
 * Nov. 13, 2015
 * Chapter 5 Programming Project
 */
import java.util.*;
public class RockPaperScissors {
	public static void main(String[] args) {
    	Scanner userinput = new Scanner(System.in);
    	game(userinput);
	}
	public static void game(Scanner userinput) {
    	Random r = new Random();
    	System.out.println("Let's play a game of Rock, Paper, Scissors.");
    	System.out.print("What is your first move? (\"quit\" to quit) ");
    	String player = userinput.next();
    	while (!(player.equals("quit"))) { // while user input is not "quit"
        	int generator = r.nextInt(3); // computer generates an integer between 0-2
        	String computer;
        	if (generator == 0){ // convert the generated numbers to corresponding moves
            	computer = "Rock";
        	}else if(generator == 1){
            	computer = "Paper";
        	}else{
            	computer = "Scissors";
        	}
        	if(player.equals("Rock")){
            	if(computer.equals("Rock")){
                	System.out.println("The computer chose Rock. You tied.");
            	}else if(computer.equals("Paper")){
                	System.out.println("The computer chose Paper. You lose.");
            	}else {
                	System.out.println("The computer chose Scissors. You win!");
            	}
        	}else if(player.equals("Paper")){
            	if(computer.equals("Rock")){
                	System.out.println("The computer chose Rock. You win!");
            	}else if(computer.equals("Paper")){
                	System.out.println("The computer chose Paper. You tied.");
            	}else {
                	System.out.println("The computer chose Scissors. You lose.");
            	}
        	}else if(player.equals("Scissors")){
            	if(computer.equals("Rock")){
                	System.out.println("The computer chose Rock. You lose.");
            	}else if(computer.equals("Paper")){
                	System.out.println("The computer chose Paper. You win!");
            	}else {
                	System.out.println("The computer chose Scissors. You tied.");
            	}
        	}else {
        	    System.out.println("Please enter \"Rock\", \"Paper\", or \"Scissors\".");
        	}
        	System.out.print("What is your next move? (\"quit\" to quit) ");
        	player = userinput.next();
    	}
    	System.out.println("Game Over.");
	}
}