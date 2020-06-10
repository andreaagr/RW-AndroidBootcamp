import java.time.Instant
import java.util.*

fun main(){
    Cafe.menu[0] = MenuItem(10.75, 0, "Adopting Cat")
    Cafe.menu[1] = MenuItem(2.50, 1, "Sponsoring Cat")
    Cafe.menu[2] = MenuItem(1.75, 2, "Cappuccino")
    Cafe.menu[3] = MenuItem(1.4, 3, "Soda")
    Cafe.people[0] = Patron(0, "Name1", "LastName1", "5516116616", "someone@gmail.com")
    Cafe.people[1] = Employee(1, "Employee1", "LastName1", "01011",
        "employee@gmail.com", Date.from(Instant.now()), "abjsbjab", 30.00)
    Cafe.people[2] = Employee(1, "Employee2", "LastName2", "01014",
            "employee2@gmail.com", Date.from(Instant.now()), "abjsbddjab", 25.00)
    Cafe.availableShelters[0] = Shelter(0, "dhsbjhdbsjd", "46646364")
    Cafe.availableShelters[1] = Shelter(1, "ajhbahah", "56566564")
    val cat1 = Cat("Cat1", "Breed1", "M", 0, 0)
    val cat2 = Cat("Cat2", "Breed2", "F", 1, 1)
    val cat3 = Cat("Cat3", "Breed3", "M", 1, 2)
    val cat4 = Cat("Cat4" , "Breed4", "F", 0, 3)
    val cat5 = Cat("Cat5" , "Breed4", "F", 0, 4)
    Cafe.availableShelters[0]?.cats?.add(cat1)
    Cafe.availableShelters[1]?.cats?.add(cat2)
    Cafe.availableShelters[1]?.cats?.add(cat3)
    Cafe.availableShelters[0]?.cats?.add(cat4)
    Cafe.availableShelters[0]?.cats?.add(cat5)
    //-------------------------------------------------------------------------------------
    val patron1 = Cafe.people[0] as Patron
    val employee1 = Cafe.people[1] as Employee
    val employee2 = Cafe.people[2] as Employee
    patron1.buy(2,10)
    cat2.sponsor(0)
    cat3.sponsor(0)
    cat1.adopt(0)
    patron1.generateReceipt()
    //--------------------------------------------------------------------------------------
    employee1.buy(3, 20)
    employee1.generateReceipt()
    cat2.sponsor(1)
    //--------------------------------------------------------------------------------------
    cat5.sponsor(2)
    Cafe.printStatistics()
}