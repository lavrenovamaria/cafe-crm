package com.comname.cafecrm.service

import com.comname.cafecrm.domain.converter.toModel
import com.comname.cafecrm.repository.MenuItemRepository
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuItemRepository: MenuItemRepository
) {

    fun getMenu() = menuItemRepository.getAll().map { it.toModel() }

}