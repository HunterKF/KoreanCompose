package model

import com.google.gson.annotations.SerializedName

class ListWordModel {
    @SerializedName("data")
    var data: ArrayList<WordCard> = ArrayList()
}