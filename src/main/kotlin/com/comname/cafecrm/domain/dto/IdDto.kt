package com.comname.cafecrm.domain.dto

data class IdDto(
    var id: Long? = null
)

fun Long.toDto() = IdDto(this)