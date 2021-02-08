package br.com.newsapp.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.newsapp.BaseApp
import br.com.newsapp.R
import br.com.newsapp.commom.base.BaseViewHolder
import br.com.newsapp.commom.extensions.DateExtensions.dateToString
import br.com.newsapp.commom.extensions.DateExtensions.stringToDate
import br.com.newsapp.data.model.News
import com.bumptech.glide.Glide

class NewsViewHolder(view: View, private val itemClick: (News) -> Unit) : BaseViewHolder<News>(view) {

    private val tvHat: TextView = itemView.findViewById(R.id.tv_hat)
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvThinLine: TextView = itemView.findViewById(R.id.tv_thin_line)
    private val tvDateTime: TextView = itemView.findViewById(R.id.tv_date_time_publish)
    private val tvCredit: TextView = itemView.findViewById(R.id.tv_credit)
    private val ivImage: ImageView = itemView.findViewById(R.id.iv_image)
    private val tvImageCredits: TextView = itemView.findViewById(R.id.tv_image_credit)

    companion object {
        private val layoutId: Int
            get() = R.layout.item_news

        fun create(parent: ViewGroup, itemClick: (News) -> Unit): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(layoutId, parent, false)
            return NewsViewHolder(view, itemClick)
        }
    }

    override fun bind(item: News) {
        tvHat.text = item.hat
        tvTitle.text = item.title
        tvThinLine.text = item.thinLine
        tvImageCredits.text = item.imageCredit

        tvCredit.text = if (item.credit != null)
            "${item.credit}, ${item.source}"
        else item.source

        val date = item.dateTimePublication?.stringToDate()
        tvDateTime.text = date?.dateToString()

        Glide.with(itemView.context)
                .applyDefaultRequestOptions(BaseApp.glideOptions)
                .load(item.image)
                .into(ivImage)

        itemView.setOnClickListener { itemClick(item) }
    }
}