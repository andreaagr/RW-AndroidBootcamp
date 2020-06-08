class Cat(
        val name : String,
        val breed : String,
        val sex : String,
        val shelterId : Int,
        val catId : Int
) {
        var status = Status.Forgotten

        fun adopt(patronId : Int){
                status = Status.Adopted
                // Check if patron haven't adopted another cat
                val check = people[patronId]?.check
                if(!check?.isEmpty()!!)
                        if(check.contains(0)){
                                val temp = check.getValue(0)
                                check[0] = temp+1
                        }else{
                                check[0] = 1
                        }

                // Remove cat from the shelter and sponsor
                shelters[shelterId]?.cats?.remove(this)
                // Remove sponsorships
        }

        fun sponsor(patronId: Int){
                status = Status.Sponsored
                sponsorships.add(Sponsorship(catId,patronId))
        }

        companion object{
                val sponsorships = mutableListOf<Sponsorship>()
        }

}