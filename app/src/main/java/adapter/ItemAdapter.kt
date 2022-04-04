package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.koreanpractice.R
import model.PracticeCard

class ItemAdapter(
    private val context: Context,
    private val dataset: MutableList<PracticeCard>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pointWord: TextView = itemView.findViewById(R.id.item_word)
        val pointGrammar: TextView = itemView.findViewById(R.id.item_grammar)
        val pointSentence: TextView = itemView.findViewById(R.id.item_sentence)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.pointWord.text = item.stringWordResourceId
        holder.pointGrammar.text = item.stringGrammarResourceId
        holder.pointSentence.text = item.stringSentenceResourceId
    }

    override fun getItemCount() = dataset.size

}