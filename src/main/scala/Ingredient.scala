import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readInt
class Ingredient extends Item:
    
    def displayItems(): Unit =
        println("[Ingredients Inventory]")//add conditions later base on category of ingredients
                                        //example wet,dry,non-perishable ingredients
    def addItem(): Unit  =
        println("")// add function later
    def removeItem(): Unit =
        println("")// add function later
    def calculateSpoilage(): Unit =
        println("")// add function later
    def menu(): Unit =
        println("[1] Display Available Ingredients ")
        println("[2] Restock ingredients")
        println("[3] Remove Expired/unusable ingredients")
        println("[4] Exit")
        print("Enter choice: "); val choice = readInt()
        
        if choice == 1 then
            displayItems()
        if choice == 2 then
            removeItem()
        if choice == 3 then
            println("Exit")