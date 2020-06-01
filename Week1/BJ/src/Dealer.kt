class Dealer(private var deckOfCards : ArrayList<Card>) {
    private var hand = ArrayList<Card>()
    var houseLose = false
    private var points = 0
    var turn = 1

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
        println("\n")
    }

    fun cardDealer(){
        val randomCard = deckOfCards.random()
        hand.add(randomCard)
        deckOfCards.remove(randomCard)
        colorMessage("Dealer","purple")
        randomCard.printCard()
        println("\n")
        if(randomCard.getisAnAce())
            points+=11
        else
            points+= randomCard.getValue()

    }

    private fun evaluateGamePlayer(player: Player){
        if(player.hasAnAce){
            if(player.getPoints() == 21 || player.getPoints2() == 21){
                if(turn == 1)
                    colorMessage("Congratulations! ${player.getName()} have a BlackJack","green")
                else
                    colorMessage("Congratulations! ${player.getName()} have 21 points","green")
            }
            else{
                player.stillInGame = player.getPoints() < 21 || player.getPoints2() < 21
                if(!player.stillInGame)
                    colorMessage("${player.getName()} lost the game", "red")
                else
                    colorMessage("${player.getName()} still in game","cyan")
            }
        }else if(player.getPoints() > 21){
            colorMessage("${player.getName()} lost the game", "red")
            player.stillInGame = false

        }else if(player.getPoints() == 21){
            if(turn == 1)
                colorMessage("Congratulations! ${player.getName()} have a BlackJack","green")
            else
                colorMessage("Congratulations! ${player.getName()} have 21 points","green")
            //findWinner = true
        }else{
            colorMessage("${player.getName()} still in game", "cyan")
        }
    }

    fun stand(){
        if(points <= 16){
            while(points < 17) {
                cardDealer()
            }
        }
        if(points > 21) {
            colorMessage("The house lose :(","red")
            houseLose = true
            //findWinner = true
        }
        else
            println("The dealer stands with $points")
    }

    fun getPoints() : Int{
        return points
    }

}
//}
