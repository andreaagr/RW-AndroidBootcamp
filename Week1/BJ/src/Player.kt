class Player(private val name : String) {
    var hand = ArrayList<Card>()
    private var points = 0
    var stillInGame = true
    var hasAnAce = false
    private var points2 = 0

    fun getName() : String{
        return name
    }

    fun countPoints(){
        for(card in hand){
            if(card.getisAnAce()){
                hasAnAce = true
            }else{
                points += card.getValue()
            }
        }
        if(hasAnAce){
            points2 = points + 11
            points += 1
        }

    }

    fun printPoints(){
        if(hasAnAce)
            colorMessage("The player has 2 options to choose: $points and $points2 points","cyan")
        else
            colorMessage("The player has $points points","cyan")
    }

    fun getPoints() : Int{
        return points
    }

    fun getPoints2() : Int{
        return points2
    }

    fun setPoints(){
        points = 0
        points2 = 0
    }
}
