import java.time.Instant
import java.util.*

val menu = mutableMapOf<Int,MenuItem>()
val people = mutableMapOf<Int,Person>()
val receipts = mutableListOf<Receipt>()
val shelters = mutableMapOf<Int,Shelter>()

fun main() {
    menu[0] = MenuItem(56.75, 0, "Adopting Cat")
    menu[1] = MenuItem(56.75, 1, "Sponsoring Cat")
    menu[2] = MenuItem(56.75, 2, "Cappuccino")
    people[0] = Patron(0, "Name", "LastName", "5516116616", "someone@gmail.com")
    people[1] = Employee(1, "Employee1", "LastName1", "01011",
            "employee@gmail.com", Date.from(Instant.now()), "abjsbjab", 100.00)
    shelters[0] = Shelter(0, "dhsbjhdbsjd", "46646364")
    shelters[1] = Shelter(1, "ajhbahah", "56566564")
    val cat1 = Cat("Cat1", "Breed1", "M", 0, 0)
    val cat2 = Cat("Cat2", "Breed2", "F", 1, 1)
    shelters[0]?.cats?.add(cat1)
    shelters[1]?.cats?.add(cat2)

    // -----------------------------In case that it just a Patron
    val patron1 = people[0] as Patron
    patron1.buy(2, 5)
    patron1.generateReceipt()
    println(receipts.size)
    receipts.forEach() {
        println(it.printReceipt())
    }
    // -----------------------------In case that is Employee
    val employee1 = people[1] as Employee
    employee1.buy(2, 5)
    //cat2.adopt(1)
    employee1.generateReceipt()
    receipts.forEach() {
        println(it.printReceipt())
    }

}