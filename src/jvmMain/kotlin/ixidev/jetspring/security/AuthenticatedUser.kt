package ixidev.jetspring.security



import ixidev.jetspring.data.entities.User
import ixidev.jetspring.data.services.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuthenticatedUser @Autowired constructor(
    private val userRepository: UserRepository,
    private val authSate: UserAuthSate
) {

    private val authentication: Optional<Authentication>
        get() {
            val context = SecurityContextHolder.getContext()
            val auth = context.authentication
            return Optional.ofNullable(auth)
                .filter { authentication: Authentication? -> authentication !is AnonymousAuthenticationToken }
        }

    fun get(): Optional<User> {
        return authentication.map { authentication: Authentication ->
            userRepository.findByUsername(authentication.name)
        }
    }

    fun logout() {
        val context = SecurityContextHolder.getContext()
        SecurityContextHolder.clearContext()
        context.authentication = null
        authSate.auth.tryEmit(false)
    }
}