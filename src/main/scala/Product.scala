import java.util.Date
class Product:
    /** A product existing in the inventory.
     * 
     * @param name of the product
     * @param price unit price of the product
     * @param unit unit measurement (eg. pieace, kg)
     * @param limit maximum quantity in the inventory
     * @param quantity current amount of item
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

    def addItem(item: Item): Unit =
        _items = item :: _items

    class Item():
        /** Instance of a particular product.
         * 
         * @param id randomly generated unique identifier
         * @param expirationDate specific date of expiration, calculated with product expiration
         */

        private var _id: String = ""
        private var _expirationDate: Date = ""