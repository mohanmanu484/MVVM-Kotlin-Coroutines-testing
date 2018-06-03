package com.mohan.prepare.extensions

import com.mohan.prepare.testingutil.TaskExecutor
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.experimental.CoroutineContext


suspend fun <T> Call<T>.await(): T {

    return suspendCancellableCoroutine { continuation ->


        continuation.invokeOnCompletion {
            if (continuation.isCancelled) {
                cancel()
            }
        }

        val callBack = object : Callback<T> {

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                continuation.resume(response.body()!!)
            }
        }
        enqueue(callBack)
    }
}


fun UI(): CoroutineContext {
    return TaskExecutor.instance.UI()
}

infix fun String.legnthOf(length: Int): String {
    return if (this.length <= length) {
        this
    } else {
        this.substring(0, length - 1)
    }
}




