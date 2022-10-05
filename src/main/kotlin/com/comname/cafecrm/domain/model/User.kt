package com.comname.cafecrm.domain.model

import com.comname.cafecrm.domain.dto.UserDto
import com.comname.cafecrm.domain.dto.toDto

data class User(
    val id: Long?
) : BaseModel {

    fun toDto() =
        UserDto(
            id = id
        )
}