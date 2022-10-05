package com.comname.cafecrm.domain.dto

import com.comname.cafecrm.domain.model.User

data class UserDto(
    override var id: Long? = null,
    var User: UserDto? = null
) : BaseDto<User> {

    override fun toModel() =
        User(
            id = id
        )
}