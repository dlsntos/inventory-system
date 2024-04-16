import scala.io.StdIn.readLine // Scanner for string values
import scala.io.StdIn.readInt  // Scanner for integer values

// Prints will be updated later
@main def main(): Unit =
  println("Bakery Inventory Management System\n")//SDGY
  //login system removed
  println("[1] Baked products inventory") 
  println("[2] Ingredients inventory")
  //println("[3] packaging supplies inventory") should we add this?  
  println("[3] Exit\n")
  print("Enter choice: "); val choice = readInt()
      
  if choice == 1 then
    val baked_products = new Product() //instance of the product class
    // baked_products.menu()

  else if choice == 2 then
    val ingredients = new Ingredient()//function
    // ingredients.menu()
    
  else if choice == 3 then
    println("Exit")//temporary
    
     
    // options for
      // displaying items (already with potential calculation of spoilage/other shit or separate option nlng sya)
        // pwede rin side thing lng un
      // adding/deleting items

    // other key stuff to remember
      // classes for Item, which will be inherited by Product and Ingredient classes?
      // main.scala shall only contain the menu system, separate files for other functions (maybe)