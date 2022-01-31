import javax.swing.*;
import java.util.Scanner;

public class Game {
    public static void main (String[] args) {
        String word = pickWord();
        int tries = 10;
        String dashes = makeDashes(word);
        char letter;
        String guessed = " ";
        for(int i = 10; i > 0; i--){
            letter = getLetter(dashes, tries).charAt(0);
            if(guessedLetter(letter, guessed)){
                System.out.println("You've already guessed " + letter);
                i += 1;
            }
            else{
                guessed = guessed + letter;
                tries -= 1;
                if(wordIncludesLetter(word, letter)){
                    dashes = dashConversion(word, dashes, letter);
                    tries += 1;
                    i += 1;
                }
            }
            if(gameWon(dashes)){
                i = 0;
                System.out.println(wordFound(dashes, tries));
            }
            else if(gameover(tries)){
                System.out.println(gameLost(dashes, word));
            }
        }
    }

    public static String pickWord_two(){
        System.out.print("What's your word? ");
        Scanner tgb = new Scanner(System.in);
        return tgb.nextLine().toUpperCase();
    }

    public static String pickWord(){
        String[] words = {"apple", "eye", "cat", "reign", "platitude", "crash", "yeast", "hale", "pine", "gratitude"};
        double doublerandnr = Math.random() * 10;
        int randnr = (int)doublerandnr;
        return words[randnr].toUpperCase();
    }

    public static String makeDashes(String w){
        String dashes = "_ ";
        for(int i = 0; i < w.length()-1; i++){
            dashes = dashes + "_ ";
        }
        return dashes;
    }

    public static String getLetter(String s, int a){
        while(true){
            System.out.println(s + "(" + a + " tries left)");
            System.out.print("Pick a letter: ");
            Scanner tgb = new Scanner(System.in);
            String letter = tgb.nextLine();
            System.out.println(" ");
            letter = letter.toUpperCase();
            if (letter.length() == 1 && checkIfLetter(letter)) {
                return letter;
            }
            else {
                System.out.println("Not valid letter");
            }
        }
    }

    public static boolean checkIfLetter(String s){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < alphabet.length(); i++){
            if(s.charAt(0) == alphabet.charAt(i)){
                return true;
            }
        }
        return false;
    }

    public static boolean guessedLetter(char l, String s){
        for(int i = 0; i < s.length(); i++){
            if(l == s.charAt(i)){
                return true;
            }
        }
        return false;
    }

    public static boolean wordIncludesLetter(String s, char l){
        for(int i = 0; i < s.length(); i++){
            if(l == s.charAt(i)){
                System.out.println("Well done!");
                return true;
            }
        }
        System.out.println("Oh no!");
        return false;
    }

    public static String dashConversion(String w, String s, char l){
        String newdashes = "";
        for(int i = 0; i < w.length(); i++){
            if(w.charAt(i) == l){
                newdashes = newdashes + l + " ";
            }
            else if(s.charAt(i*2) != ' ' || s.charAt(i*2) != '_'){
                newdashes = newdashes + s.charAt(i*2) + " ";
            }
            else{
                newdashes = newdashes + "_ ";
            }
        }
        return newdashes;
    }

    public static boolean gameWon(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '_'){
                return false;
            }
            i += 1;
        }
        return true;
    }

    public static boolean gameover(int a){
        return a == 0;
    }

    public static String wordFound(String s, int a){
        System.out.println("You won!");
        return s + "(You had " + a + " tries left)";
    }

    public static String gameLost(String s, String w){
        System.out.println(s);
        return "You lost! The word was " + w;
    }

}

