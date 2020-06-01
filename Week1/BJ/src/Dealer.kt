class Dealer(private var deckOfCards : ArrayList<Card>) {
    private var hand = ArrayList<Card>()
    var findWinner = false
    //var turn = 0

    fun giveCards(number : Int, player : Player){
        for(i in 1..number){
            val randomCard = deckOfCards.random()
            player.hand.add(randomCard)
            deckOfCards.remove(randomCard)
            randomCard.printCard()
        }
        if(player.getPoints() == 0)
            player.countPoints()
        else {
            player.setPoints()
            player.countPoints()
        }
        player.printPoints()
        evaluateGamePlayer(player)
    }

    fun cardDealer(){
        val randomCard = deckOfCards.random()
        hand.add(randomCard)
        deckOfCards.remove(randomCard)
        println("Dealer")
        randomCard.printCard()
    }

    private fun evaluateGamePlayer(player: Player){
        if(player.hasAnAce){
            if(player.getPoints() == 21 || player.getPoints2() == 21){
                //if(turn == 1)
                println("Congratulations! ${player.getName()} have a BlackJack")
                //else
                //    println("Congratulations! ${player.getName()} have 21 points")
                findWinner = true
            }
            else{
                player.stillInGame = player.getPoints() < 21 || player.getPoints2() < 21
                if(!player.stillInGame)
                    println("${player.getName()} lost the game")
                else
                    println("${player.getName()} still in game")
            }
        }else if(player.getPoints() > 21){
            println("${player.getName()} lost the game")
            player.stillInGame = false

        }else if(player.getPoints() == 21){
            //if(turn == 1)
            println("Congratulations! ${player.getName()} have a BlackJack")
            //else
            //    println("Congratulations! ${player.getName()} have 21 points")
            findWinner = true
        }else{
            println("${player.getName()} still in game")
        }
    }
}
