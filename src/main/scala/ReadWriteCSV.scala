
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

    products