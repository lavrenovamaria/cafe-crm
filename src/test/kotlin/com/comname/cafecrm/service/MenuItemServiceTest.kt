package com.comname.cafecrm.service

import com.comname.cafecrm.BaseServiceTest
import com.comname.cafecrm.domain.converter.toModel
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.menuItem
import com.comname.cafecrm.domain.menuItemEntity
import com.comname.cafecrm.repository.MenuItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

class MenuItemServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var menuItemService: MenuItemService

    @MockBean
    private lateinit var menuItemRepository: MenuItemRepository

    //TODO: not found cases
    @Test
    fun `create - happy path`() {
        val id = 5L

        whenever(menuItemRepository.save(any<MenuItemEntity>())).thenReturn(MenuItemEntity().apply { this.id = id })

        val result = menuItemService.create()

        assertEquals(id, result)
        verify(menuItemRepository).save(any<MenuItemEntity>())
    }

    @Test
    fun `get - happy path`() {
        val id = 5L
        val persistedEntity = menuItemEntity().apply { this.id = id }

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.of(persistedEntity))

        val result = menuItemService.get(id)

        assertEquals(persistedEntity.toModel(), result)
        verify(menuItemRepository).findById(id)
    }

    @Test
    fun `update - happy path`() {
        val id = 3L
        val persistedMenuItem = menuItemEntity(id = id)
        val newMenuItem = menuItem(
            id = id,
            name = "newName",
            category = "newCategory",
            ingredients = "newIngredients",
            isAvailable = false,
            weight = 1223,
            price = 4124,
            image = "newImage"
        )

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.of(persistedMenuItem))

        val result = menuItemService.update(id, newMenuItem.copy(id = null))

        assertEquals(newMenuItem, result)
        verify(menuItemRepository).findById(id)
    }

    @Test
    fun `delete - happy path`() {
        val id = 5L

        menuItemService.delete(id)

        verify(menuItemRepository).deleteById(id)
    }

}