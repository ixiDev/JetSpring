package ixidev.jetspring.data.services

import ixidev.jetspring.data.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsername(username: String?): User?
}