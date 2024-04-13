import scala.io.StdIn.readLine // Scanner for string values
import scala.io.StdIn.readInt  // Scanner for integer values
// Prints will be updated later
@main def main(): Unit =
    println("Bakery Inventory Management System\n")//SDGY
    // login() system for admin
    println("[1] Login\n")//SDGY
    println("[2] Exit\n")
    print("Enter choice: "); val choice = readInt()
    
    //SDGY
    if choice == 1 then// Update login
      print("Enter Admin name: "); val username = readLine()
      print("Enter Admin Numeric passcode: "); val passcode = readInt()

      //add error handling later
      //after logging in, display menu
      println("Inventory Menu\n\n")// menu()
      println("[1] Show Pastries") //shows 
      println("[2] Add new baked pastry")
      println("[3] remove spoiled pastry")
      //other functions to be added
      println("[4] Exit\n")
      
      print("Enter choice: "); val choice = readInt()
      // functions will be added later
        if choice == 1 then
          println()//function
        if choice == 2 then
          println()//function
        if choice == 3 then
          println()//function
        if choice == 4 then
          println("Exit")
    if choice == 2 then
      print("Exit")//Edit exit message later
    
    
     
    // options for
      // displaying items (already with potential calculation of spoilage/other shit or separate option nlng sya)
        // pwede rin side thing lng un
      // adding/deleting items

    // other key stuff to remember
      // classes for Item, which will be inherited by Product and Ingredient classes?
      // main.scala shall only contain the menu system, separate files for other functions (maybe)