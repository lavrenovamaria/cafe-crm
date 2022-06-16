package com.comname.cafecrm.domain.dto

import com.comname.cafecrm.domain.model.CartItem

data class CartItemDto(
    override var id: Long? = null,
    var menuItem: MenuItemDto? = null,
    var quantity: Long? = null
) : BaseDto<CartItem> {

    override fun toModel() =
        CartItem(
            id = id,
            quantity = quantity,
            menuItem = menuItem?.toModel()
        )

}