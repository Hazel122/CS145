/**
 * QuestionTree class represents a binary tree built with QuestionNode.
 * The tree is used to play a game of 20 Questions.
 * The game is about guessing an object based on a series of yes/no questions.
 * The tree structure grows as new questions and objects are added during the game.
 *
 * @author Hazel Zhou
 * @version 1.0 (12/4/23)
 */
import java.util.*;
import java.io.*;

public class QuestionTree {

    /** User interface for input/output */
    UserInterface my;
    
    /** Root node of the binary tree */
    QuestionNode root;
    
    /** Total number of games played */
    int totalGames;
    
    /** Number of games won by computer */
    int gamesWon;

    /**
     * Constructs a QuestionTree given the specified UserInterface.
     *
     * @param ui The UserInterface for input/output.
     */
    public QuestionTree(UserInterface ui) {
        my = ui;
        root = new QuestionNode("Jedi"); // initialize the tree with a single node "Jedi"
        totalGames = 0;
        gamesWon = 0;
    }

    /**
     * Starts the game of 20 Questions.
     * Lets the user to think of an object and guesses it based on the binary tree.
     */
    public void play() {
        my.println("Play Game here");
        play1Round(root);
    }

    /**
     * Plays one round of the game, asking questions and making guesses based on user input.
     * Creates a new branch node if a new object appears.
     *
     * @param current The current node in the binary tree.
     */
    private void play1Round(QuestionNode current) {
        // throw exception when node is null
        if (current == null) throw new IllegalArgumentException();

        // start playing the game when the node is not null
        if (current.left == null) { // if node is a leaf, guess the answer
            my.print("Would your object happen to be " + current.data + "?");
            totalGames++;
        } else { // if node is a branch, then ask the question
            my.print(current.data);
        }

        // get user feed back from the guess
        boolean answer = my.nextBoolean();

        // handle "yes"
        if (answer) {
            if (current.left == null) { // if node is a leaf, computer wins the game
                my.println("I win!");
                gamesWon++;
            } else { // if node is a branch, play another round with left child node
                play1Round(current.left);
            }
        } else { // handle "no"
            // unknown leaf case
            if (current.right == null) {
                my.println("I lose! What object are you thinking of? ");
                String object = my.nextLine();
                String oldObject = current.data;

                // add new question as a node to the tree
                my.println("Type a yes/no question to distinguish " + object + " from " + oldObject);
                String question = my.nextLine();
                add(current, question);

                // add new object and old object to the new question node
                my.println("And what is the answer for your object? (Yes/No) ");
                boolean answ = my.nextBoolean();
                add(current, object, answ);
                add(current, oldObject, !answ);
            } else { // if node is a branch, play another round with right child node
                play1Round(current.right);
            }
        }
    }

    /**
     * Adds a new node with the specified data to the binary tree.
     *
     * @param root The root node of the subtree where the new node will be added.
     * @param data The data for the new node.
     */
    private void add(QuestionNode root, String data) {
        root.data = data;
    }

    /**
     * Adds a new node with the specified data and position to the binary tree.
     *
     * @param root    The root node of the subtree where the new node will be added.
     * @param data    The data for the new node.
     * @param isLeft  A boolean indicating whether the new node should be the left child.
     */
    private void add(QuestionNode root, String data, boolean isLeft) {
        if (isLeft) root.left = new QuestionNode(data); // case to add it to left child node
        else root.right = new QuestionNode(data); // case to add it to right child node
    }

    /**
     * Saves the current tree as a txt file.
     *
     * @param output The PrintStream representing the output stream.
     */
    public void save(PrintStream output) {
        // throw exception when the output stream is null
        if (output == null) throw new IllegalArgumentException();
        my.println("Save the current tree here");
        saveTree(root, output);
    }

    /**
     * Recursive helper method to save the tree to the output stream.
     *
     * @param root   The root node of the subtree to be saved.
     * @param output The PrintStream representing the output stream.
     */
    private void saveTree(QuestionNode root, PrintStream output) {
        // throw exception when the node is null
        if (root == null) throw new IllegalArgumentException();
        if (root != null) {
            if (root.left != null) output.println("Q: " + root.data); // if a branch, save it as a question
            else output.println("A: " + root.data); // if a leaf, save it as an answer
            saveTree(root.left, output); // recursive to the left
            saveTree(root.right, output); // recursive to the right
        }
    }

    /**
     * Loads a new tree from the specified input scanner.
     *
     * @param input The Scanner representing the input stream.
     */
    public void load(Scanner input) {
        // throw exception when the input scanner is null
        if (input == null) throw new IllegalArgumentException();
        my.println("Save the current file here");
        root = loadTree(input);
    }

    /**
     * Recursive helper method to load the tree from the input scanner.
     *
     * @param input The Scanner representing the input stream.
     * @return The root node of the loaded tree.
     */
    private QuestionNode loadTree(Scanner input) {
        // throw exception when the input scanner is null
        if (input == null) throw new IllegalArgumentException();

        if (!input.hasNextLine()) return null; // if at the end of file
        String line = input.nextLine().trim(); // if not the end, take the next line

        if (line.startsWith("Q:")) { // if the line is a question
            QuestionNode current = new QuestionNode(line.substring(2));
            current.left = loadTree(input); // recursively process the next line to left child node
            current.right = loadTree(input); // then process right child node
            return current;
        } else if (line.startsWith("A:")) { // if the line is an answer
            return new QuestionNode(line.substring(2)); // return as leaf node
        }

        return null;
    }

    /**
     * Returns the total number of games played.
     *
     * @return The total number of games played.
     */
    public int totalGames() {
        return totalGames;
    }

    /**
     * Returns the number of games won.
     *
     * @return The number of games won.
     */
    public int gamesWon() {
        return gamesWon;
    }
}
