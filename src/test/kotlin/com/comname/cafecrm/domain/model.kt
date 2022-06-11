package com.comname.cafecrm.domain

import com.comname.cafecrm.domain.model.MenuItem

fun menuItem(
    id: Long? = 127L,
    name: String? = "Meatballs",
    category: String? = "Main",
    ingredients: String? = "Meat, salt",
    isAvailable: Boolean? = true,
    weight: Long? = 350,
    price: Long? = 69900,
    image: String? = "https://picsum.photos/id/12/300/300"
) = MenuItem(
    id = id,
    name = name,
    category = category,
    ingredients = ingredients,
    isAvailable = isAvailable,
    weight = weight,
    price = price,
    image = image
)