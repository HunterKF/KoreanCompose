package model

import com.google.gson.annotations.SerializedName

class WordCard {
    @SerializedName("word")
    var word: String = ""

    @SerializedName("def")
    var def: String = ""
}