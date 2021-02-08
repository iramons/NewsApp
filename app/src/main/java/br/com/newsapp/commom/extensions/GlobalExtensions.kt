package br.com.newsapp.commom.extensions

import android.os.Build
import android.os.Handler
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Delay to execute a task
 */
fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

/**
 * Check Version API Android
 */
fun isAtLeastLollipop(): Boolean {
    return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
}
fun isAtLeastMarshmallow(): Boolean {
    return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
}
fun isAtLeastNougat(): Boolean {
    return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
}

/**
 * Cancel the Job if it's active.
 */
fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancelChildren()
        cancel()
    }
}

/**
 * Cancel the CoroutineContext if it's active.
 */
fun CoroutineContext?.cancelIfActive() {
    if (this?.isActive == true) {
        cancelChildren()
        cancel()
    }
}
/**
 * Cancel the CoroutineContext if it's active.
 */
fun CoroutineScope?.cancelIfActive() {
    this?.coroutineContext.cancelIfActive()
}
