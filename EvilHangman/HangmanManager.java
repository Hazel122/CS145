/**  HangmanManager Class represents a manager for the Evil Hangman Game. It keeps tracks of number of guesses, 
 * the length of the target word, the remaining possible words, and the guessed letters list.
 * @author Hazel Zhou
 * @version 1.0 (11/03/2023)
 */

import java.util.*;

public class HangmanManager{
   /**
    * the length of the word
    */
   private int length = -1;
   /**
    * number of guesses left
    */
   private int guess = 0;
   /**
    * the entire word list
    */
   private List <String> dictionary;
   /**
    * the remaining possible words list
    */
   private Set <String> words;
   /**
    * the set of letters that is already guessed
    */
   private Set <Character> guessedSet;

   /**
    * Constructs a HangmanManager with a word list, the length of the target word, and the number of guesses allowed.
    * @param dictionary A list of words for the game.
    * @param length The length of the target word.
    * @param guess The number of guesses allowed.
    * @throws IllegalArgumentException if length is less than 1 or guess is less than 0.
    */
   public HangmanManager(List <String> dictionary, int length, int guess){
      if (length < 1 || guess < 0) throw new IllegalArgumentException();
      this.length = length;
      this.guess = guess;
      this.dictionary = new ArrayList <String> (dictionary);
      this.words = new TreeSet <String> ();
      for (String word : dictionary){
         if (word.length() == length) this.words.add(word);
      }
      this.guessedSet = new TreeSet <Character> ();
   }
   
   /**
    * Retrieves the set of remaining possible words
    * @return A set containing remaining possible words
    */
   public Set <String> words(){
      return words;
   }
   
   /**
    * Retrieves the number of guesses left
    * @return The number of remaining guesses
    */
   public int guessesLeft(){
      return guess;
   }
   
   /**
    * Retrieves the alphabetical set of guessed letters.
    * @return the set of guessed letters
    */
   public SortedSet <Character> guesses(){
      return (SortedSet<Character>) guessedSet;
   }
   
   /**
    * Retrieves the current pattern of a target word based on the guessed letter(s)
    * @return the String pattern of the current pattern of possible words
    * @throws IllegalStateException if no words is remaining, happens when no word has the desired length
    */
   public String pattern(){
      if (words.isEmpty()) throw new IllegalStateException();
      String targetWord = "";
      Iterator<String> itr = words.iterator();
      targetWord = itr.next();
      return pattern(targetWord);
   }

   /**
    * Retrieves the pattern of a specific word based on the guessed letter
    * @param targetWord The target word to generate pattern for
    * @return the String pattern of the specific word based on the guessed letter(s)
    */
   public String pattern(String targetWord){
      String result = "";
      for (int i = 0; i < length; i++){
         if (guessedSet.contains(targetWord.charAt(i))) result += targetWord.charAt(i);
         else result += '-';
         result += ' ';
      }
      return result;
   }

   /**
    * Records the guessed letter, update the new remaining word list,
    * returns the number of occurrences of the new guessed letter in the target word.
    * @param guess the new guess letter
    * @return the number of occurences of the new guessed letter in te target word
    * @throws IllegalArgumentException if remaining possible word list is not empty and this letter is already guessed
    * @throws IllegalStateException if remaining possible word list is empty or no guess left
    */
   public int record(char guess){
      if (!words.isEmpty() && guessedSet.contains(guess)) throw new IllegalArgumentException();
      if (words.isEmpty() || this.guess < 1) throw new IllegalStateException();
      this.guess --;
      guessedSet.add(guess);
      Map <String, Set <String>> map = new TreeMap <String, Set <String>>((patternWordSetMap()));
      // find the key of the largest set
      String maxSetKey = largestKey(map);
      // update remaining word list
      words = map.get(maxSetKey);
      // return the number of occurances of the letter in the word
      return countOccurances();
   }

   /**
    * Build a map with pattern as key and corresponding words set as value
    * @return a map with pattern as key and word set with this pattern as value
    */
   public Map <String, Set <String>> patternWordSetMap(){
      Map <String, Set <String>> map = new TreeMap <String, Set <String>>(); // create auxiliary map
      for (String word : words){
         String key = pattern(word);
         if (!map.containsKey(key)){
            map.put(key, new TreeSet<String>());
         }
         map.get(key).add(word);
      }
      return map;
   }

   /**
    * Find the key associated with the largest value set in the provided map
    * @param map A map with pattern strings as key and word list with this pattern as value
    * @return the string key with the largest value set
    */
   public String largestKey(Map <String, Set <String>> map){
      String maxSetKey = "";
      int maxSetSize = 0;
      for (String key : map.keySet()){
         if (map.get(key).size() > maxSetSize) {
            maxSetSize = map.get(key).size();
            maxSetKey = key;
         }
      }
      return maxSetKey;
   }

   /**
    * Counts the number of occurances of the guessed letter in target word's pattern
    * @return the integer of occurancess of the guessed letter in target word's pattern
    */
   public int countOccurances(){
      int count = 0;
      for (int i = 0; i < length; i++){
         if (pattern().charAt(i) == guess) count++;
      }
      return count;
   }
}