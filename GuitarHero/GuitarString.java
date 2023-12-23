import java.util.*;

//GuitarString class models a single guitar string
public class GuitarString {
   private Queue<Double> ringbuffer; // a queue to represent ringbuffer
   private int size; // size of ringbuffer
   public static double energyDecayFactor = .996; // energy decay factor in Karplus-Strong
   
   // constructor for creating a guitar string with a specified frequency
   public GuitarString(double frequency) {
      ringbuffer = new LinkedList<>();
      size = Math.round ((float)(StdAudio.SAMPLE_RATE/frequency));
      // if frequency is less than 0 or size is less than 2, throw exception
      if (frequency <=  0 || size <= 2) throw new IllegalArgumentException();
      // initialize the ringbuffer with all 0.0
      for (int i=0; i<size; i++) {
         ringbuffer.add(0.0);
      }
   }
   
   // constructor for creating a guitar string with initial values
   public GuitarString (double[] init) {
      ringbuffer = new LinkedList<>();
      size = init.length;
      // if size is invalid, throw exception
      if (size < 2) throw new IllegalArgumentException();
      // copy from init to ringbuffer
      for (int i=0; i<size; i++) {
         ringbuffer.add(init[i]);
      }
   }
   
   // method to pluck the string by replacing all elements in ringbuffer to be random values
   public void pluck(){
      Random rand = new Random();
      int length = ringbuffer.size();
      for (int i = 0; i < length; i++){
         double n = rand.nextDouble() - 0.5; // generate random decimal between [-0.5,0.5)
         ringbuffer.remove();
         ringbuffer.add(n);
      }
   }
   
   // method to apply the Karplus-Strong update once
   public void tic(){
      double firstValue = ringbuffer.remove(); // remove first element
      double newSample = energyDecayFactor * (firstValue + ringbuffer.peek()) / 2.; //apply Karplus-Strong
      ringbuffer.add(newSample); // add new sample
   }
   
   // method to get the current sample without removing it
   public double sample(){
      return ringbuffer.peek();
   }
}