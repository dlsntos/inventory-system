/** Gets the total number of items in the inventory. */
def totalInventory: Int =
    var total: Int = 0

    readInventory.foreach(product => {
        total += product.quantity
    })

    total

/** Gets the maximum number of the items in the inventory. */
def maxInventory: Int =
    var max: Int = 0

    readInventory.foreach(product => {
        max += product.limit
    })

    max

def searchProduct(input: String): Option[Product] =
    /** Checks the inputted product name in the inventory and returns the respective Product.
     * 
     * @param input inputted name of product to check
     * @return an Option[Product] where it either returns Some(Product) if the item does exist or None if it doesn't
     */

    val products: List[Product] = readInventory
    var resultProduct: Product = null

    // add matched product to resultProduct if found, otherwise remains None
    products.foreach(product => {
        if product.name.toUpperCase().equals(input.toUpperCase()) then
            resultProduct = product
    })

    Option(resultProduct)

def addItem(product: Product): Either[String, Unit] =
    /** Creates a new instance of Item in a Product and adds it to Product's list of items.
     * 
     * @param product a Product object to add the item on
     * @return an Either of Left(String) for passing error message or Right(Unit) for a successful add.
     */

    // checks if product will exceed the max stock using Left/Right
    if (product.quantity + 1) <= product.limit then            
        var products: List[Product] = readInventory

        // finds for the corresponding product and adds the new item to it
        products.foreach(p => {
            if product.name.equals(p.name) then
                p.addItem(new p.Item())
        })

        // update inventory afterwards
        Right(writeInventory(products))
    else Left("\nYou have already exceeded the max stock for this product !!!")

def deleteItem(product: Product): Either[String, Unit] =
    /** Decrease stock of a certain product.
     * 
     * @param product a Product object to update
     * @return an Either[String, Unit] of Left(String) to pass error message and Right(Unit) for a successful delete.
     */

    // checks if product's current stock already reached 0 to prevent negative stock using Left/Right
    if (product.quantity - 1) >= 0 then            
        var products: List[Product] = readInventory

        // removes item from list using slice
        products.foreach(p => {
            if product.name.equals(p.name) then
                p.items_=(p.items.slice(0, p.quantity - 1))
        })

        // update inventory
        Right(writeInventory(products))
    else Left("\nYou have already exceeded the max stock for this product !!!")

def removeSpoilages: Either[String, Int] =
    /** Checks for all spoiled products in the inventory and removes them from the Product's list of items.
     * 
     * @return an Either[String, Int] of Left(String) passing an error message or Right(Int) passing the number of spoiled items removed.
     */

    var products: List[Product] = readInventory
    var totalSpoilage: Int = 0

    // adds each Product's spoilage count to the total spoilage and then removes them
    products.foreach(p => {
        totalSpoilage += p.getSpoilageCount
        p.removeSpoilage
    })

    // pass a message that no spoiled items were removed, otherwise update the inventory and return totalSpoilage count
    if totalSpoilage == 0 then
        Left("\nThere are no spoiled items to remove !!!")
    else
        writeInventory(products)
        Right(totalSpoilage)