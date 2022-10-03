package com.comname.cafecrm.service

import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.domain.entity.resolver.CartItemEntityResolver
import com.comname.cafecrm.domain.model.CartItem
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.repository.CartItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val UserRepository: CartItemRepository,
    private val UserEntityResolver: CartItemEntityResolver
) {

    fun create() = UserRepository.save(CartItemEntity()).id
        ?: throw EntityNotPersistedException(CartItemEntity::class)

    fun get(id: Long) = UserEntityResolver.toModel(
        UserRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(CartItemEntity::class, id))

    fun update(id: Long, cartItem: CartItem) =
        UserEntityResolver
            .merge(
                UserRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(CartItemEntity::class, id),
                cartItem
            ).let { UserEntityResolver.toModel(it) }

    fun delete(id: Long) = UserRepository.findByIdOrNull(id)
        .let { entity ->
            entity ?: throw EntityNotFoundException(CartItemEntity::class, id)
            UserRepository.deleteById(id)
        }

}