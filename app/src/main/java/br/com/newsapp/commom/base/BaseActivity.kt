package br.com.newsapp.commom.base

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.view.isVisible
import br.com.newsapp.R
import br.com.newsapp.util.ConnectionMonitorLiveData
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), IBaseContract {

    @Inject
    lateinit var connectionLiveData: ConnectionMonitorLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNetObserver()
    }

    private fun initNetObserver() {
        connectionLiveData.observe(this, androidx.lifecycle.Observer { isConnected ->
            isConnected?.let {
                //TODO: notifiy logic for user is offline
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            recreate()
        }
    }

    fun onLoading(show: Boolean) {
        try {
            val shimmer = findViewById<ShimmerFrameLayout>(R.id.shimmerLayout)
            if (shimmer != null && !isFinishing) {
                shimmer.isVisible = show

                if (show)
                    shimmer.startShimmerAnimation()
                else
                    shimmer.stopShimmerAnimation()
            }
        } catch (e: Exception) {
            Timber.e(e)
            e.printStackTrace()
        }
    }
}