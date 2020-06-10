class Cafe {
    companion object{
        val people = mutableMapOf<Int,Person>()
        val availableShelters = mutableMapOf<Int,Shelter>()
        val menu = mutableMapOf<Int,MenuItem>()
        val receipts = mutableListOf<Receipt>()


        val setOfCostumers = mutableSetOf<Int>()
        var cats = mutableSetOf<Cat>()


        fun calculateNumberOfTransactions() : Int{
            return receipts.size
        }

        fun calculateNumberOfCostumers() : Int{
            receipts.forEach(){
                setOfCostumers.add(it.patronId)
            }
            return setOfCostumers.size
        }

        fun calculateNumberOfPatrons() : Int{
            var patrons = 0
            setOfCostumers.forEach(){
                if(!(people[it] is Employee)){
                    patrons += 1
                }
            }
            return patrons
        }

        fun calculateNumberOfAdoptions() : Int{
            var adoptions = 0
            receipts.forEach(){receipt ->
                receipt.menuItems.forEach(){
                    if(it.key == 0)
                        adoptions += it.value
                }
            }
            return adoptions
        }

        fun produceListSponsoredCats(){
            val sponsoredCats = mutableSetOf<Cat>()
            // Review in the set what cats are sponsored
            cats.forEach(){
                if(it.status == Status.Sponsored){
                    sponsoredCats.add(it)
                }
            }

            // Print sponsored cats
            println("This list shows the cats that currently are sponsored:")
            sponsoredCats.forEach(){
                println(it.name)
            }
            println("\n")


        }

        fun produceListForgottenCats(){
            val forgottenCats = mutableListOf<Cat>()
            // Review in the set what cats are sponsored
            cats.forEach(){
                if(it.status == Status.Forgotten){
                    forgottenCats.add(it)
                }
            }

            println("This list shows the cats that aren't sponsored and adopted:")
            forgottenCats.forEach(){
                println(it.name)
            }
            println("\n")
        }

        fun topTenMenuItems(){
            val items = countItems()
            val top = items.toList().sortedBy { (_, value) -> value}.reversed().toMap()
            println("Top ten selling menu:")
            println("Description: \t Number of orders \t Gross Sales")
            top.forEach(){
                val menuItem = menu[it.key]
                println("${menuItem!!.description} \t ${it.value} \t ${it.value * menuItem.price}")
            }
            println("\n")
        }

        fun produceListOfPopularCats(){
            val sponsoredCatsCount = countCats()
            val popularCats = sponsoredCatsCount.toList().sortedBy { (_, value) -> value}.reversed().toMap()
            println("This list shows the cats in order of popularity :3")
            println("Cat name \t Number of sponsorships")

            popularCats.forEach(){
                val cat = findCat(it.key)
                println("${cat.name} \t\t ${it.value}")
            }
            println("\n")
        }

        fun findCat(catId : Int) : Cat{
            return cats.filter { cat -> cat.catId == catId  }[0]
        }

        fun countItems(): MutableMap<Int, Int> {
            val items = mutableMapOf<Int,Int>()
            receipts.forEach(){receipt ->
                receipt.menuItems.forEach(){
                    if(items.contains(it.key) && it.key !in 0..1)
                        items[it.key] = it.value + items[it.key]!!
                    // Exclude cats from top ten
                    else if(it.key !in 0..1)
                        items[it.key] = it.value
                }
            }
            return items
        }

        fun countCats() : MutableMap<Int,Int>{
            val cats = mutableMapOf<Int,Int>()
            Cat.sponsorships.forEach(){
                if(cats.containsKey(it.catId))
                    cats[it.catId] = cats[it.catId]!!.plus(1)
                else
                    cats[it.catId] = 1
            }
            return cats
        }

        fun printStatistics(){
            createSetOfCats()
            println("Number of transactions: ${calculateNumberOfTransactions()}")
            println("Number of costumers: ${calculateNumberOfCostumers()}")
            println("Number of patrons: ${calculateNumberOfPatrons()}")
            println("Number of adoptions: ${calculateNumberOfAdoptions()}\n\n")
            produceListSponsoredCats()
            produceListForgottenCats()
            produceListOfPopularCats()
            topTenMenuItems()

        }

        fun createSetOfCats() {
            // Create a set with the mix of cats of all shelters
            availableShelters.forEach(){
                cats.plusAssign(it.value.cats)
            }
        }
    }
}