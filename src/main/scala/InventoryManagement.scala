def searchProduct(input: String): Option[Product] =
    val products: List[Product] = readInventory
    var resultProduct: Product = null

    var matchFound = false
    products.foreach(product => {
        if product.name.toUpperCase().equals(input.toUpperCase()) then
            matchFound = true
            resultProduct = product
    })

    Option(resultProduct)

def addItem(product: Product): Either[String, Unit] =
    if (product.quantity + 1) <= product.limit then            
        var products: List[Product] = readInventory

        products.foreach(p => {
            if product.name.equals(p.name) then
                p.addItem(new p.Item())
        })

        Right(writeInventory(products))
    else Left("\nYou have already exceeded the max stock for this product !!!")

def removeSpoilages: Either[String, Int] =
    var products: List[Product] = readInventory
    var totalSpoilage: Int = 0

    products.foreach(p => {
        totalSpoilage += p.getSpoilageCount
        p.removeSpoilage
    })

    if totalSpoilage == 0 then
        Left("\nThere are no spoiled items to remove !!!")
    else
        writeInventory(products)
        Right(totalSpoilage)