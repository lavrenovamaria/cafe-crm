package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.dto.UserDto
import com.comname.cafecrm.domain.dto.toDto
import com.comname.cafecrm.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${paths.api.prefix}/users")
class UserController(
    private val userService: UserService)  {

    fun create() = userService.create().toDto()

    fun get(id: Long) = userService.get(id).toDto()

    fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    fun update(id: Long, dto: UserDto): UserDto {
        TODO("Not yet implemented")
    }

}