package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.dto.CartItemDto
import com.comname.cafecrm.domain.dto.toDto
import com.comname.cafecrm.service.CartItemService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${paths.api.prefix}/users")
class UserController(
    private val userService: UserService
) : BaseCrudController<UserDto> {

    override fun create() = userService.create().toDto()

    override fun get(id: Long) = userService.get(id).toDto()

    override fun update(id: Long, dto: CartItemDto) =
        userService.update(id, dto.toModel()).toDto()

    override fun delete(id: Long) = userService.delete(id)
}