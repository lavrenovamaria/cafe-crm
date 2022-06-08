package com.comname.cafecrm.domain.converter

import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.model.MenuItem

fun MenuItemDto.toModel() =
    MenuItem(
        id = id ?: error("id must be defined"),
        name = name ?: error("name must be defined"),
        category = category ?: error("category must be defined"),
        ingredients = ingredients ?: error("ingredients must be defined"),
        isAvailable = isAvailable ?: error("isAvailable must be defined"),
        weight = weight ?: error("weight must be defined"),
        price = price ?: error("price must be defined"),
        image = image ?: error("image must be defined")
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