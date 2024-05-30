package ixidev.jetspring.security


import ixidev.jetspring.data.entities.Role
import ixidev.jetspring.data.entities.User
import ixidev.jetspring.data.services.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
//@EnableGlobalAuthentication
class UserDetailsServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        return if (user == null) {
            throw UsernameNotFoundException("No user present with username: $username")
        } else {
            org.springframework.security.core.userdetails.User(
                user.username, user.hashedPassword,
                getAuthorities(user)
            )
        }
    }

    companion object {
        private fun getAuthorities(user: User): List<GrantedAuthority> {
            return user.roles.stream().map { role: Role ->
                SimpleGrantedAuthority(
                    "ROLE_$role"
                )
            }.collect(Collectors.toList())
        }
    }
}