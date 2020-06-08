import java.util.*

class Employee(
        id: Int,
        name: String,
        last_name: String,
        phone_number: String,
        email: String,
        val hireDate : Date,
        val securityNumber : String,
        var salary : Double) :
        Patron(
                id,
                name,
                last_name,
                phone_number,
                email){

        fun clockIn(){

        }

        fun clockOut(){

        }

        override fun generateReceipt() {
                val receipt = Receipt(check, id)
                receipt.calculateTotal()
                if(!receipt.adoptCat)
                        receipt.total *= 0.85
                receipts.add(receipt)
        }
}