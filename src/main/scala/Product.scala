import scala.collection.mutable.ArrayBuffer

class Product extends Item:
    /* Product is a child of Item, specifying ingredients used to make product and a unit price
    it is being sold for */

    private var _ingredients = new ArrayBuffer[String]
    private var _price: Double = 0.0

    // setter and getter
    def ingredients: ArrayBuffer[String] = _ingredients
    def ingredients_=(newIngredients: ArrayBuffer[String]) =
        _ingredients = newIngredients
    
    def price: Double = _price
    def price_=(newPrice: Double) =
        _price = newPrice