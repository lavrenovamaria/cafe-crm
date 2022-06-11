package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.converter.toDto
import com.comname.cafecrm.domain.converter.toModel
import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.service.MenuItemService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("menu-items")
class MenuItemController(
    private val menuItemService: MenuItemService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create MenuItem")
    fun create() = menuItemService.create().toDto()

    @GetMapping("/{id}")
    @Operation(summary = "Get MenuItem by ID")
    fun get(@PathVariable("id") id: Long) = menuItemService.get(id).toDto()

    @GetMapping
    @Operation(summary = "Get all persisted MenuItems")
    fun getMenu() = menuItemService.getAll().map { it.toDto() }

    @PatchMapping("/{id}")
    @Operation(summary = "Update MenuItem")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody menuItemDto: MenuItemDto
    ) = menuItemService.update(id, menuItemDto.toModel()).toDto()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete MenuItem by ID")
    fun delete(@PathVariable("id") id: Long) = menuItemService.delete(id)
}