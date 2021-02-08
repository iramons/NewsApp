package br.com.newsapp.commom.extensions

import android.content.SharedPreferences

object SharedPreferencesExtensions {

    fun SharedPreferences.prefsSave(key: String, text: String?) {
        val edt = this.edit()
        edt.putString(key, text)
        edt.apply()
    }
    fun SharedPreferences.prefsSave(key: String, value: Int) {
        val edt = this.edit()
        edt.putInt(key, value)
        edt.apply()
    }
    fun SharedPreferences.prefsSave(key: String, value: Long) {
        val edt = this.edit()
        edt.putLong(key, value)
        edt.apply()
    }
    fun SharedPreferences.prefsSave(key: String, value: Float) {
        val edt = this.edit()
        edt.putFloat(key, value)
        edt.apply()
    }
    fun SharedPreferences.prefsSave(key: String, status: Boolean) {
        val edt = this.edit()
        edt.putBoolean(key, status)
        edt.apply()
    }

    inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
        when(T::class) {
            Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
            Float::class -> return this.getFloat(key, defaultValue as Float) as T
            Int::class -> return this.getInt(key, defaultValue as Int) as T
            Long::class -> return this.getLong(key, defaultValue as Long) as T
            String::class -> return (this.getString(key, defaultValue as String?) as T)
            else -> {
                if (defaultValue is Set<*>) {
                    return (this.getStringSet(key, defaultValue as Set<String>?) as T)
                }
            }
        }
        return defaultValue!!
    }

    inline fun <reified T> SharedPreferences.put(key: String, value: T): T? {
        val editor = this.edit()
        when(T::class) {
            Boolean::class -> editor.putBoolean(key, value as Boolean)
            Float::class -> editor.putFloat(key, value as Float)
            Int::class -> editor.putInt(key, value as Int)
            Long::class -> editor.putLong(key, value as Long)
            String::class -> editor.putString(key, value as String?)
            else -> {
                if (value is Set<*>) {
                    editor.putStringSet(key, value as Set<String>?)
                }
            }
        }
        editor.apply()
        return value
    }

    fun SharedPreferences.clear() {
        val edt = this.edit()
        edt.clear()
        edt.apply()
    }

    fun SharedPreferences.remove(key: String): String {
        val editor = this.edit()
        editor.remove(key).apply()
        return key
    }
}