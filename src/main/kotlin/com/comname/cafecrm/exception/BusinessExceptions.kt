package com.comname.cafecrm.exception

import com.comname.cafecrm.domain.entity.BaseEntity
import org.springframework.http.HttpStatus
import kotlin.reflect.KClass

abstract class BusinessException(
        val code: ErrorCode,
        val status: HttpStatus,
        message: String
) : RuntimeException(message)

class EntityNotFoundException(
        entity: KClass<out BaseEntity<out Any>>,
        id: Long
) : BusinessException(
        ErrorCode.ENTITY_NOT_FOUND,
        HttpStatus.NOT_FOUND,
        "${entity.simpleName} with id = $id is not found"
)

class EntityNotPersistedException(
        entity: KClass<out BaseEntity<out Any>>
) : BusinessException(
        ErrorCode.ENTITY_NOT_PERSISTED,
        HttpStatus.INTERNAL_SERVER_ERROR,
        "${entity.simpleName} is not persisted"
)

enum class ErrorCode {
        ENTITY_NOT_FOUND,
        ENTITY_NOT_PERSISTED
}