/** ShoppingList class representing a list of items to buy in cart
* @author Hazel Zhou
* @version 1.0 (10/02/2023)
*/
package ShoppingCart;

public class ShoppingList {
   /**
    * an array with Shopping items as elements
    */
   private ShoppingItem[] items;
   /**
    * the number of items in the current ShoppingList
    */
   private int size;
   
   /**
    * The constructor of the ShoppingList class that 
    * creats a ShoppingList array with initial size 0.
    */
   public ShoppingList(){
      items = new ShoppingItem[10];
      size = 0;
   }
   /**
    * Add an ShoppingItem into the ShoppingList
    * @param item The ShoppingItem that need to be added
    * @return a boolean value that is true when added successful and false when ShoppingList is full
    */
   public boolean add(ShoppingItem item){
      if (size >= 10){
         return false;
      }
      items[size] = item;
      size += 1;
      return true;
   }
   /**
    * Returns the size of the ShoppingList
    * @return the number of items in ShoppingList
    */
   public int getSize() {
      return size;
   }
   /**
    * Returns the total sum cost of all shopping items in this list 
    * @return the total cost of the items in the ShoppingList
    */
   public double getTotalCost(){
      double cost = 0;
      for (int i = 0; i < size; i++){
         cost += items[i].getPrice();
      }
      return cost;
   }
   /**
    * Returns the items in the ShoppingList as a string representation, 
    * if the ShoppingList is empty, return “No Items in your shopping list” 
    * @return the string of ShoppingList
    */
   public String toString() {
      if (size == 0){
         return "No Items in your shopping list";
      }
      String string = "ShoppingList has " + size + " shopping item(s):";
      for (int i = 0; i < size; i++){
         string += items[i];
         string = (i+1 < size) ? string + ", " : string;
      }
      return string;
   }
   /**
    * Search a name of the item in the ShoppingList, returns the ShoppingItem if exists, and null if not found
    * @param itemName the name of the item
    * @return the ShoppingItem if exists, and null if not found
    */
   public ShoppingItem searchByName (String itemName){
      for (int i = 0; i < size; i++){
         if (items[i].getName().equals(itemName)){
            return items[i];
         }
      }
      return null;
   }

}