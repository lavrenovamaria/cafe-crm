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
class CartItemService(
    private val cartItemRepository: CartItemRepository,
    private val cartItemEntityResolver: CartItemEntityResolver
) {

    fun create() = cartItemRepository.save(CartItemEntity()).id
        ?: throw EntityNotPersistedException(CartItemEntity::class)

    fun get(id: Long) = cartItemEntityResolver.toModel(
        cartItemRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(CartItemEntity::class, id))

    fun update(id: Long, cartItem: CartItem) =
        cartItemEntityResolver
            .merge(
                cartItemRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(CartItemEntity::class, id),
                cartItem
            ).let { cartItemEntityResolver.toModel(it) }

    fun delete(id: Long) = cartItemRepository.findByIdOrNull(id)
        .let { entity ->
            entity ?: throw EntityNotFoundException(CartItemEntity::class, id)
            cartItemRepository.deleteById(id)
        }

}