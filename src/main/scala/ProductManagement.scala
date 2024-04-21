import scala.io.StdIn.*
import scala.util.*

def addProduct: Unit =
    /** Takes inputs from user, creates a new Product and adds it to the inventory.
     * User may input 'cancel' at any time they can to end the process and return to display.
     */

    // set up variables
    var input: String = ""
    var name: String = ""
    var unit: String = ""
    var limit: Int = 0
    var price: Double = 0.0
    var expiration: Int = 0
    println("\nFill out the details below. You may type 'cancel' any time to return.\n")

    // name
    print("Enter product name: ")
    input = readLine()
    if input.toLowerCase.equals("cancel") then
        return
    else
        // checking if product already exists with the same name
        searchProduct(input) match
            case Some(o) => return println("Product already exists !!!")
            case None => name = input.toLowerCase.split(' ').map(_.capitalize).mkString(" ")
    
    // unit
    print("Enter its measurement in units > ")
    input = readLine()
    if input.toLowerCase.equals("cancel") then
        return
    else
        unit = input.toLowerCase.split(' ').map(_.capitalize).mkString(" ")
    
    // limit
    print("Enter max quantity in stock > ")
    input = readLine()
    if input.toLowerCase.equals("cancel") then
        return
    else Try(input.toInt) match
        case Success(s) => {
            if s > 0 then limit = s
            else return println("You cannot have max stock 0 or less !!!")
        }
        case Failure(f) => return println("Please enter a number !!!")

    // price
    print("Enter unit price > ")
    input = readLine()
    if input.toLowerCase.equals("cancel") then
        return
    else Try(input.toDouble) match
        case Success(s) => price = s
        case Failure(f) => return println("Please enter a number !!!")

    // expiration
    print("Enter expiration length in days > ")
    input = readLine()
    if input.toLowerCase.equals("cancel") then
        return
    else Try(input.toInt) match
        case Success(s) => {
            if s > 0 then expiration = s
            else return println("You can't enter an expiration length of 0 days !!!")
        }
        case Failure(f) => return println("Please enter a number !!!")

    // print all details again and confirms to user
    println("")
    println(s"Product Name:\t\t$name")
    println(s"Unit Measurement:\t$unit")
    println(s"Max Quantity:\t\t$limit")
    println(f"Unit Price:\t\tPhp $price%,6.2f")
    println(s"Expiration Length:\t$expiration")

    print("\nIs this correct? Type 'Y' to confirm > ")
    readLine().toUpperCase match
        case "Y" => {
            // creating new Product with the values and adding it to inventory list
            var product = new Product
            product.name_=(name)
            product.unit_=(unit)
            product.limit_=(limit)
            product.price_=(price)
            product.expiration_=(expiration)

            var products = readInventory
            products = product :: products
            writeInventory(products)

            return println("Product has been successfully added!")
        }
        case _ => return println("Cancelling add product ...")

