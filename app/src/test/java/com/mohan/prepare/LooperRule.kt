package com.mohan.prepare

import com.mohan.prepare.testingutil.ContextProvider
import com.mohan.prepare.testingutil.TaskExecutor
import kotlinx.coroutines.experimental.DefaultDispatcher
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.experimental.CoroutineContext

class LooperRule : TestWatcher() {




    override fun starting(description: Description?) {
        super.starting(description)

        val contextProvider=object : ContextProvider {
            override fun UI(): CoroutineContext {
                return DefaultDispatcher
            }
        }

        TaskExecutor.instance?.setDelegate(contextProvider)


    }

    override fun finished(description: Description?) {
        super.finished(description)
        TaskExecutor.instance?.setDelegate(null)

    }
}
