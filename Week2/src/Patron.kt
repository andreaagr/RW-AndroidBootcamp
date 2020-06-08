open class Patron(
        id: Int,
        name: String,
        last_name: String,
        phone_number: String,
        email: String
) : Person(id, name, last_name, phone_number, email) {

    open fun generateReceipt(){
        val receipt = Receipt(check, id)
        receipt.calculateTotal()
        receipts.add(receipt)
    }

    fun buy(menuId : Int, quantity : Int){
        createOrReplaceValue(menuId,quantity)
    }

    fun createOrReplaceValue(menuId : Int, quantity: Int){
        if(!check?.isEmpty()!!)
            if(check.contains(menuId)){
                val temp = check.getValue(menuId)
                check[menuId] = temp+1
            }else{
                check[menuId] = quantity
            }
        else
            check.set(menuId,quantity)

    }
}