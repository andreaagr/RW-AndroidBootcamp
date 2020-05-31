class Player(private val name : String) {
    var hand = ArrayList<Card>()
    var points = 0

    fun printHand(){
        for(card in hand){
            card.printCard()
        }
    }

    fun getName(){
        println("Player $name")
    }
}