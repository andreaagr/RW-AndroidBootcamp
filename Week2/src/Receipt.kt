import kotlin.properties.Delegates
// A cat adoption has the key 0
// A cat sponsorship has the key 1
class Receipt (val menuItems : MutableMap<Int,Int>, val patronId : Int){
    var total = 0.0
    var adoptCat = false

    fun calculateTotal(){
        menuItems.forEach(){
            val item = menu[it.key]!!
            total += it.value * item.price
            if(it.key == 0)
                adoptCat = true
        }

    }

    fun printReceipt(){
        var num_items = 0
        menuItems.forEach(){
            num_items += it.value
        }
        println("The total was $total realised by patron with id = $patronId")
    }

}
