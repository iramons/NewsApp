package br.com.newsapp.ui.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.newsapp.data.model.Document
import javax.inject.Inject

class DetailsAdapter @Inject constructor(
        var list: List<Document> = emptyList(),
        private val onItemClick: (Document) -> Unit
): RecyclerView.Adapter<DetailsViewHolder>() {

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.create(parent, onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}