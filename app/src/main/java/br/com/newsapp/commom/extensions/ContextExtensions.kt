package br.com.newsapp.commom.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import br.com.newsapp.R
import br.com.newsapp.data.source.remote.Resource

object ContextExtensions {

    infix fun Context.color(@ColorRes id: Int) = when {
        isAtLeastMarshmallow() -> resources.getColor(id, null) else -> resources.getColor(id)
    }

    fun <T> Context.showError(errorResource: Resource<T>) {
        val defaultMessage: String = this.getString(R.string.error_default)
        var message = errorResource.message(this)
        if (message.isBlank())
            message = defaultMessage

        this.showError(message = message)
    }

    fun Context.showError(title: String = getString(R.string.ops), message: String) {
        this.showInfoDialog(title, message)
    }

    fun Context.showInfoDialog(title: String, message: String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setIcon(R.drawable.round_warning_amber_24)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok") {
                dialog, _ -> dialog.dismiss()
        }
        alertDialog.show()
    }

    fun Context.hideKeyboard(view: Activity) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}

