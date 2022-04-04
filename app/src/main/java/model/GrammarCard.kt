package model

import com.google.gson.annotations.SerializedName

class GrammarCard {
    @SerializedName("grammar")
    var grammar: String = ""

    @SerializedName("def")
    var def: String = ""

    @SerializedName("example")
    var exampleSentence: String = ""
}