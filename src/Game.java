import javax.swing.*;
import java.util.Scanner;

public class Game {
    public static void main (String[] args){
        int points = 0;
        for(int x = 0; x < 1; x++){
            String word = pickWord();
            int tries = 10;
            String currentword = makeDashes(word);
            String visualdashes = makeVisualDashes(currentword);
            char letter;
            String guessed = " ";
            for(int i = 10; i > 0; i--){
                letter = getLetter(visualdashes, tries).charAt(0);
                if(guessedLetter(letter, guessed)){
                    System.out.println("You've already guessed " + letter);
                    i += 1;
                }
                else{
                    guessed = guessed + letter;
                    tries -= 1;
                    if(wordIncludesLetter(word, letter)){
                        currentword = wordUpdate(word, currentword, letter);
                        tries += 1;
                        i += 1;
                    }
                }
                visualdashes = makeVisualDashes(currentword);
                if(gameWon(currentword)){
                    points += tries;
                    i = gameWonActions(visualdashes, tries, points);
                }
                else if(gameover(tries)){
                    x = gameLostActions(visualdashes, word, points);
                }
                x -= 1;
            }
        }
    }

    public static String pickWord(){
        String[] words = {"hades", "zeus", "poseidon", "hera", "hestia", "demeter", "apollo", "artemis", "hermes", "ares", "aphrodite", "hephaestus", "athena", "dionysus", "chaos", "erebus", "gaia", "nyx", "tartarus", "ouranos", "thesis", "phanes", "koios", "krios", "kronos", "hyperion", "iapetos", "mnemosyne", "oceanus", "phoebe", "rhea", "tethys", "theia", "themis", "atlas", "epimetheus", "menoitios", "prometheus", "asteria", "perses", "asterious", "dione", "eos", "helios", "selene", "ariadne", "aeolus", "asclepius", "bia", "cratos", "deimos", "eris", "eros", "psyche", "geras", "ganymede", "harmonia", "hebe", "hecate", "hypnos", "khione", "leto", "metis", "enyo", "eileithyia", "momus", "moros", "nemesis", "nike", "persephone", "phobos", "thanatos", "tyche", "zelus", "klotho", "lachesis", "atropos", "morpheus", "phobetor", "phantasos", "pan", "iris", "triton", "paean", "pallas", "melinoe", "morpheus" };
        double doublerandnr = Math.random() * 87;
        int randnr = (int)doublerandnr;
        return words[randnr].toUpperCase();
    }

    public static String makeDashes(String w){
        String dashes = "";
        for(int i = 0; i < w.length(); i++){
            dashes = dashes + "_";
        }
        return dashes;
    }

    public static String makeVisualDashes(String w){
        String visualdashes = "";
        for(int i = 0; i < w.length(); i++){
            visualdashes = visualdashes + w.charAt(i) + " ";
        }
        return visualdashes;
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

    public static String wordUpdate(String w, String s, char l){
        String word = "";
        for(int i = 0; i < w.length(); i++){
            if(w.charAt(i) == l){
                word = word + l;
            }
            else if(s.charAt(i) != '_'){
                word = word + s.charAt(i);
            }
            else{
                word = word + "_";
            }
        }
        return word;
    }

    public static boolean gameWon(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '_'){
                return false;
            }
        }
        return true;
    }

    public static int gameWonActions(String s, int t, int p){
        System.out.println(wordFound(s, t));
        System.out.println("Current score: " + p);
        System.out.println(" ");
        return 0;
    }

    public static boolean gameover(int a){
        return a == 0;
    }

    public static int gameLostActions(String c, String w, int p){
        System.out.println(gameLost(c, w));
        System.out.println("Final score: " + p);
        System.out.println(" ");
        return 1;
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

