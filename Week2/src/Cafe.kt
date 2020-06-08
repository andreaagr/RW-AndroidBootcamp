class Cafe {
        var setOfCostumers = mutableSetOf<Int>()

        fun calculateNumberOfTransactions() : Int{
            return receipts.size
        }

        fun calculateNumberOfCostumers() : Int{
            receipts.forEach(){
                setOfCostumers.add(it.patronId)
            }
            return setOfCostumers.size
        }

        fun calculateNumberOfPatrons(){

        }

        // Adoptions per shelter, return a Pair<Int,Int>
        fun calculateNumberOfAdoptions() : Int{
           return Cat.adoptedCats.size
        }


        fun produceListSponsoredCats(){
            val setOfCats = mutableSetOf<Int>()
            Cat.sponsorships.forEach(){
                setOfCats.add(it.catId)
            }
        }

        // Return a list of the cats unadopted and unsponsored :(
        fun produceListForgottenCats(){

        }



        fun topTenMenuItems(){

        }

        fun produceListPopularCats(){

        }

        fun printStatisticsOfTheDay(){



        }

}

