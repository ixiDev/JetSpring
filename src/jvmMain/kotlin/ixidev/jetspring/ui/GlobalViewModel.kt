package ixidev.jetspring.ui

import ixidev.jetspring.base.BaseViewModel
import ixidev.jetspring.security.AuthenticatedUser
import org.springframework.stereotype.Component

/***
 * Created by ixiDev on 20/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/

@Component
class GlobalViewModel(
    private val user: AuthenticatedUser
) : BaseViewModel() {

    fun getUserName(): String {
        val userOptional = user.get()
        return if (userOptional.isPresent)
            userOptional.get().name ?: "Unknown"
        else "Unknown"
    }
}