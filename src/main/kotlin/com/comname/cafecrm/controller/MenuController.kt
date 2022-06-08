package com.comname.cafecrm.controller

import com.comname.cafecrm.domain.converter.toDto
import com.comname.cafecrm.service.MenuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("menu")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping
    fun getMenu() = menuService.getMenu().map { it.toDto() }

}