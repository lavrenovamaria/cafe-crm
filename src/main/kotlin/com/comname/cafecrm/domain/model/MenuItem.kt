package com.comname.cafecrm.domain.model

import com.comname.cafecrm.domain.dto.MenuItemDto

data class MenuItem(
    val id: Long?,
    val name: String?,
    val category: String?,
    val ingredients: String?,
    val isAvailable: Boolean?,
    val weight: Long?,
    val price: Long?,
    val image: String?
) : BaseModel {

    fun toDto() =
        MenuItemDto(
            id = id,
            name = name,
            category = category,
            ingredients = ingredients,
            isAvailable = isAvailable,
            weight = weight,
            price = price,
            image = image
        )

}