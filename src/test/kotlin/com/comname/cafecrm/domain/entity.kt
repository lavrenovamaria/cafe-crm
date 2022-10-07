package com.comname.cafecrm.domain

import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.entity.UserEntity

fun menuItemEntity(
    id: Long = 127L,
    name: String = "Meatballs",
    category: String = "Main",
    ingredients: String = "Meat, salt",
    isAvailable: Boolean = true,
    weight: Long = 350,
    price: Long = 69900,
    image: String = "https://picsum.photos/id/12/300/300"
) = MenuItemEntity(
    name = name,
    category = category,
    ingredients = ingredients,
    isAvailable = isAvailable,
    weight = weight,
    price = price,
    image = image
).apply {
    this.id = id
}

fun cartItemEntity(
    id: Long? = 110L,
    menuItem: MenuItemEntity? = menuItemEntity(),
    quantity: Long? = 1
) = CartItemEntity(
    menuItem = menuItem,
    quantity = quantity
).apply {
    this.id = id
}

fun userEntity(
    id: Long = 1L,
    user: Long = 1L,
) = UserEntity(
    user = user,
).apply {
    this.id = id
}