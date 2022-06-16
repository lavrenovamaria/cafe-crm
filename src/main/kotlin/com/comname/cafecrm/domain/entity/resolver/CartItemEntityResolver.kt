package com.comname.cafecrm.domain.entity.resolver

import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.model.CartItem
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.repository.MenuItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(propagation = Propagation.MANDATORY)
class CartItemEntityResolver(
    private val menuItemEntityResolver: MenuItemEntityResolver,
    private val menuItemRepository: MenuItemRepository
) : Resolver<CartItemEntity, CartItem>{

    override fun toEntity(model: CartItem) =
        with(model) {
            CartItemEntity(
                quantity = quantity,
                menuItem = menuItem?.let { menuItemEntityResolver.toEntity(it) }
            )
        }

    override fun toModel(entity: CartItemEntity) =
        with(entity) {
            CartItem(
                id = id,
                quantity = quantity,
                menuItem = menuItem?.let { menuItemEntityResolver.toModel(it) }
            )
        }

    override fun merge(old: CartItemEntity, new: CartItem) =
        old.apply {
            val newMenuItem = new.menuItem?.id?.let { menuItemId ->
                menuItemRepository.findByIdOrNull(menuItemId) ?: throw EntityNotFoundException(MenuItemEntity::class, menuItemId)
            }
            quantity = new.quantity ?: old.quantity
            menuItem = newMenuItem ?: old.menuItem
        }

}