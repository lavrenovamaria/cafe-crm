package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.dto.CartItemDto
import com.comname.cafecrm.domain.dto.toDto
import com.comname.cafecrm.service.CartItemService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${paths.api.prefix}/cart-items")
class CartItemController(
    private val cartItemService: CartItemService
) : BaseCrudController<CartItemDto> {

    override fun create() = cartItemService.create().toDto()

    override fun get(id: Long) = cartItemService.get(id).toDto()

    override fun update(id: Long, dto: CartItemDto) =
        cartItemService.update(id, dto.toModel()).toDto()

    override fun delete(id: Long) = cartItemService.delete(id)
}