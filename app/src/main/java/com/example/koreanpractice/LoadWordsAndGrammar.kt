package com.example.koreanpractice

import android.content.Context
import android.util.Log
import com.example.koreanpractice.databinding.ActivityMainBinding
import com.google.gson.Gson
import model.ListGrammarModel
import model.ListWordModel
import java.io.InputStream
import java.lang.Exception

private lateinit var binding: ActivityMainBinding

class LoadWordsAndGrammar {
    fun loadWordJson(context: Context) {
        var input: InputStream? = null
        var jsonString: String

        try {
            input = context.assets.open("text.json")

            val size = input.available()

            //Create a buffer with the size
            val buffer = ByteArray(size)

            //Read data from InputStream into the buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            val loadedWordList = Gson().fromJson<ListWordModel>(
                jsonString,
                ListWordModel::class.java
            )
            wordList = loadedWordList
            Log.d("fromLoadWordsAndGrammar", "${wordList}")
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }
        return
    }

    fun loadGrammarJson(context: Context) {
        var input: InputStream? = null
        var jsonString: String
        Log.d("loadGrammarJson", "has fired.")
        try {
            input = context.assets.open("grammar.json")

            val size = input.available()

            //Create a buffer with the size
            val buffer = ByteArray(size)

            //Read data from InputStream into the buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            Log.d("loadGrammarJson", "This is before loadedGrammarList")
            val loadedGrammarList = Gson().fromJson<ListGrammarModel>(
                jsonString,
                ListGrammarModel::class.java
            )
            Log.d("loadGrammarJson", "This is after loadedGrammarList")
            grammarList = loadedGrammarList
            Log.d("loadGrammarJson", "The current value of grammarList is ${grammarList}")
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }
        return
    }

    var grammarList: ListGrammarModel? = null

    var wordList: ListWordModel? = null

//

}


