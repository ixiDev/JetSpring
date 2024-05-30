package ixidev.jetspring.data.entities


import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Type
import java.util.*

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue
//    @Type("uuid-char")
    var id: UUID? = null
    override fun hashCode(): Int {
        return if (id != null) {
            id.hashCode()
        } else super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractEntity) {
            return false // null or other class
        }
        return if (id != null) {
            id == other.id
        } else super.equals(other)
    }
}