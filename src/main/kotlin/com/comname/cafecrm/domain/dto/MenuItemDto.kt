package com.comname.cafecrm.domain.dto

data class MenuItemDto(
    override var id: Long? = null,
    var name: String? = null,
    var category: String? = null,
    var ingredients: String? = null,
    var isAvailable: Boolean? = null,
    var weight: Long? = null,
    var price: Long? = null,
    var image: String? = null
) : BaseDto