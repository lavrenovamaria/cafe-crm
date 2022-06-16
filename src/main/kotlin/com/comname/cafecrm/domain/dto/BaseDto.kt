package com.comname.cafecrm.domain.dto

interface BaseDto<Model> {
    var id: Long?

    fun toModel(): Model

}