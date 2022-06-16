package com.comname.cafecrm.service

import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.entity.resolver.MenuItemEntityResolver
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
    private val menuItemRepository: MenuItemRepository,
    private val menuItemEntityResolver: MenuItemEntityResolver
) {

    fun create() = menuItemRepository.save(MenuItemEntity()).id
        ?: throw EntityNotPersistedException(MenuItemEntity::class)

    fun get(id: Long) = menuItemEntityResolver.toModel(
        menuItemRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(MenuItemEntity::class, id))

    fun getAll() = menuItemRepository.getAllBy().map { menuItemEntityResolver.toModel(it) }

    fun update(id: Long, menuItem: MenuItem) =
        menuItemEntityResolver.merge(
            menuItemRepository.findByIdOrNull(id) ?: throw EntityNotFoundException(MenuItemEntity::class, id),
            menuItem
        ).let { menuItemEntityResolver.toModel(it) }

    fun delete(id: Long) = menuItemRepository.findByIdOrNull(id)
        .let { entity ->
            entity ?: throw EntityNotFoundException(MenuItemEntity::class, id)
            menuItemRepository.deleteById(id)
        }

}