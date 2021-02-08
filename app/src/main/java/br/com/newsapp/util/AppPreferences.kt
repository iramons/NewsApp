package br.com.newsapp.util

import android.content.Context
import android.content.SharedPreferences
import br.com.newsapp.commom.extensions.SharedPreferencesExtensions.clear
import br.com.newsapp.commom.extensions.SharedPreferencesExtensions.get
import br.com.newsapp.commom.extensions.SharedPreferencesExtensions.put
import br.com.newsapp.commom.extensions.SharedPreferencesExtensions.remove
import br.com.newsapp.util.Preferences.HAS_PERMISSIONS


class AppPreferences {

    companion object {

        private var initialized: Boolean = false

        private lateinit var prefs: SharedPreferences

        fun init(context: Context) {
            if (!initialized) {
                synchronized(AppPreferences::class.java) {
                    this.prefs = context.getSharedPreferences(Preferences.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    initialized = true
                }
            }
        }

        fun getStringValue(KEY_NAME: String, defValue: String? = null): String? {
            return prefs.get(KEY_NAME, defValue)
        }

        fun getIntValue(KEY_NAME: String, defValue: Int = 0): Int {
            return prefs.get(KEY_NAME, defValue)
        }

        fun getLongValue(KEY_NAME: String, defValue: Long = 0): Long {
            return prefs.get(KEY_NAME, defValue)
        }

        fun getFloatValue(KEY_NAME: String, defValue: Float = 0F): Float {
            return prefs.get(KEY_NAME, defValue)
        }

        fun getBooleanValue(KEY_NAME: String, defaultValue: Boolean): Boolean {
            return prefs.get(KEY_NAME, defaultValue)
        }

        fun save(KEY_NAME: String, text: String) {
            prefs.put(KEY_NAME, text)
        }

        fun save(KEY_NAME: String, value: Int) {
            prefs.put(KEY_NAME, value)
        }

        fun save(KEY_NAME: String, value: Long) {
            prefs.put(KEY_NAME, value)
        }

        fun save(KEY_NAME: String, value: Float) {
            prefs.put(KEY_NAME, value)
        }

        fun save(KEY_NAME: String, status: Boolean) {
            prefs.put(KEY_NAME, status)
        }

        fun clearSharedPreferences() {
            prefs.clear()
        }

        fun removeValue(KEY_NAME: String) {
            prefs.remove(KEY_NAME)
        }
    }
}