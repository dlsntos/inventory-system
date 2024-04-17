import scala.io.StdIn.* // Imports primarily for inputs
import scala.util.*

// Prints will be updated later
@main def main(): Unit =
    var choice = 0 
    while
        choice != 3
    do
        // further nice formatting later
        println("\nBakery Inventory Management System\n")

        println("[1] Baked Products Inventory") 
        println("[2] Ingredients Inventory")
        println("[3] Exit\n")

        print("Enter choice: ")

        choice = Try(readInt()) match // enclosed in a try for non-integer input
            case Success(c) => c match
                // once no error found, proceed to evaluate option for next course of action (all return c to return to loop)

                case 1 => { // ingredients processes
                    println("Ingredients Menu")
                    c
                }

                case 2 => { // products processes
                    println("Products Menu")
                    c
                }

                case 3 => { // exit program
                    println("See you again.")
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