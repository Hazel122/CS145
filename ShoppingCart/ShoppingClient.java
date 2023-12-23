/* Hazel Zhou
Oct 2,2023
CS& 145 (20258)
Assignment - ShoppingsCart */

package ShoppingCart;

import java.util.Scanner;

public class ShoppingClient {  
   //MAIN:
   public static void main (String[] args) {
      ShoppingList list1 = new ShoppingList();
      // fence: take menu input
      System.out.println("Welcome to your shopping cart manager! Below is the menu:");
      menuDisplay();
      System.out.print("Enter your the number (1-5) of your desired service: ");
      int input = navigateMenu();
      // post: according to the number entered, go to the service
      while (input != 5) {//Option 5: Exit the program
         if (input == 1){
            menuDisplay();
         }else if(input == 2){
            addItems(list1);
         }else if(input == 3){
            System.out.print("Here's your shopping cart overview: " + changeQuantity(list1));
         }else if(input == 4){
            displayCart(list1);
         }

         // fence: take menu input
         System.out.print("\nEnter you desired service (1-5): ");
         input = navigateMenu(); //update input number
      }

   }

   // METHODS:
   // method to display control menu
   public static void menuDisplay(){
      String menu = """
         \nOption 1: Display the menu for the user.
         \nOption 2: Add a shopping item with the Item name, quantity and the unit price
         \nOption 3: Change the quantity of the item in the shopping list. Search for the item in the list and change its quantity
         \nOption 4: Display the shopping list and the total cost
         \nOption 5: Exit the program \n
         """;  
      System.out.print(menu);
   }  

   // method to navigate menu
   public static int navigateMenu(){
      return inputRangeValidation(1, 5);
   }
   
  // method to validate and return the user input to be a int between range[lowRange,highRange]
   public static int inputRangeValidation(double lowRange,double highRange){
      Scanner console = new Scanner(System.in); 
      int num = 0;
      boolean valid = false;
      // fence: check if it is int
      while (!valid){
         while(!console.hasNextInt()){ 
            console.next();
            System.out.print("Please enter a NUMBER:");
         }
         // post: store int
         num = console.nextInt();
         // fence: check if it is in range
         if (num <= highRange && num >= lowRange){
            valid = true;
         }else{
            System.out.printf("Please enter a number between %.0f and %.0f: ", lowRange, highRange);
         }
      }
      return num;
   }

   // method to validate the user input to not be null
   public static String inputGeneralValidation(){
      Scanner console = new Scanner(System.in);
      // initialize input variable and valid condition
      String input = "";
      boolean valid = false;
      while (!valid){
         if(!console.hasNextLine()){
            console.nextLine();
            System.out.print("No text entered. Please enter again: ");
         }else{// input is valid, store the trimmed line string
            input = console.nextLine().trim(); 
            valid = true;
         }
      }
      return input;
   }

   // method to validate the user input to be a int >= lowRange
   public static int intInputValidation_onlyLowRange(int lowRange){
      Scanner console=new Scanner(System.in);
      // initialize num variable and valid condition
      int num = 0;
      boolean valid = false;
      while (!valid){
         if(!console.hasNextInt()){
            console.next();
            System.out.print("Enter a NUMBER: ");
         }else {// input is an integer, next check if input is in range
            num = console.nextInt();
            if(num < lowRange){
               System.out.print("Enter a VALID number:");
            }else {
               valid = true;
            }
         }
      }
      return num;
   }
   // method to validate the user input to be a double
   public static double inputDoubleValidation(){
      Scanner console = new Scanner(System.in);
      // initialize input variable and valid condition
      double num = 0;
      boolean valid = false;
      while (!valid){
         if(!console.hasNextDouble()){// input is not a double
            console.next();
            System.out.print("Please enter a number:");
         }else{// input is valid, store it to num
            num = console.nextDouble();
            valid = true;
         }
      }
      return num;
   }
   // method to ask for new item then add ShoppingItems to ShoppingLists
   public static void addItems(ShoppingList items){
      // get name
      System.out.print("Enter the item's name: ");
      String name = inputGeneralValidation();
      //check if name already exist in list
      boolean existence = true;
      while (existence){// when the name already exist, execute while loop
         if (items.searchByName(name) == null){// use searchByName to find if item exist
            existence = false;
         }else{
            System.out.print("Item exists already. Enter a new one: ");
            name = inputGeneralValidation();
         }
         
      }
      // get quantity
      System.out.print("Enter the item's quantity: ");
      int quantity = intInputValidation_onlyLowRange(1);
      // get price
      System.out.print("Enter the item's price per unit: ");
      double pricePerUnit = inputDoubleValidation();
      // add item
      ShoppingItem item = new ShoppingItem(name, quantity, pricePerUnit);
      String str = (items.add(item)) ? item + " added successfully." + items : item + " not added. Your shopping list is full.";
      System.out.println(str);
   }

   // method to search a item in the shopping list and change the quantity of it
   public static ShoppingList changeQuantity(ShoppingList items){
      // get name
      System.out.print("Enter the name of the item: ");
      String name = inputGeneralValidation();
      // search name in shopping list
      boolean valid = false;
      ShoppingItem item = new ShoppingItem(name, 0, 0);
      while (!valid){
         if (items.getSize() == 0){// check if the list is empty
            System.out.println("You don't have any items in your shopping cart yet. Please add items first. ");
            return items;
         }else if (items.searchByName(name) == null){// check if the item is found in the list
            System.out.println("Item not found. " + items + ". Enter again: ");
            name = inputGeneralValidation();
         }else{ // when the input is valid
            item = items.searchByName(name);
            valid = true;
         }
      }
      // set new quantity
      System.out.println("The current quantity of " + item.getName() + " in your shopping cart is " + item.getQuantity());
      System.out.print("Enter the new quantity of items: ");
      int quantity = intInputValidation_onlyLowRange(1); // take input that is >= lowrange
      item.setQuantity(quantity);
      return items;
   }

   // method to display shopping cart
   public static void displayCart(ShoppingList items){
      System.out.println(items);
      System.out.println("The total cost of your items is $" + items.getTotalCost());
   }
}