import com.example.koreanpractice.LoadWordsAndGrammar
import com.example.koreanpractice.MainActivity
import com.example.koreanpractice.databinding.ActivityMainBinding
import com.google.gson.Gson
import model.ListGrammarModel
import model.ListWordModel

private lateinit var binding: ActivityMainBinding

class viewModifier {
    val loadContent = LoadWordsAndGrammar()
    val grammarList = Gson().fromJson<ListGrammarModel>(
        loadContent.loadGrammarJson(this),
        ListGrammarModel::class.java
    )
    val wordList = Gson().fromJson<ListWordModel>(
        loadContent.loadWordJson(MainActivity.this),
        ListWordModel::class.java
    )

    val start = 0
    val grammarEnd = grammarList.data.size
    val wordExnd = wordList.data.size
    val wordView = binding.itemWord.text
    var grammarView = binding.itemGrammar.text

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
    val randomNumber = rand(start, grammarEnd)

    fun getGrammarDef() {
        grammarView = grammarList.data[randomNumber].grammar
    }


}