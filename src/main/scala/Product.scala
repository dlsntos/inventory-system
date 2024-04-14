import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readInt
class Product extends Item:
    var baked_goods_inventory = new ArrayBuffer()

    def displayItems(): Unit =
        println("\n[Baked Goods]")// add values and conditions later 
                                //conditions should have types of baked goods kung pwede
    def addItem(): Unit =
        println()// add function later
    def removeItem(): Unit =
        println()// add function later
    def calculateSpoilage(): Unit =
        println()// add function later
    def menu(): Unit=
        println("[1] Display Available Baked Products ")
        println("[2] Add a new Baked product to inventory")
        println("[3] Remove Baked good from inventory")
        println("[4] Exit")
        print("Enter choice: "); val choice = readInt()

        if choice == 1 then
            displayItems()
        if choice == 2 then
            removeItem()
        if choice == 3 then
            println("Exit")