import java.lang.StrictMath.min
import kotlin.math.max
import kotlin.system.exitProcess

class Game {
    private var players = ArrayList<Player>()
    private val deckOfCards = createDeckOfCards()
    private val dealer = Dealer(deckOfCards)

    fun welcome() : Boolean{
        /*
          Min players: 1
          Max players: 6
        */
        println("Please input the number of players:")
        val numberOfPlayers = readLine()!!.toInt()
        for(i in 1..numberOfPlayers){
            println("Input the name of the player:")
            val name = readLine().toString()
            val player = Player(name)
            players.add(player)
        }
        return true

    }

    private fun nextTurn(){
        var input: String
        var salir = true
        //if(!dealer.findWinner)
            for(player in players){
                if(player.stillInGame){
                    println("Next turn: ${player.getName()}")
                    while (salir) {
                        println("Do you want another card?(y/n)")
                        input = readLine().toString()
                        if (input == "y")
                            dealer.giveCards(1, player)
                        else
                            salir = false

                        if(!player.stillInGame)
                            salir = false
                    }
                }
                salir = true
            }
    }

    fun start(){
        deckOfCards.shuffle()                                                                            //Mix the cards
        for (player in players) {
            println("Player ${player.getName()}")
            dealer.giveCards(2, player)
        }
        dealer.cardDealer()
        //if(!dealer.findWinner){
            dealer.turn = 2
            nextTurn()
            dealer.cardDealer()
            dealer.stand()
            nameWinner()
        //}
    }

    fun nameWinner() {
        val differenceDealer = 21 - dealer.getPoints()
        var differencePlayer: Int
        var winner = ""
        if (!dealer.houseLose) {
            for (player in players) {
                differencePlayer = if (player.getPoints() in 1..21 && player.getPoints2() in 1..21)
                    21 - max(player.getPoints(), player.getPoints2())
                else if (player.getPoints() > 0 && player.getPoints2() > 0) {
                    21 - min(player.getPoints(), player.getPoints2())
                } else
                    21 - max(player.getPoints(), player.getPoints2())

                if (player.stillInGame)
                    if (differencePlayer != differenceDealer) {
                        if (differencePlayer < differenceDealer) {
                            println("Player ${player.getName()} wins!")
                        } else
                            println("PLayer ${player.getName()} loses :(")
                    } else {
                        println("Draw!")
                    }

            }
        } else{
            for(player in players){
                if(player.stillInGame)
                    println("Player ${player.getName()} wins!")
            }
        }
    }
}