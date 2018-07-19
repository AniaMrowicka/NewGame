package pl.com.Game;

import entities.Level;
import pojo_entity.HibernateSender;
import pojo_entity.WordsEntityDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class HangMan implements Playable {
    private final Scanner sc = new Scanner(System.in);
    private int triesCount;
    private String choosenLevel;
    private String word="";
    public HibernateSender hibernateSender = new HibernateSender();


    @Override
    public void start() {
        chooseLevel();
        game();
        whatIsNext();
    }

    private String[] wordToArray(String word) {
        return word.split("");
    }

    private String[] getHiddenWord(String word) {
        return word.replaceAll("\\p{L}", "_").split("");
    }

    private void game() {
        String[] wordInTheArray = wordToArray(word);
        String[] hiddenWord = getHiddenWord(word);
        boolean letterFound;
        boolean gameInProgress = true;

        System.out.println(Arrays.toString(hiddenWord));
        System.out.println("Tries left: " + triesCount + ". Let's start");

        while (gameInProgress) {
            letterFound = false;
            String letter = "";

            while (!letter.matches("^\\D$")) {
                System.out.println("Enter letter");
                letter = sc.next();
            }

            for (int i = 0; i < wordInTheArray.length; i++) {
                if (wordInTheArray[i].equalsIgnoreCase(letter)) {
                    hiddenWord[i] = letter;
                    letterFound = true;
                }
            }

            if (!letterFound) {
                triesCount--;
                System.out.println("Letter not found. Tries left: " + triesCount);
                if (triesCount == 0) {
                    System.out.println("Game over, No more tries left");
                    System.out.println(Arrays.toString(wordInTheArray));
                    gameInProgress = false;
                }
            }
            int counter = 0;
            for (int i = 0; i < hiddenWord.length; i++) {
                if (hiddenWord[i].contains("_")) {
                    counter++;
                }
            }
            if (counter == 0) {
                System.out.println("CONGRATULATIONS! YOU WON!");
                System.out.println(Arrays.toString(wordInTheArray));
                gameInProgress = false;
            }
            System.out.println(Arrays.toString(hiddenWord));
        }
    }

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

    private void chooseLevel() {
        String level = "";
        while (!level.matches("[1-3]")) {
            System.out.println("WELCOME TO HANGMAN. Choose level:" +
                    "\n1-easy," +
                    "\n2-medium," +
                    "\n3-hard");
            level = sc.next();
        }
        switch (level) {
            case "1":
                choosenLevel = "easy";
                hibernateSender.readFromHangman(choosenLevel);
                word= hibernateSender.getWord();
                triesCount = 4;
                break;
            case "2":
                choosenLevel = "medium";
                hibernateSender.readFromHangman(choosenLevel);
                word=hibernateSender.getWord();
                triesCount = 6;
                break;
            case "3":
                choosenLevel = "hard";
                hibernateSender.readFromHangman(choosenLevel);
                word=hibernateSender.getWord();
                triesCount = 10;
                break;
            default:
                chooseLevel();
                break;
        }
    }

    private List<String> reader(String cos) {
        List<String> words = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(cos);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    @Override
    public int getNumber() {
        return 2;
    }
}