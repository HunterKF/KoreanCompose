package model

import com.google.gson.annotations.SerializedName

class ListWordModel {
    @SerializedName("dataGrammar")
    var dataGrammar: ArrayList<GrammarCard> = ArrayList()

    @SerializedName("dataWord")
    var dataWord: ArrayList<WordCard> = ArrayList()
}