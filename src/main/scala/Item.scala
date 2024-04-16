class Item:
    /* This is a parent class carrying attributes for a generic item, including name (name of item),
    cost (cost to get item), unit (how each quantity is measured), quantity (current in stock),
    and limit (max quantity). */

    protected var _name: String = ""
    protected var _cost: Double = 0.0
    protected var _unit: String = ""
    protected var _quantity: Int = 0
    protected var _limit: Int = 0

    def unit: String = _unit
    def unit_=(newUnit: String): Unit =
        _unit = newUnit

    // setters and getters
    def name: String = _name
    def name_=(newName: String): Unit =
        _name = newName

    def cost: Double = _cost
    def cost_=(newCost: Double): Unit =
        _cost = newCost
    
    def quantity: Int = _quantity
    def quantity_=(newQuantity: Int): Unit =
        _quantity = newQuantity

    def limit: Int = _limit
    def limit_=(newLimit: Int): Unit =
        _limit = newLimit
end Item