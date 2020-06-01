class Game {
    private var players = ArrayList<Player>()
    private val deckOfCards = createDeckofCards()
    val dealer = Dealer(deckOfCards)


    fun welcome(){
        println("Welcome to Andrea's Casino :)")
        println("Please input the number of players:")
        val numberOfPlayers = readLine()!!.toInt()
        for(i in 1..numberOfPlayers){
            println("Input the name of the player:")
            val name = readLine().toString()
            var player = Player(name)
            players.add(player)
        }
    }

    fun nextTurn(){
        var input = ""
        var salir = true
        if(!dealer.findWinner)
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

                        if(!player.stillInGame || dealer.findWinner)
                            salir = false
                    }
                }
                salir = true
            }
        }

    fun start(){
        deckOfCards.shuffle()                           //Mix the cards
        dealer.turn = 1
        for (player in players) {
            println("Player ${player.getName()}")
            dealer.giveCards(2, player)
        }
        dealer.cardDealer()
        nextTurn()
    }
}