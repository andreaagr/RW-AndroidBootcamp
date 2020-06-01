import java.lang.StringBuilder

class Card(private val pip : Int, private val suit: Int) {
    private var symbol = ""
    private var figure = ""
    private var value : Int? = null
    private var isAnAce = false

    init {
        createSymbol()
        assignValue()
    }

    private fun createSymbol(){
        figure = when(suit){
            0 -> putColor("\u25C6")
            1 -> """${'\u2660'}"""
            2 -> putColor("\u2665")
            3 -> """${'\u2663'}"""
            else -> ""
        }
    }

    private fun assignValue(){                              //Aces
        when {
            pip == 1 -> {
                symbol = "A"
                isAnAce = true
            }
            pip > 10 -> {                          //Face cards
                symbol = if(pip == 11) "J" else if(pip == 12) "Q" else "K"
                value = 10
            }
            else -> {                                      //Numbers
                symbol = pip.toString()
                value = pip
            }
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

    private fun putColor(char : String) : String{
        return StringBuilder("${27.toChar()}[31m"+char+"${27.toChar()}[0m").toString()
    }

    fun getValue() : Int{
        return value!!
    }

    fun getisAnAce() : Boolean{
        return isAnAce
    }
}