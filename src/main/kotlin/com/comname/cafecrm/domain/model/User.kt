package com.comname.cafecrm.domain.model

import com.comname.cafecrm.domain.dto.CartItemDto

data class User(
    val id: Long?,
) : BaseModel {

    fun toDto() =
        CartItemDto(
            id = id,
        )
}