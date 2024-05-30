package ixidev.jetspring.security

import kotlinx.coroutines.flow.MutableStateFlow
import org.springframework.stereotype.Component

/***
 * Created by ixiDev on 20/08/2022
 * GitHub : https://www.github.com/ixiDev
 **/

@Component
class UserAuthSate {
    var auth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var error = MutableStateFlow<String?>(null)
}