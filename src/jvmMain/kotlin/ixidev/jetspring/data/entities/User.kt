package ixidev.jetspring.data.entities

import javax.persistence.*

@Entity
@Table(name = "application_user")
class User(
    var username: String? = null,
    var name: String? = null,
    var hashedPassword: String? = null,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<Role> = mutableSetOf()
) : AbstractEntity()