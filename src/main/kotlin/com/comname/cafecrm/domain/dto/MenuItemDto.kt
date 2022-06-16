package com.comname.cafecrm.domain.dto

import com.comname.cafecrm.domain.model.MenuItem

data class MenuItemDto(
    override var id: Long? = null,
    var name: String? = null,
    var category: String? = null,
    var ingredients: String? = null,
    var isAvailable: Boolean? = null,
    var weight: Long? = null,
    var price: Long? = null,
    var image: String? = null
) : BaseDto<MenuItem> {

    override fun toModel() =
        MenuItem(
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