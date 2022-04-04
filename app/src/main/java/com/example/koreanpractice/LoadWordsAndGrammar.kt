package com.example.koreanpractice

import android.content.Context
import com.google.gson.Gson
import model.ListWordModel
import viewModifier
import java.io.InputStream
import java.lang.Exception



class LoadWordsAndGrammar {
    fun loadWordJson(context: Context): String?{
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
            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }
        return null
    }

    fun loadGrammarJson(context: viewModifier): String?{
        var input: InputStream? = null
        var jsonString: String

        try {
            input = context.assets.open("grammar.json")

            val size = input.available()

            //Create a buffer with the size
            val buffer = ByteArray(size)

            //Read data from InputStream into the buffer
            input.read(buffer)

            // Create a json String
            jsonString = String(buffer)
            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            // Must close the stream
            input?.close()
        }
        return null
    }

}


