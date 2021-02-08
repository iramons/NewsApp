package br.com.newsapp.commom.extensions

import android.os.Build
import android.text.Html

fun String?.isNotNullOrEmpty() = !this.isNullOrEmpty()
fun String?.isNotNullOrBlank() = !this.isNullOrBlank()

fun String.converTextWithHtmlTags() : String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
     else Html.fromHtml(this).toString()
}
