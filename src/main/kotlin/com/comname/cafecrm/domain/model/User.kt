package com.comname.cafecrm.domain.model

import com.comname.cafecrm.domain.dto.UserDto

data class User(
    val id: Long?
) : BaseModel {

    fun toDto() =
        UserDto(
            id = id
        )
}