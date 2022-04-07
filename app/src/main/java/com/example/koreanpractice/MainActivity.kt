package com.example.koreanpractice

import adapter.ItemAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koreanpractice.databinding.ActivityMainBinding
import data.Datasource
import model.ListGrammarModel
import model.ListWordModel
import model.PracticeCard

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
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            true
        ).apply { stackFromEnd = true }

        /* This improves performance*/
        recyclerView.setHasFixedSize(false)
//This allows for imeOption="actionDone" to work, as well as having textMultiline work.
        binding.itemPracticeInput.setRawInputType(TYPE_CLASS_TEXT);

//        Loads the lists
        val grammar = loadGrammar()
        val word = loadWord()
//        Calls words onCreate
        changeGrammarView(returnRandomGrammar(grammar))
        changeWordView(returnRandomWord(word!!))

        onActionDone(binding.itemPracticeInput, word, grammar)

    }

    /*When user clicks the done button on keyboard, all functions are called.*/
    private fun onActionDone(search: EditText, itemWord: ListWordModel, itemGrammar: ListGrammarModel){
        search.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->
            Log.d("doSomething() ", "The function has fired.")
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.d("doSomething() ", "The 1st if statement has fired.")
                // Do something of your interest.
                getSentence()
                changeWordView(returnRandomWord(itemWord))
                changeGrammarView(returnRandomGrammar(itemGrammar))
                binding.itemPracticeInput.setText("")

                Log.d("doSomething() ", "The function has finished.")
                return@OnEditorActionListener true
            }
            false
        })
    }

    private val loadWords = LoadWordsAndGrammar()
    private fun loadGrammar(): ListGrammarModel {
        loadWords.loadGrammarJson(applicationContext)
        val grammar = loadWords.grammarList
        Log.d("randomGrammar", "Random grammar is ${grammar}")
        return grammar!!
    }

    /*Get random grammar and use it in the changeGrammarView function*/
    data class GrammarData(var grammar: String, var def: String, var exampleSentence: String)

    private fun returnRandomGrammar(item: ListGrammarModel): GrammarData {
        Log.d("returnRandomGrammar", "The function has fired. The passed parameter is ${item}")
        var randomNumber = rand(item.data.size)
        Log.d("returnRandomGrammar",
            "The function has fired. The index is ${item.data.size} and the random number was ${randomNumber}")

        if (randomNumber === item.data.size) {
            randomNumber -= 1
            Log.d("returnGramTooBig",
                "The number was too big. The new random number is ${randomNumber}")
        }
        val grammar = item.data[randomNumber].grammar
        val def = item.data[randomNumber].def
        val exampleSentence = item.data[randomNumber].exampleSentence
        return GrammarData(grammar, def, exampleSentence)
    }

    /*This changes the grammarView in the xml.
     * Later add def and example*/
    private fun changeGrammarView(item: GrammarData) {
        binding.itemGrammar.text = item.grammar
    }

    /*Get random word and use it in the changeWordView function*/
    data class WordData(var word: String, var def: String)

    private fun returnRandomWord(item: ListWordModel): WordData {
        Log.d("returnRandomWord", "The function has fired. The passed parameter is ${item}")
        var randomNumber = rand(item.data.size)
        Log.d("returnRandomWord",
            "The function has fired. The index is ${item.data.size} and the random number was ${randomNumber}")
        if (randomNumber === item.data.size) {
            randomNumber -= 1
            Log.d("returnWordTooBig",
                "The number was too big. The new random number is ${randomNumber}")
        }
        val word = item.data[randomNumber].word
        val def = item.data[randomNumber].def
        return WordData(word, def)
    }

    /*This changes the wordView in the xml.
    * Later add def and example*/
    private fun changeWordView(item: WordData) {
        binding.itemWord.text = item.word
    }

    /*Random number function*/
    private fun rand(end: Int): Int {
        val start = 0
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }


    private fun loadWord(): ListWordModel? {
        loadWords.loadWordJson(applicationContext)
        val word = loadWords.wordList
        Log.d("randomWord", "${word?.data?.get(9)?.def}")

        return word!!
    }

    private fun getSentence() {

        Log.d("getSentence", "has been activated")

        val wordInField = binding.itemWord.text.toString()
        val grammarInField = binding.itemGrammar.text.toString()
        var stringInField = binding.itemPracticeInput.text.toString()

        if (stringInField == null) {
            return
        }

        this.dataSource.cards.add(PracticeCard(wordInField, grammarInField, stringInField))


    }

    // call loadGrammarJson when the activity starts, so that the instance var has all the words loaded already
    // create a method that pulls out a random word from the model you loaded
    // call that method in newWord()
    // use the returned word

    //    Uses rand() to get a word from JSON

}