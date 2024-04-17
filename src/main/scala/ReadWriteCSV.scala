
import com.github.tototoshi.csv._
import scala.collection.mutable.ArrayBuffer
import java.io.File

def readIngredients: ArrayBuffer[Ingredient] =
    /* Import contents of ingredients.csv to an ArrayBuffer of Ingredient objects */
    var ingredients = new ArrayBuffer[Ingredient]

    val reader = CSVReader.open(new File("src/main/data/ingredients.csv"))
    
    // iterating through each line of the csv file
    reader.foreach(line => {
        // creating a new instance
        var newIngredient = new Ingredient

        // setting values through each element of line
        newIngredient.name_=(line(0))
        newIngredient.cost_=(line(1).toDouble)
        newIngredient.unit_=(line(2))
        newIngredient.quantity_=(line(3).toInt)
        newIngredient.limit_=(line(4).toInt)
        newIngredient.category_=(line(5))

        // adding the object to the array
        ingredients += newIngredient
    })

    reader.close()

    ingredients

def readProducts: ArrayBuffer[Product] =
    /* Import contents of products.csv to an ArrayBuffer of Product Objects */
    var products = new ArrayBuffer[Product]

    val reader = CSVReader.open(new File("src/main/data/products.csv"))

    // iterating through each line of the csv file
    reader.foreach(line => {
        // creating a new instance
        var newProduct = new Product

        // setting values through each element of line
        newProduct.name_=(line(0))
        newProduct.cost_=(line(1).toDouble)
        newProduct.unit_=(line(2))
        newProduct.quantity_=(line(3).toInt)
        newProduct.limit_=(line(4).toInt)
        newProduct.ingredients_=(line(5).split('_').to(ArrayBuffer)) // converting ingredient elements to array buffer
        newProduct.price_=(line(6).toDouble)


        // adding the object to the array
        products += newProduct
    })

    reader.close()

    products

def writeIngredients(ingredients: ArrayBuffer[Ingredient]): Unit =
    /* Write contents of an ArrayBuffer of Ingredients to ingredients.csv for updating current list of ingredients */
    val writer = CSVWriter.open(new File("src/main/data/ingredients.csv"))

    // create an Array line of Strings to insert each attribute to each index, then convert to List to write 
    ingredients.foreach(ingredient => {
        var line: Array[String] = new Array[String](6)
        
        line(0) = ingredient.name
        line(1) = ingredient.cost.toString()
        line(2) = ingredient.unit
        line(3) = ingredient.quantity.toString()
        line(4) = ingredient.limit.toString()
        line(5) = ingredient.category

        writer.writeRow(line.toList)
    })

    writer.close()

def writeProducts(products: ArrayBuffer[Product]): Unit =
    /* Write contents of an ArrayBuffer of Products to products.csv for updating current list of products */
    val writer = CSVWriter.open(new File("src/main/data/products.csv"))

    // create an Array line of Strings to insert each attribute to each index, then convert to List to write 
    products.foreach(product => {
        var line: Array[String] = new Array[String](7)

        line(0) = product.name
        line(1) = product.cost.toString()
        line(2) = product.unit
        line(3) = product.quantity.toString()
        line(4) = product.limit.toString()
        line(5) = product.ingredients.mkString("_") // convert ArrayBuffer to a single String separated by '_'
        line(6) = product.price.toString()

        writer.writeRow(line.toList)
    })

    writer.close()