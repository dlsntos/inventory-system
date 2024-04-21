import scala.io.StdIn.*
import scala.util.*

@main def main(): Unit =
    var choice = 0 
    while
        choice != 3
    do
        println("\n[ Inventory Management System ]\n")

        println("[1] Manage Inventory")
        println("[2] Manage Product Information")
        println("[3] Exit\n")

        print("Enter choice > ")

        choice = Try(readInt()) match // enclosed in a try for non-integer input
            case Success(c) => c match
                // once no error found, proceed to evaluate option for next course of action (all return c to return to loop)
                case 1 => { // display inventory 
                    displayInventory
                    c
                }

                case 2 => { // add product
                    displayProducts
                    c
                }

                case 3 => { // exit program
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