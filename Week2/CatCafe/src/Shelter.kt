class Shelter(val shelterId : Int, var shelterAddress: String, var shelterPhone : String) {
    val cats = mutableSetOf<Cat>()
    val records = mutableMapOf<Int,MutableList<String>>()

}