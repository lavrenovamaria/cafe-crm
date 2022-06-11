package com.comname.cafecrm.service

import com.comname.cafecrm.domain.converter.toModel
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.model.MenuItem
import com.comname.cafecrm.repository.MenuItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MenuItemService(
    private val menuItemRepository: MenuItemRepository
) {

    //TODO: add not found exceptions
    fun create() = menuItemRepository.save(MenuItemEntity()).id ?: error("Error while persisting MenuItem")

    fun get(id: Long) = menuItemRepository.findByIdOrNull(id)?.toModel() ?: error("MenuItem with id=$id not found")

    fun update(id: Long, menuItem: MenuItem) =
        menuItemRepository
            .findByIdOrNull(id)
            ?.merge(menuItem)?.toModel() ?: error("MenuItem with id=$id not found")

    fun delete(id: Long) = menuItemRepository.deleteById(id)

}