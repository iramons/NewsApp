package br.com.newsapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import br.com.newsapp.commom.extensions.SharedPreferencesExtensions.put
import br.com.newsapp.commom.extensions.SharedPreferencesExtensions.remove
import br.com.newsapp.data.model.APITokenResult
import br.com.newsapp.util.Preferences.ACCESS_TOKEN
import br.com.newsapp.util.Preferences.LOGIN_EMAIL_TEMP
import br.com.newsapp.util.Preferences.LOGIN_PASSWORD_TEMP
import br.com.newsapp.util.Preferences.LOGIN_STATUS
import br.com.newsapp.util.Preferences.SHARED_PREFERENCES_ENCRYPT_NAME

class SessionManager {

    companion object {

        private var initialized: Boolean = false

        private lateinit var prefs: SharedPreferences

        fun init(context: Context) {
            if (!initialized) {
                synchronized(SessionManager::class.java) {
                    val masterKey: MasterKey = MasterKey.Builder(
                        context,
                        MasterKey.DEFAULT_MASTER_KEY_ALIAS
                    ).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

                    this.prefs = EncryptedSharedPreferences
                        .create(
                            context,
                            SHARED_PREFERENCES_ENCRYPT_NAME,
                            masterKey,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                        )

                    initialized = true
                }
            }
        }

        fun isUserLoggedIn(): Boolean = prefs.getBoolean(LOGIN_STATUS, false)

        private fun removeUserLoggedIn() {
            this.prefs.remove(LOGIN_STATUS)
        }

        private fun saveUserLoggedIn(value: Boolean) {
            this.prefs.put(LOGIN_STATUS, value)
        }

        private fun saveLogin(login: String?) {
            this.prefs.put(LOGIN_EMAIL_TEMP, login)
        }

        private fun savePass(pass: String?) {
            this.prefs.put(LOGIN_PASSWORD_TEMP, pass)
        }

        private fun saveAuthToken(token: String?) {
            this.prefs.put(ACCESS_TOKEN, token)
        }

        fun fetchAuthToken(): String? {
            return prefs.getString(ACCESS_TOKEN, null)
        }

        private fun saveToken(apiToken: APITokenResult) {
            saveAuthToken(apiToken.token)
        }

        fun onLogin(user: String, password: String, apiToken: APITokenResult) {
            saveToken(apiToken)
            saveLogin(user)
            savePass(password)
            saveUserLoggedIn(true)
        }

    }
}