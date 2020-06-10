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
        val patron= Cafe.people[patronId] as Patron
        // The adoption is charged to check
        patron.buy(0,1)
        // The shelter stores the information from the adoption
        createOrUpdateRecord(patron,"adopted")
        // Remove the cat from the list of cats
        Cafe.availableShelters[shelterId]?.cats?.remove(this)
        // Remove the sponsorships of the cat
        sponsorships.removeIf {
            it.catId == catId
        }
    }

    fun sponsor(patronId: Int){
        // Update the status of the cat
        status = Status.Sponsored
        val patron= Cafe.people[patronId] as Patron
        // Add the sponsor to the list
        sponsorships.add(Sponsorship(catId,patronId))
        // The shelter stores the information from the adoption
        createOrUpdateRecord(patron,"sponsored")
        patron.buy(1,1)
    }

    fun createOrUpdateRecord(patron : Patron, action : String){
        // Find a cat
        val shelter = Cafe.availableShelters[shelterId]
        // Check if cat has historial
        val message = "The cat with name $name has been $action for ${patron.name + patron.last_name}"
        if (Cafe.availableShelters[shelterId]!!.records.containsKey(catId))
            shelter?.records?.get(catId)?.add(message)
        else
            shelter?.records?.set(catId, mutableListOf<String>(message))
    }

    companion object{
        val sponsorships = mutableListOf<Sponsorship>()
    }


}