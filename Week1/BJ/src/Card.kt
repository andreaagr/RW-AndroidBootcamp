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

    fun createSymbol(){
        figure = when(suit){
            0 -> putColor("\u25C6")
            1 -> """${'\u2660'}"""
            2 -> putColor("\u2665")
            3 -> """${'\u2663'}"""
            else -> ""
        }
    }

    fun assignValue(){                              //Aces
        if(pip == 1){
            symbol = "A"
            isAnAce = true
        }else if(pip > 10){                          //Face cards
            if(pip == 11)
                symbol = "J"
            else if(pip == 12)
                symbol = "Q"
            else
                symbol = "K"
            value = 10
        }else{                                      //Numbers
            symbol = pip.toString()
            value = pip.toInt()
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

    fun getValue() : Int{
        return value!!
    }

    fun getisAnAce() : Boolean{
        return isAnAce
    }
}