package com.mohan.prepare.testingutil

import kotlin.coroutines.experimental.CoroutineContext


class TaskExecutor : ContextProvider {

    private var delegate: ContextProvider? = null

    internal var defaultProvider: ContextProvider = ContextProvider { kotlinx.coroutines.experimental.android.UI }

    init {
        setDelegate(defaultProvider)
    }


    fun setDelegate(contextprovider: ContextProvider?) {
        if (contextprovider == null) {
            delegate = defaultProvider
        } else {
            delegate = contextprovider
        }
    }


    override fun UI(): CoroutineContext {
        return delegate!!.UI()
    }

    companion object {
        private  var taskExecutor: TaskExecutor?=null


        val instance: TaskExecutor
            get() {
                return taskExecutor?:TaskExecutor().also {
                    taskExecutor=it
                }
            }
    }
}
