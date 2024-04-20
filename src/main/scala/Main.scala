import scala.io.StdIn.* // Imports primarily for inputs
import scala.util.*

// Prints will be updated later
@main def main(): Unit =
    var choice = 0 
    while
        choice != 5
    do
        // further nice formatting later
        println("\nInventory Management System\n")

        println("[1] Manage Inventory")
        println("[2] Add New Product")
        println("[3] Delete Existing Product")
        println("[4] Update Product Information")
        println("[5] Exit\n")

        print("Enter choice: ")

        choice = Try(readInt()) match // enclosed in a try for non-integer input
            case Success(c) => c match
                // once no error found, proceed to evaluate option for next course of action (all return c to return to loop)

                case 1 => { // display inventory 
                    displayInventory
                    c
                }

                case 2 => { // add product
                    c
                }

                case 3 => { // delete product
                    c
                }

                case 4 => { // update product
                    c
                }

                case 5 => { // exit program
                    println("\nSee you again!")
                    c
                }
                case _ => { // invalid integer input
                    println("Please input a valid integer !!!")
                    c
                }

            case Failure(e) => {
                println("Please input a valid integer !!!")
                0 // to return back to loop
            }