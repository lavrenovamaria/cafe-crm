package com.comname.cafecrm.domain.model

data class MenuItem(
    val id: Long?,
    val name: String?,
    val category: String?,
    val ingredients: String?,
    val isAvailable: Boolean?,
    val weight: Long?,
    val price: Long?,
    val image: String?
)