/** Takes user inputs on what field to update, asks confirmation, and applies updates accordingly without contradictions */
def updateProduct: Unit =
    print("Enter full name of product to update > ")

    // check if product exists for it to be deleted
    searchProduct(readLine()) match
        case Some(p) => {
            println(" \nWhat would you like to update?")
            print("[N]ame, [U]nit, [L]imit, [P]rice, [E]xpiraton > ")

            readLine().toUpperCase match
                // new name
                case "N" => {
                    print("Type in the new name > ")

                    // ensures that the name will be in capital first letter format                  
                    var newName = readLine().toLowerCase.split(' ').map(_.capitalize).mkString(" ")

                    println(s"Changing ${p.name} >> $newName")
                    print("Type 'Y' to confirm > ")
                    
                    readLine().toUpperCase match
                        case "Y" => {
                            var products = readInventory

                            products.foreach(product => {
                                if product.name.equals(p.name) then product.name_=(newName)
                                writeInventory(products)
                            })

                            return println("\nRename successful!")
                        }
                        case _ => return println("Cancelling rename ...")
                }

                // new unit
                case "U" => {
                    print("Type in the new unit measurement > ")                    
                    var newUnit = readLine().toLowerCase.split(' ').map(_.capitalize).mkString(" ")

                    println(s"Changing ${p.unit} >> $newUnit")
                    print("Type 'Y' to confirm > ")
                    
                    readLine().toUpperCase match
                        case "Y" => {
                            var products = readInventory

                            products.foreach(product => {
                                if product.name.equals(p.name) then product.unit_=(newUnit)
                                writeInventory(products)
                            })

                            return println("\nUpdate successful!")
                        }
                        case _ => return println("Cancelling update ...")
                }

                // new limit
                case "L" => {
                    print("Type in the new limit > ")

                    Try(readLine().toInt) match
                        case Success(s) => {
                            if s < 1 then return println("You cannot have a limit of 0 or less !!!")

                            var newLimit = s

                            println(s"Changing ${p.limit} >> $newLimit")
                            print("Type 'Y' to confirm > ")
                            
                            readLine().toUpperCase match
                                case "Y" => {
                                    var products = readInventory

                                    products.foreach(product => {
                                        if product.name.equals(p.name) then
                                            if product.quantity > newLimit then
                                                return println("Update unsuccessful: current amount of stuck exceeds new desired limit! Please remove some items first before updating.")
                                            else
                                                product.limit_=(newLimit)
                                                writeInventory(products)
                                                return println("\nUpdate successful!")
                                    })
                                }
                                case _ => return println("Cancelling update ...")
                        }
                        case Failure(e) => return println("Please input a number !!!")
                }

                // new price
                case "P" => {
                    print("Type in the new price > ")                    
                    Try(readLine().toDouble) match
                        case Success(s) => {
                            var newPrice = s

                            println(s"Changing ${p.price} >> $newPrice")
                            print("Type 'Y' to confirm > ")
                            
                            readLine().toUpperCase match
                                case "Y" => {
                                    var products = readInventory

                                    products.foreach(product => {
                                        if product.name.equals(p.name) then product.price_=(newPrice)
                                        writeInventory(products)
                                    })

                                    return println("\nUpdate successful!")
                                }
                                case _ => return println("Cancelling update ...")
                        }
                        case Failure(e) => return println("Please input a number !!!")
                }

                // new expiration
                case "E" => {
                    print("Type in the new expiration length > ")

                    Try(readLine().toInt) match
                        case Success(s) => {
                            // checks first if expiration is greater than 0
                            if s < 0 then return println("You cannot have an expiration date of 0 or less !!!")

                            var newExpiration = s

                            println(s"Changing ${p.expiration} >> $newExpiration")
                            print("Type 'Y' to confirm > ")
                            
                            readLine().toUpperCase match
                                case "Y" => {
                                    var products = readInventory

                                    // update expiration dates of existing items
                                    products.foreach(product => {
                                        if product.name.equals(p.name) then
                                            product.items.foreach(item => item.expirationDate_=(newExpiration))
                                            product.expiration_=(newExpiration)
                                            writeInventory(products)
                                    })

                                    return println("\nUpdate successful!")
                                }
                                case _ => return println("Cancelling update ...")
                        }
                        case Failure(e) => return println("Please input a number !!!")
                }
                case _ => return println("That is not a valid option !!!")
        }
        case None => return println("Product does not exist !!!")

/** Takes input from user, verifies if the product exists in the inventory, and deletes it once confirmed. */
def deleteProduct: Unit =
    print("Enter full name of product to delete > ")

    // check if product exists for it to be deleted
    searchProduct(readLine()) match
        case Some(p) => {
            // confirmation
            println(s"\nYou will be deleting ${p.name} with ${p.quantity} item/s.")
            print("Type 'Y' to confirm > ")

            readLine().toUpperCase match
                case "Y" => {
                    // delete product by filtering it out
                    var products = readInventory
                    products = products.filter(_.name != p.name)
                    writeInventory(products)

                    return println(s"${p.name} has been successfully removed!")
                }
                case _ => return println("\nCancelling delete ...")
        }
        case None => return println("Product does not exist !!!")