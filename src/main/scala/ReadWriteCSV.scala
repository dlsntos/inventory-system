
import com.github.tototoshi.csv._
import java.io.File
import com.github.nscala_time.time.Imports.LocalDate

def readInventory: List[Product] =
    /** Generate an inventory containing individual Items and their related Product.
     * 
     * @return A List of Products with their associated items.
     */

    // retrieve all products in raw List[List[String]]
    val pReader = CSVReader.open(new File("src/main/data/products.csv"))
    var rawProducts = pReader.all()
    var products: List[Product] = Nil
    pReader.close()

    val iReader = CSVReader.open(new File("src/main/data/items.csv"))

    // outer loop to iterate over each product
    for i <- 0 to (rawProducts.size - 1) do
        // create a new instance of Product and add it to products List
        val product = new Product
        product.name_=(rawProducts(i)(0))
        product.price_=(rawProducts(i)(1).toDouble)
        product.unit_=(rawProducts(i)(2))
        product.limit_=(rawProducts(i)(3).toInt)
        product.expiration_=(rawProducts(i)(5).toInt)

        products = product :: products

        // inner loop to iterate over each item line by line by the quantity of its associated product
        for j <- 0 to (rawProducts(i)(4).toInt - 1) do // 0 to 2
            var line = iReader.readNext().getOrElse(Nil) // line 0
            var item = product.Item(line(0), LocalDate.parse(line(1)))
            product.addItem(item)

    iReader.close()
    
    products.reverse

def writeInventory(inventory: List[Product]): Unit =
    /** Writes back an inventory of products into products.csv and items.csv.
     * 
     * @param inventory a list of products
     */
    
    // prepare writers
    val pWriter = CSVWriter.open(new File("src/main/data/products.csv"))
    val iWriter = CSVWriter.open(new File("src/main/data/items.csv"))

    // iterate over each product, create an array of a rowline and write to products.csv
    inventory.foreach(product => {
        var line: Array[String] = new Array[String](6)

        line(0) = product.name
        line(1) = product.price.toString
        line(2) = product.unit
        line(3) = product.limit.toString
        line(4) = product.quantity.toString
        line(5) = product.expiration.toString

        pWriter.writeRow(line.toList)

        // for each product, write its items to items.csv in a similar fashion
        product.items.reverse.foreach(item => {
            line = new Array[String](2)
            
            line(0) = item.id
            line(1) = item.expirationDate.toString

            iWriter.writeRow(line.toList)
        })
    })

    pWriter.close()
    iWriter.close()