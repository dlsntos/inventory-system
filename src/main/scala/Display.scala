import scala.io.StdIn.*

/** Display a single row of Product information */
def displayRow(product: Product): Unit =
    printf(" %-10s | %-8s | %-9s | %-8s | %-7s | Php %,6.2f | Php %,6.2f", product.name, product.unit, product.limit, product.quantity, product.getSpoilageCount, product.price, product.getTotalPrice)
    println("")

def displayInventory: Any =
    /** Display pages of products with options to update stock and remove spoilage
     * 
     * @return A message for if the inventory is empty, otherwise prints out the display
     */

    var products: List[Product] = readInventory

    // case for if there are no products to retrieve to skip going through the rest of the printing
    if products.isEmpty then
        return println("There are currently no items in the inventory.")
    
    // set up page numbers
    var page: Int = 0
    var maxPage = (products.size + 10 - 1) / 10
    var keepDisplaying: Boolean = true;

    while keepDisplaying == true do
        // initialise starting and ending index to display at most 10 products at a time
        var startIndex = page * 10
        // end index gets either 10 more than start, or the size of the List to prevent out of bound exceptions
        var endIndex = (startIndex + 10).min(products.length)

        // print page number and column headers
        println("\n[ PAGE " + (page + 1) + " / " + maxPage + " ]\n")
        printf(" %-10s | %-8s | %-9s | %-8s | %-7s | %-10s | %-10s","Name", "Unit", "Max Stock", "In Stock", "Spoiled", "Price", "Total Price")
        println("")
        println("-----------------------------------------------------------------------------------")

        // print rows based on index
        for i <- startIndex to endIndex - 1 do
            displayRow(products(i))

        // next options of user
        println("\n[P]revious, [N]ext, [A]dd Item, [R]emove Spoilage, [E]xit")
        print("Choose an action > ")
        var option = readLine().toUpperCase() match
            // previous and next designed to loop back around if page reaches start/end
            case "P" => {
                if page == 0 then page = maxPage - 1
             
                else page -= 1
            }
            case "N" => {
                if page == maxPage - 1 then page = 0
                else page += 1
            }

            case "A" => {
                print("Input full name of product > ")

                // error handling using Option-Some-None on whether product input exists
                searchProduct(readLine()) match
                    case Some(p) => {
                        // error handling using Either-Left-Right on whether adding will exceed limit
                        addItem(p) match
                            case Right(r) => {
                                println(s"Successfully added a new ${p.unit.toLowerCase()} of ${p.name}!")

                                // refresh displayInventory
                                keepDisplaying = false
                                displayInventory
                            }
                            case Left(l) => println(l)
                    }
                    case None => println("Product not found !!!")
            }

            case "R" => {
                print("Input full name of product > ")

                // error handling using Option-Some-None on whether product input exists
                searchProduct(readLine()) match
                    // error handling using Either-Left-Right on whether there are spoiled items or not
                    case Some(p) => {
                        removeSpoilages(p) match
                            case Right(r) => println(s"Successfully removed $r spoiled items!")
                            case Left(l) => println(l)
                    }
                    case None => println("Product not found !!!")
            }

            // exit inventory display
            case "E" => keepDisplaying = false

            case _ => println("Please choose a valid option !!!")