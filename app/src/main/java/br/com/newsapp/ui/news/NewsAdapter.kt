package br.com.newsapp.ui.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.newsapp.data.model.News
import javax.inject.Inject

class NewsAdapter @Inject constructor(
        var list: List<News> = emptyList(),
        private val onItemClick: (News) -> Unit
): RecyclerView.Adapter<NewsViewHolder>() {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(parent, onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}