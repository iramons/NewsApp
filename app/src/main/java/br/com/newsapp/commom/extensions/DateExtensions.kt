package br.com.newsapp.commom.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateExtensions {

    fun String.stringToDate() : Date {
        var date = Date()
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            date = format.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    fun Date.dateToString() : String {
        var string = ""
        val dateFormat = SimpleDateFormat("dd/MM/yyy - HH:mm")
        try {
            string = dateFormat.format(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return string
    }


}