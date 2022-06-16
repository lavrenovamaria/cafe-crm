package com.comname.cafecrm.domain.model

import com.comname.cafecrm.domain.dto.CartItemDto

data class CartItem(
    val id: Long?,
    val menuItem: MenuItem?,
    val quantity: Long?
) : BaseModel {

    fun toDto() =
        CartItemDto(
            id = id,
            quantity = quantity,
            menuItem = menuItem?.toDto()
        )

}