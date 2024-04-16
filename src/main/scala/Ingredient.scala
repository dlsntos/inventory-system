import scala.collection.mutable.ArrayBuffer

class Ingredient extends Item:
    /* Ingredient is a child class of Item that holds items not for sale but are isntead used for Product.
    For further organization, it adds a category attribute */

    private var _category: String = ""

    // setters and getters
    def category: String = _category
    def category_=(newCategory: String): Unit =
        _category = newCategory