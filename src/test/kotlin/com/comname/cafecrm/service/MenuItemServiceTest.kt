package com.comname.cafecrm.service

import com.comname.cafecrm.BaseServiceTest
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.entity.resolver.MenuItemEntityResolver
import com.comname.cafecrm.domain.menuItem
import com.comname.cafecrm.domain.menuItemEntity
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.repository.MenuItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
class MenuItemServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var menuItemService: MenuItemService

    @Autowired
    private lateinit var menuItemEntityResolver: MenuItemEntityResolver

    @MockBean
    private lateinit var menuItemRepository: MenuItemRepository

    @Test
    fun `create - happy path`() {
        val id = 5L

        whenever(menuItemRepository.save(any<MenuItemEntity>())).thenReturn(MenuItemEntity().apply { this.id = id })

        val result = menuItemService.create()

        assertEquals(id, result)
        verify(menuItemRepository).save(any<MenuItemEntity>())
    }

    @Test
    fun `create - fail - not persisted`() {
        whenever(menuItemRepository.save(any<MenuItemEntity>())).thenReturn(MenuItemEntity().apply { this.id = null })

        assertThrows<EntityNotPersistedException> { menuItemService.create() }

        verify(menuItemRepository).save(any<MenuItemEntity>())
    }

    @Test
    fun `get - happy path`() {
        val id = 5L
        val persistedEntity = menuItemEntity().apply { this.id = id }

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.of(persistedEntity))

        val result = menuItemService.get(id)

        assertEquals(persistedEntity.let { menuItemEntityResolver.toModel(it) }, result)
        verify(menuItemRepository).findById(id)
    }

    @Test
    fun `get - fail - not found`() {
        val id = 5L

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { menuItemService.get(id) }

        verify(menuItemRepository).findById(id)
    }

    @Test
    fun `getAll - happy path`() {
        val menuItemList = listOf(menuItemEntity(), menuItemEntity())

        whenever(menuItemRepository.getAllBy()).thenReturn(menuItemList)

        val result = menuItemService.getAll()

        assertEquals(menuItemList.map { it.let { menuItemEntityResolver.toModel(it) } }, result)
    }

    @Test
    fun `update - happy path - update all fields`() {
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
    fun `update - happy path - update a few fields`() {
        val id = 3L
        val persistedMenuItem = menuItemEntity(id = id)
        val newMenuItem = menuItem(
            id = id,
            name = null,
            category = "newCategory",
            ingredients = "newIngredients",
            isAvailable = null,
            weight = 1223,
            price = null,
            image = "newImage"
        )

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.of(persistedMenuItem))

        val result = menuItemService.update(id, newMenuItem.copy(id = null))

        with(result) {
            assertEquals(persistedMenuItem.id, result.id)
            assertEquals(persistedMenuItem.name, name)
            assertEquals(newMenuItem.category, category)
            assertEquals(newMenuItem.ingredients, ingredients)
            assertEquals(persistedMenuItem.isAvailable, isAvailable)
            assertEquals(newMenuItem.weight, weight)
            assertEquals(persistedMenuItem.price, price)
            assertEquals(newMenuItem.image, image)
        }
        verify(menuItemRepository).findById(id)
    }

    @Test
    fun `update - fail - not found`() {
        val id = 3L
        val newMenuItem = menuItem(id = id)

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { menuItemService.update(id, newMenuItem.copy(id = null)) }

        verify(menuItemRepository).findById(id)
    }

    @Test
    fun `delete - happy path`() {
        val id = 5L
        val persistedMenuItem = menuItemEntity(id = id)

        whenever(menuItemRepository.findById(id)).thenReturn(Optional.of(persistedMenuItem))

        menuItemService.delete(id)

        verify(menuItemRepository).findById(id)
        verify(menuItemRepository).deleteById(id)
    }

    @Test
    fun `delete - fail - not found`() {
        val id = 5L
        whenever(menuItemRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { menuItemService.delete(id) }

        verify(menuItemRepository).findById(id)
        verify(menuItemRepository, times(0)).deleteById(id)
    }

}