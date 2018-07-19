package pl.com.Game;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber implements Playable {
    private final Scanner sc = new Scanner(System.in);
    private final Random random = new Random();
    private int triesLeft;
    private int randomNumber;

    @Override
    public void start() {
        chooseLevel();
        game();
        whatIsNext();

    }

    private void game() {
        boolean gameInProgress = true;
        while (gameInProgress) {
            int number;
            String tempNumber = "";
            while (!tempNumber.matches("\\d+")) {
                System.out.println("Enter the number ");
                tempNumber = sc.next();
            }
            number = Integer.parseInt(tempNumber);
            if (number > randomNumber) {
                triesLeft--;
                System.out.println("my number is less then " + number + " Tries left: " + triesLeft);
                gameInProgress = true;
            } else if (number < randomNumber) {
                triesLeft--;
                System.out.println("my number is greater then " + number + " Tries left: " + triesLeft);
                gameInProgress = true;
            } else if (number == randomNumber) {
                System.out.println("CONGRATULATIONS! YOU GUESS THE NUMBER!");
                gameInProgress = false;
            }

            if (triesLeft == 0) {
                gameInProgress = false;
                System.out.println("Game over, No more tries left. Correct answer is : " + randomNumber);
            }
        }
    }

    private void chooseLevel() {
        String level = "";
        while (!level.matches("[1-3]")) {
            System.out.println("WELCOME TO GUESS THE NUMBER. Choose level:" +
                    "\n1-easy," +
                    "\n2-medium," +
                    "\n3-hard");
            level = sc.next();
        }
        String range = "";
        switch (level) {
            case "1":
                triesLeft = 3;
                range = "0-10";
                randomNumber = random.nextInt(11);
                break;
            case "2":
                triesLeft = 5;
                range = "0-50";
                randomNumber = random.nextInt(51);
                break;
            case "3":
                triesLeft = 10;
                range = "0-100";
                randomNumber = random.nextInt(101);
        }
        System.out.println("The number you looking for is in the range: " + range + ".\nTries left:" + triesLeft);

    }
    @Override
    public void whatIsNext() {
        String yesOrNot = "";
        while (!yesOrNot.matches("[1|2]")) {
            System.out.println("DO YOU WANT PLAY AGAIN?\n1-YES,\n2-NO ");
            yesOrNot = sc.next();
        }
        if (yesOrNot.equals("1")) {
            GameOperator gameOperator = new GameOperator();
            gameOperator.start();
        }
    }
    @Override
    public int getNumber() {
        return 1;
    }
}