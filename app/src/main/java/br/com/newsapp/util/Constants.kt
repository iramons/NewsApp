package br.com.newsapp.util

// path calls API
object APIPaths {
    const val login = "login"
    const val news = "news"
    const val news_details = "news/{id}"
}

object Preferences {
    const val SHARED_PREFERENCES_NAME           = "newsapp_user_preferences"
    const val SHARED_PREFERENCES_ENCRYPT_NAME   = "newsapp_encrypt"
    const val TIME_TOKEN_EXPIRES: Long          = 2 // in hour
    const val LOGIN_STATUS                      = "isUserLoggedIn"
    const val LOGIN_EMAIL_TEMP                  = "emailLoggedInTemp"
    const val LOGIN_PASSWORD_TEMP               = "passwordLoggedInTemp"
    const val FIRST_TIME                        = "isFirstTime"
    const val ACCESS_TOKEN                      = "token"
    const val HAS_PERMISSIONS                   = "hasAllPermissions"
    const val DB_WORKER_THREAD_NAME             = "dbWorkerThread"
    const val DATE_FORMAT                       = "yyyy-MM-dd'T'HH:mm:ss"

}

object RequestsPermissionsCodes {
    const val REQUEST_READ_EXTERNAL_STORAGE     = 1000 + 10
    const val REQUEST_CAMERA                    = 1000 + 20
    const val REQUEST_GPS                       = 1000 + 30
    const val REQUEST_LOCATION                  = 1000 + 40
}

