package com.comname.cafecrm.domain.entity

import org.springframework.data.util.ProxyUtils
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<T> {

    abstract var id: T?

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode() = 25

    override fun toString() = "${this.javaClass.simpleName}(id=$id)"
}