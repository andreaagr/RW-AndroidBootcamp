/***
 *
 * @author Andrea García
 * Week 1
 *
 * ***/


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
    val new_game = Game()
    new_game.welcome()
    new_game.start()
}