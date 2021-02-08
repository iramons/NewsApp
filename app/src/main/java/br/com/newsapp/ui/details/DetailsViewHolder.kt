package br.com.newsapp.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ImageView
import android.widget.TextView
import br.com.newsapp.BaseApp
import br.com.newsapp.R
import br.com.newsapp.commom.base.BaseViewHolder
import br.com.newsapp.commom.extensions.converTextWithHtmlTags
import br.com.newsapp.data.model.Document
import com.bumptech.glide.Glide


class DetailsViewHolder(view: View, private val itemClick: (Document) -> Unit) : BaseViewHolder<Document>(view) {

//    val url: String? = null,
//    val product: String? = null,
//    val editorial: String? = null,
//    val subEditorial: String? = null,
//    val origin: String? = null,

    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvThinLine: TextView = itemView.findViewById(R.id.tv_thin_line)
    private val tvDateTime: TextView = itemView.findViewById(R.id.tv_date_time_publish)
    private val tvCredit: TextView = itemView.findViewById(R.id.tv_credit)
    private val ivImage: ImageView = itemView.findViewById(R.id.iv_image)
    private val tvImageLegend: TextView = itemView.findViewById(R.id.tv_legend_image)
    private val wvformattedBody: WebView = itemView.findViewById(R.id.wv_formatted_body)

//    private val tvProduct: TextView = itemView.findViewById(R.id.tv_product)
//    private val tvEditorial: TextView = itemView.findViewById(R.id.tv_editorial)
//    private val tvSubEditorial: TextView = itemView.findViewById(R.id.tv_sub_editorial)
//    private val tvOrigin: TextView = itemView.findViewById(R.id.tv_origin)

    companion object {
        private val layoutId: Int
            get() = R.layout.item_details

        fun create(parent: ViewGroup, itemClick: (Document) -> Unit): DetailsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(layoutId, parent, false)
            return DetailsViewHolder(view, itemClick)
        }
    }

    override fun bind(item: Document) {
        tvTitle.text = item.title
        tvThinLine.text = item.thinLine
        tvCredit.text = "${item.credit}, ${item.source}"
        tvDateTime.text = "${item.datePub} - ${item.timePub}"
        tvImageLegend.text = "${item.legendImage?.converTextWithHtmlTags()} ● Foto: ${item.creditImage}"

        Glide.with(itemView.context)
                .applyDefaultRequestOptions(BaseApp.glideOptions)
                .load(item.image)
                .into(ivImage)

        item.formattedBody?.let {
            wvformattedBody.loadDataWithBaseURL(
                item.url,
                it,
                "text/html",
                "UTF-8",
                null)
        }
    }
}