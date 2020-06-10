open class Patron(
    id: Int,
    name: String,
    last_name: String,
    phone_number: String,
    email: String
) : Person(id, name, last_name, phone_number, email){

    val check = mutableMapOf<Int,Int>()

    fun buy(menuItemId : Int, quantity : Int){
        if (check.containsKey(menuItemId))
            check[menuItemId] = quantity + check[menuItemId]!!
        else
            check[menuItemId] = quantity
    }

    open fun generateReceipt(){
        val receipt = Receipt(check, id)
        receipt.calculateTotal()
        Cafe.receipts.add(receipt)
        receipt.printReceipt()
        //check.clear()
    }

}