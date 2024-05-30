package ixidev.jetspring.ui.auth

import ixidev.jetspring.base.BaseViewModel
import ixidev.jetspring.security.UserAuthSate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


/***
 * Created by ixiDev on 19/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/
@Component
class LoginViewModel @Autowired constructor(
    private val authManager: AuthenticationManager,
    private val authSate: UserAuthSate,
) : BaseViewModel() {
    val errorState = authSate.error

    fun login(user: String?, pass: String?) {
        launchOnIO {
            try {
                val authReq = UsernamePasswordAuthenticationToken(user, pass)
                val auth: Authentication = authManager.authenticate(authReq)
                val sc = SecurityContextHolder.getContext()
                sc.authentication = auth
                authSate.auth.emit(auth.isAuthenticated)
            } catch (e: Exception) {
                authSate.error.emit(e.message)
            }
        }
    }

    fun emitError(error: String?) {
        launchOnIO {
            authSate.error.emit(null)
        }
    }
}