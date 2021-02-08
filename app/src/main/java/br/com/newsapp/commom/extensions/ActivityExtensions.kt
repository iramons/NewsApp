package br.com.newsapp.commom.extensions

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.newsapp.R

object ActivityExtensions {

    fun Activity.rootView(): View? {
        return this.window.decorView.findViewById<View>(android.R.id.content)
    }

    fun Activity.statusBarVisible() {
        val w = this.window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun Activity.statusBarTransparent() {
        val w = this.window
        w.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    /**
     * config Toolbar para AppCompatActivity
     */
    fun AppCompatActivity.setupToolbar(
        toolbar: Toolbar? = this.findViewById(R.id.toolbar_default),
        title: String = "",
        subtitle: String? = "",
        showBackButton: Boolean = false,
        closeBackButton: Boolean = false
    ) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.subtitle = subtitle

        if (showBackButton) {
            if (closeBackButton) {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.round_close_24)
            } else {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.round_arrow_back_24)
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}