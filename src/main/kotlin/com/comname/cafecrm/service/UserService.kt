package com.comname.cafecrm.service

import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.domain.entity.UserEntity
import com.comname.cafecrm.domain.entity.resolver.CartItemEntityResolver
import com.comname.cafecrm.domain.entity.resolver.UserEntityResolver
import com.comname.cafecrm.domain.model.CartItem
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.repository.CartItemRepository
import com.comname.cafecrm.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val UserRepository: UserRepository,
    private val UserEntityResolver: UserEntityResolver
) {

    fun create() = UserRepository.save(UserEntity()).id
        ?: throw EntityNotPersistedException(UserEntity::class)

    fun get(id: Long) = UserEntityResolver.toModel(
        UserRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(UserEntity::class, id))

}