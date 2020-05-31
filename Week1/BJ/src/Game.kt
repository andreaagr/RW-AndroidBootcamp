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
        for(player in players){
            if(player.points < 21){
                println("Do you want another card?(y/n)")
                input = readLine().toString()
                if(input == "y")
                    dealer.giveCards(1,player)
            }
        }
    }

    fun start(){
        deckOfCards.shuffle()                           //Mix the cards
        for (player in players) {
            player.getName()
            dealer.giveCards(2, player)
        }
        dealer.cardDealer()
    }
}