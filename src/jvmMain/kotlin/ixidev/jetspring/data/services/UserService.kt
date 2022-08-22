package ixidev.jetspring.data.services

import ixidev.jetspring.data.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(private val repository: UserRepository) {
    operator fun get(id: UUID): Optional<User> {
        return repository.findById(id)
    }

    fun update(entity: User): User {
        return repository.save(entity)
    }

    fun delete(id: UUID) {
        repository.deleteById(id)
    }

    fun list(pageable: Pageable?): Page<User> {
        return repository.findAll(pageable!!)
    }

    fun count(): Int {
        return repository.count().toInt()
    }
}