package com.example.koreanpractice

import adapter.ItemAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koreanpractice.databinding.ActivityMainBinding
import com.google.gson.Gson
import data.Datasource
import model.ListGrammarModel
import model.ListWordModel
import model.PracticeCard
import viewModifier

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataSource: Datasource


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Hides app bar*/
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Creates data source*/
//        val myDataset = Datasource().loadPracticeCard()

        this.dataSource = Datasource()
        /*Finds a reference to the RecyclerView within the layout*/
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        /*Create a new ItemAdapter instance
       * The parameters are set in ItemAdapter.kt (context, and list)*/
        recyclerView.adapter = ItemAdapter(this, this.dataSource.cards)
        recyclerView.layoutManager = LinearLayoutManager(this)

        /* This improves performance*/
        recyclerView.setHasFixedSize(false)

        binding.button.setOnClickListener { getSentence() }
//        binding.practiceInput.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }

        val loadWords = LoadWordsAndGrammar()
        fun loadGrammar(): ListGrammarModel? {
            val grammar = loadWords.grammarList
            loadWords.loadGrammarJson(applicationContext)
            Log.d("randomWord", "${grammar?.data?.size}")
            return grammar
        }
        fun loadWord(): ListWordModel? {
            val word = loadWords.wordList
            loadWords.loadWordJson(applicationContext)
            Log.d("randomGrammar", "${word?.data?.get(9)?.def}")

            return word
        }

    }

    private fun loadGrammar() {

    }
    private fun getSentence() {

        Log.d("getSentence", "has been activated")

        val wordInField = binding.itemWord.text.toString()
        val grammarInField = binding.itemGrammar.text.toString()
        val stringInField = binding.itemPracticeInput.text.toString()

        if (stringInField == null) {
            return
        }

        this.dataSource.cards.add(PracticeCard(wordInField, grammarInField, stringInField))

    }

    //    Function to make a random number using variables


    // call loadGrammarJson when the activity starts, so that the instance var has all the words loaded already
    // create a method that pulls out a random word from the model you loaded
    // call that method in newWord()
    // use the returned word

    //    Uses rand() to get a word from JSON

    private fun randomWord(item: String) {
        val item = ""


    }
//    private fun newWord(): String {
//        val loadContent = LoadWordsAndGrammar()
//        val wordModel = loadContent.
//
//        var viewWord = ""
//        val start = 0
//        val end = wordModel.data.size
//        val randomNumber = rand(start, end)
//
//
//        viewWord = wordModel.data[randomNumber].word
//        Log.d("newWord", "${viewWord}")
//
//        Log.d("newWord", "Size: ${wordModel.data.size}")
//        Log.d("newWord", "Data: ${wordModel.data[1].def}")
//
//        Log.d("Rand()", "${rand(start, end)}")
//        return viewWord
//    }



    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            getSentence()
            return true
        }
        return false
    }


}