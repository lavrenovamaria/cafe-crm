package com.comname.cafecrm.service

import com.comname.cafecrm.domain.converter.toModel
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.model.MenuItem
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.repository.MenuItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MenuItemService(
    private val menuItemRepository: MenuItemRepository
) {

    fun create() = menuItemRepository.save(MenuItemEntity()).id
        ?: throw EntityNotPersistedException(MenuItemEntity::class)

    fun get(id: Long) = menuItemRepository.findByIdOrNull(id)?.toModel()
        ?: throw EntityNotFoundException(MenuItemEntity::class, id)

    fun getAll() = menuItemRepository.getAllBy().map { it.toModel() }

    fun update(id: Long, menuItem: MenuItem) =
        menuItemRepository
            .findByIdOrNull(id)
            ?.merge(menuItem)?.toModel() ?: throw EntityNotFoundException(MenuItemEntity::class, id)

    fun delete(id: Long) = menuItemRepository.findByIdOrNull(id)
        .let { entity ->
            entity ?: throw EntityNotFoundException(MenuItemEntity::class, id)
            menuItemRepository.deleteById(id)
        }

}