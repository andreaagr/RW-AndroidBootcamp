import java.net.InetAddress

class Shelter(val shelterId : Int, var shelterAddress: String, var shelterPhone : String) {
    val cats = mutableSetOf<Cat>()
}