import java.lang.StringBuilder

class Card(
    private val pip : Int,
    private val suit: Int
){
    private var symbol = ""
    private var figure = ""
    private var value = 0


    init {
        createSymbol()
        assignValue()
    }

    fun createSymbol(){
        if(suit == 0){                              //Diamonds
            //figure = """${'\u25C6'}"""
            figure = putColor("\u25C6")
        }else if(suit == 1){                        //Spades
            figure = """${'\u2660'}"""
        }else if(suit == 2){                        //Hearts
            figure = putColor("\u2665")
        }else{                                      //Clubs
            figure = """${'\u2663'}"""
        }

    }

    fun assignValue(){                              //Aces
        if(pip == 1){
            symbol = "A"
            value = 11
        }else if(pip > 9){                          //Face cards
            if(pip == 10)
                symbol = "J"
            else if(pip == 11)
                symbol = "Q"
            else
                symbol = "K"
            value = 10
        }else{                                      //Numbers
            symbol = pip.toString()
            value = pip
        }

    }
    fun printCard(){
        if(suit == 2 || suit == 0)
            symbol = putColor(symbol)
        println(
            """
                .-------------.
                | $symbol           |
                |             |
                |             |
                |      $figure      |
                |             |
                |             |
                |            $symbol|
                `-------------´
            """.trimIndent()

        )
    }

    fun putColor(char : String) : String{
        return StringBuilder("${27.toChar()}[31m"+char+"${27.toChar()}[0m").toString()
    }
}

fun createDeckofCards() : ArrayList<Card>{
    var deck = ArrayList<Card>()
    for(i in 0..4) {
        for (j in 1..12) {
            val card = Card(j,i)
            deck.add(card)
        }
    }
    return deck
}

fun main(){
    val deckOfCards = createDeckofCards()
    deckOfCards.shuffle()                           //Mix the cards
    var carta = deckOfCards.random()
    carta.printCard()
}