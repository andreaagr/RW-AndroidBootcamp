class Dealer(private var deckOfCards : ArrayList<Card>){
    var hand = ArrayList<Card>()

    fun giveCards(number : Int, player : Player){
        ///Checar los null y not null
        for(i in 1..number){
            var randomCard = deckOfCards.random()
            player.hand.add(randomCard)
            deckOfCards?.remove(randomCard)
            randomCard.printCard()
        }
    }

    fun cardDealer(){
        var randomCard = deckOfCards!!.random()
        hand.add(randomCard)
        deckOfCards?.remove(randomCard)
        println("Dealer")
        randomCard.printCard()
    }
}
