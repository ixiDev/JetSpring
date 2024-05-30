@file:Suppress(
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused"
)

package ixidev.jetspring.base

import kotlinx.coroutines.*

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/


@Suppress("unused")
abstract class BaseViewModel {

    private val viewModelScope: CoroutineScope = CoroutineScope(Job())

    protected fun launchOnMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.Main) { block() }
    }

    protected fun launchOnIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) { block() }
    }

    protected fun onClear() {
        if (viewModelScope.isActive) {
            viewModelScope.cancel()
        }
    }
}