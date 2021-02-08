package br.com.newsapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.com.newsapp.R
import br.com.newsapp.commom.extensions.postDelayed
import br.com.newsapp.ui.login.LoginActivity
import br.com.newsapp.ui.news.NewsActivity
import br.com.newsapp.util.SessionManager

class SplashScreenActivity: AppCompatActivity() {

    private lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val fadeIn = AnimationUtils.loadAnimation(
                        this,
                        R.anim.fade_in)

        logo = findViewById(R.id.imageLogo)
        logo.startAnimation(fadeIn)
        startTransition()
    }

    private fun startTransition() {
        val intent: Intent = if (SessionManager.isUserLoggedIn())
                    Intent(this@SplashScreenActivity, NewsActivity::class.java)
                 else
                    Intent(this@SplashScreenActivity, LoginActivity::class.java)
        putFlags(intent)
        postDelayed(1000) {
            startActivity(intent)
        }
    }

    private fun putFlags(intent: Intent) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

}