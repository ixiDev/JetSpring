package ixidev.jetspring.data


import ixidev.jetspring.data.entities.Role
import ixidev.jetspring.data.entities.User
import ixidev.jetspring.data.services.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataGenerator {
    @Bean
    fun loadData(passwordEncoder: PasswordEncoder, userRepository: UserRepository): CommandLineRunner {
        return CommandLineRunner {
            val logger = LoggerFactory.getLogger(javaClass)
            if (userRepository.count() != 0L) {
                logger.info("Using existing database")
                return@CommandLineRunner
            }
            logger.info("Generating Admin Data")
            val admin = User()
            admin.name = "Admin"
            admin.username = "admin@jetspring.com"
            admin.hashedPassword = passwordEncoder.encode("admin")
            admin.roles = mutableSetOf(Role.USER, Role.ADMIN)
            userRepository.save(admin)
            logger.info("Admin credential info generated")
        }
    }
}