package com.paultroger;

import java.util.Random;
import java.util.Scanner;

public class RollTheDice {

    static Scanner scan01 = new Scanner(System.in);
    static boolean diceShowedSixBefore;
    static boolean playerPickedSixBefore;
    static int counter;
    static boolean yellowCard;
    static boolean continueGame = true;
    static String decision = "";

    public static void main(String... args) {
        System.out.println("This is a variant of 'Black Jack'.\nThe Game starts now.");

        while (continueGame) {
            diceShowedSixBefore = false;
            playerPickedSixBefore = false;

            for (counter = 1; counter <= 12; counter++) {

                Random rand = new Random();
                int value = rand.nextInt(6) + 1;
                System.out.print("Round " + counter + ": ");
                System.out.println(value);
                gameLogic();

//              First the cases are caught which handle the six in the game.

                if (value == 6 && yellowCard) {
                    System.out.println("You picked a six earlier in the game. You lost.");
                    break;
                }
                else if (value == 6 && !lastRound()) {
                    pickSix();
                    if (decision.equals("y")) {
                        playerPickedSixBefore = true;
                    }
//                  else if (counter == 12) {
//                        System.out.println("You dummy, you missed it!");
//                  }
                    diceShowedSixBefore = true;
                }
                else if (value == 6) {
                    pickSix();
                    if (decision.equals("y"))
                        System.out.println("Great timing, you did it!");
                    else
                        System.out.println("You dummy, you missed it!");
                }

//              Only afterwards the special cases for any other dice value are caught.

                else if (lastRound() && playerPickedSixBefore)
                    System.out.println("Good job, you won the game!");
                else if (lastRound() && diceShowedSixBefore)
                    System.out.println("You missed a six before. You lost.");
                else if (lastRound())
                    System.out.println("Good job, you won the game!");
            }

//          After the for loop finished executing, the player is asked whether he wants to play again.

            System.out.println("Do you want to play again? (Y/N)");
            if (!resumeGame()) {
                System.out.println("Thanks for playing Roll-The-Dice!");
                break;
            }
        }
    }

//  Here we go with some methods, that we extracted from the main method.

    public static boolean lastRound() {
        return (counter == 12);
    }

    public static void gameLogic() {
        yellowCard = diceShowedSixBefore && playerPickedSixBefore;
    }

    public static void pickSix() {
        String simonSays = "";
        while (!simonSays.equals("y") && !simonSays.equals("n")) {
            System.out.println("Do you want to pick the six?\nPlease enter \"Y\" or \"N\"");
            simonSays = scan01.nextLine().toLowerCase();
        }
        decision = simonSays;
    }

    public static boolean resumeGame() {
        String simonSays = "";
        while (!simonSays.equals("y") && !simonSays.equals("n")) {
            System.out.println("Please enter \"Y\" or \"N\"");
            simonSays = scan01.nextLine().toLowerCase();
        }
        return simonSays.equals("y");
    }

}