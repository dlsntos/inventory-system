import com.github.nscala_time.time.Imports._
import scala.util.Random

class Product:
    /** A product existing in the inventory.
     * 
     * @param name of the product
     * @param price unit price of the product
     * @param unit unit measurement (eg. pieace, kg)
     * @param limit maximum quantity in the inventory
     * @param quantity current amount of items, always dependent on items
     * @param expiration number of days before spoilage
     * @param items list of items (instances) of this product
     */

    private var _name: String = ""
    private var _price: Double = 0.0
    private var _unit: String = ""
    private var _limit: Int = 0
    private var _quantity: Int = 0
    private var _expiration: Int = 0
    private var _items: List[Item] = Nil

    // getters and setters
    def name: String = _name
    def name_=(newVal: String) = _name = newVal

    def price: Double = _price
    def price_=(newVal: Double) = _price = newVal

    def unit: String = _unit
    def unit_=(newVal: String) = _unit = newVal

    def limit: Int = _limit
    def limit_=(newVal: Int) = _limit = newVal

    def expiration: Int = _expiration
    def expiration_=(newVal: Int) = _expiration = newVal

    def items: List[Item] = _items
    def items_=(newVal: List[Item]) = _items = newVal

    /** Calculates quantity based on current number of items. */
    def quantity: Int = _items.size

    /** Adds a new Item affiliated with the Product. */
    def addItem(item: Item): Unit =
        _items = item :: _items

    /** Calculates the total price of the product */
    def getTotalPrice: Double = _price * quantity

    /** Finds all spoiled Items of a Product */
    def getSpoiledItems: List[Item] =
        var spoiled: List[Item] = Nil

        _items.foreach(item => {
            if item.expirationDate <= LocalDate.now()
            then spoiled = item :: spoiled
        })

        spoiled
    
    /** Calculates number of spoiled items */
    def getSpoilageCount: Int = getSpoiledItems.size

    /** Removes all spoiled items */
    def removeSpoilage =_items = _items.filter(!getSpoiledItems.contains(_))

    class Item():
        /** Instance of a particular product.
         * 
         * @param id randomly generated unique identifier
         * @param expirationDate specific date of expiration, calculated with product expiration
         */

        private var _id: String = generateId()
        private var _expirationDate: LocalDate = generateExpirationDate()

        // auxiliary constructor for if id and expirationdate had been instantiated before
        def this(id: String, expirationDate: LocalDate) =
            this()
            _id = id
            _expirationDate = expirationDate

        // getters and setters
        def id: String = _id
        def id_=(newVal: String) = _id = newVal

        def expirationDate: LocalDate = _expirationDate
        def expirationDate_=(newVal: LocalDate) = _expirationDate = newVal
        def expirationDate_=(newLength: Int) = _expirationDate = (_expirationDate - (Product.this.expiration).days) + newLength.days

        /** Generates a random ID ranging from 11111 to 99999 */
        private def generateId() =
            val rnd = new Random
            rnd.between(11111, 99999).toString
        
        private def generateExpirationDate() =
            (LocalDate.now() + (Product.this.expiration).days)