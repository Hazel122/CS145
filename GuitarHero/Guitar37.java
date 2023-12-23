// Guitar37 class models a guitar with 37 strings 

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout that contains 37 keys
      private GuitarString[] strings; // collection of all 37 strings
      private int currentTime; // current number of tics
  
   // constructor to create a collection of 37 guitar strings with from 110 Hz to 880 Hz
   public Guitar37(){
      strings = new GuitarString[37];
      // initialize the number of tics to be 0
      currentTime = 0; 
      // add different Hz into the collection
      for (int i = 0; i < strings.length; i++){ 
         strings [i] = new GuitarString(440 * Math.pow(2, (i-24)/12.));
      }
   }

   // method to play the concert note corresponding to the pitch
   public void playNote(int pitch){
      // if pitch is out of range, throw an exception
      if (pitch < -24 || pitch > 12) throw new IllegalArgumentException("Pitch is not between [-24,12]");
      // since pitch is found, pluck the corresponding string frequency
      strings [pitch + 24].pluck();
   }

   // method to check if the desired string is valid
   public boolean hasString(char key){
      return KEYBOARD.indexOf(key) != -1; // check if no key is found in the string (when returns -1)
   }

   // method to pluck the string corresponding to the given key
   public void pluck(char key){
      // if key is not valid, throw an exception
      if (!hasString(key)) throw new IllegalArgumentException();
      // if found valid, pluck the corresponding string
      else {
         strings[KEYBOARD.indexOf(key)].pluck();
      }
   }

   // method to calculate the sum of samples from all 37 strings
   public double sample() {
      double sum = 0;
      for (int i = 0; i < KEYBOARD.length(); i ++){
         sum += strings[i].sample();
      }
      return sum;
   }
   
   // method to apply the Karplus-Strong update to all strings and increment the current time
   public void tic(){
      for (int i = 0; i < KEYBOARD.length(); i ++){
         strings[i].tic();
      }
      currentTime ++;
   }

   // method to get the current time
   public int time(){
      return currentTime;
   }
}
