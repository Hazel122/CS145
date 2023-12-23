/** ShoppingItem class representing an item in the ShoppingList cart
* @author Hazel Zhou
* @version 1.0 (10/02/2023)
*/
package ShoppingCart;

public class ShoppingItem {
   /**
    * the name of the item
    */
   private String name;
   /**
    * how many item
    */
   private int quantity;
   /**
    * the price per unit of the item
    */
   private double price;
   /**
    * the constructor of the ShoppingItem class that creates a item
    * @param name name of the item
    * @param quantity how many item
    * @param pricePerUnit price per unit of the item
    */
   public ShoppingItem (String name, int quantity, double pricePerUnit) {
      this.name = name;
      this.quantity = quantity;
      this.price = pricePerUnit;
   }
   /**
    * returns the name of item
    * @return the name of item
    */
   public String getName() {
      return name;
   }
   /**
    * returns the price * quantity of the item
    * @return price * quantity of the item
    */
   public double getPrice() {
      return price * quantity;
   }
   /**
    * returns how many item
    * @return the quantity of item
    */
   public double getQuantity() {
      return quantity;
   }
   /**
    * set the quantity of item
    * @param quantity the new quantity
    */
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   /**
    * returns the item as a string in the form of "quantity name" (e.g. 2 apple)
    */
   public String toString(){
      return quantity + " " + name;
   }
}