package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.domain.dto.toDto
import com.comname.cafecrm.service.MenuItemService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${paths.api.prefix}/menu-items")
class MenuItemController(
    private val menuItemService: MenuItemService
) : BaseCrudController<MenuItemDto> {

    @GetMapping
    @Operation(summary = "Get all persisted MenuItems")
    fun getMenu() = menuItemService.getAll().map { it.toDto() }

    override fun create() = menuItemService.create().toDto()

    override fun get(id: Long) = menuItemService.get(id).toDto()

    override fun update(id: Long, dto: MenuItemDto) = menuItemService.update(id, dto.toModel()).toDto()

    override fun delete(id: Long) = menuItemService.delete(id)
}