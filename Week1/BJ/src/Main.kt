import java.lang.StringBuilder

/***
 *
 * @author Andrea García
 * Week 1
 *
 * ***/
fun colorMessage(text : String, color : String){
    when(color){
        "purple" -> println(StringBuilder("${27.toChar()}[45m"+text+"${27.toChar()}[0m").toString())
        "cyan" -> println(StringBuilder("${27.toChar()}[46m"+text+"${27.toChar()}[0m").toString())
        "green" -> println(StringBuilder("${27.toChar()}[44m"+text+"${27.toChar()}[0m").toString())
        "red" -> println(StringBuilder("${27.toChar()}[41m"+text+"${27.toChar()}[0m").toString())
        else -> println(text)
    }
}


fun createDeckOfCards() : ArrayList<Card>{
    val deck = ArrayList<Card>()
    for(i in 0..3) {
        for (j in 1..13) {
            val card = Card(j,i)
            deck.add(card)
        }
    }
    return deck
}

fun main(){
    val newGame = Game()
    newGame.welcome()
    newGame.start()
}