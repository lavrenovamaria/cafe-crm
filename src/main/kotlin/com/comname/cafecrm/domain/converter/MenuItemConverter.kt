package com.comname.cafecrm.domain.converter

import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.model.MenuItem

fun MenuItemDto.toModel() =
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

fun MenuItemEntity.toModel() =
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

fun MenuItem.toDto() =
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

fun MenuItem.toEntity() =
    MenuItemEntity(
        name = name,
        category = category,
        ingredients = ingredients,
        isAvailable = isAvailable,
        weight = weight,
        price = price,
        image = image
    )
