import java.time.Instant
import java.util.*

class Receipt (var menuItems : MutableMap<Int,Int>, val patronId : Int){
    var total = 0.0
    var adoptCat = false
    var numitems = 0

    fun calculateTotal(){
        menuItems.forEach(){
            val item = Cafe.menu[it.key]!!
            total += it.value * item.price
            if(it.key == 0)
                adoptCat = true
            numitems += it.value
        }

    }

    fun printReceipt(){
        println("Meow Cafe at ${Date.from(Instant.now())}")
        println("Item \t Quantity \t Subtotal")
        menuItems.forEach(){
            val item = Cafe.menu[it.key]
            println("${item?.description} \t ${it.value} \t ${item?.price?.times(it.value)} ")
        }
        println("The total was $total realised by patron with id = $patronId for $numitems items\n\n")
    }

}