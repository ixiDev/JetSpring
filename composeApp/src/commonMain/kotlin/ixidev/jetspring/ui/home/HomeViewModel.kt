package ixidev.jetspring.ui.home

import ixidev.jetspring.base.BaseViewModel
import ixidev.jetspring.data.DataService
import kotlinx.coroutines.flow.MutableStateFlow
import org.springframework.stereotype.Component

/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/

@Component
class HomeViewModel constructor(
    private val dataService: DataService
) : BaseViewModel() {

    val clientsCount: MutableStateFlow<Int> = MutableStateFlow(0)

    fun getCount() {
        launchOnIO {
            clientsCount.emit(
                dataService.getCount()
            )
        }
    }
}