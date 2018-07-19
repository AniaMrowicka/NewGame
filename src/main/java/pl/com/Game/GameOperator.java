package pl.com.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GameOperator {
    private final Scanner sc = new Scanner(System.in);
    private final List<Playable> gameList = new ArrayList<Playable>();
    private final GuessTheNumber guessTheNumber = new GuessTheNumber();
    private final HangMan hangMan = new HangMan();

    public void start() {
        gameList.add(guessTheNumber);
        gameList.add(hangMan);
        String chooseGame = "";
        while (!chooseGame.matches("[1|2]")) {
            System.out.println("Welcome! Select game:\n1-Guess The Number,\n2-Hang man");
            chooseGame = sc.next();
        }
        for (Playable game : gameList) {
            if (game.getNumber() == Integer.parseInt(chooseGame))game.start();

        }
    }
}