package model

import com.google.gson.annotations.SerializedName

class ListGrammarModel {
    @SerializedName("data")
    var data: ArrayList<GrammarCard> = ArrayList()
}