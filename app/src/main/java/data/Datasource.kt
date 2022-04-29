package data

import model.PracticeCard

class Datasource {

    val cards = mutableListOf<PracticeCard>(
    )



    init {
//        this.cards = this.loadPracticeCard().toMutableList()
    }

//Alex code
    fun addCard(word: String, grammar: String, sentence: String) {
        var newCard = PracticeCard(word, grammar, sentence)
    }


}