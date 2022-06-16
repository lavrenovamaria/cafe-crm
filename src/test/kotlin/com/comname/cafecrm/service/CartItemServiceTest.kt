package com.comname.cafecrm.service

import com.comname.cafecrm.BaseServiceTest
import com.comname.cafecrm.domain.cartItem
import com.comname.cafecrm.domain.cartItemEntity
import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.domain.entity.resolver.CartItemEntityResolver
import com.comname.cafecrm.domain.menuItem
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.repository.CartItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

class CartItemServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var cartItemService: CartItemService

    @Autowired
    private lateinit var cartItemEntityResolver: CartItemEntityResolver

    @MockBean
    private lateinit var cartItemRepository: CartItemRepository

    @Test
    fun `create - happy path`() {
        val id = 5L

        whenever(cartItemRepository.save(any<CartItemEntity>())).thenReturn(CartItemEntity().apply { this.id = id })

        val result = cartItemService.create()

        assertEquals(id, result)
        verify(cartItemRepository).save(any<CartItemEntity>())
    }

    @Test
    fun `create - fail - not persisted`() {
        whenever(cartItemRepository.save(any<CartItemEntity>())).thenReturn(CartItemEntity().apply { this.id = null })

        assertThrows<EntityNotPersistedException> { cartItemService.create() }

        verify(cartItemRepository).save(any<CartItemEntity>())
    }

    @Test
    fun `get - happy path`() {
        val id = 5L
        val persistedEntity = cartItemEntity().apply { this.id = id }

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.of(persistedEntity))

        val result = cartItemService.get(id)

        assertEquals(persistedEntity.let { cartItemEntityResolver.toModel(it) }, result)
        verify(cartItemRepository).findById(id)
    }

    @Test
    fun `get - fail - not found`() {
        val id = 5L

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { cartItemService.get(id) }

        verify(cartItemRepository).findById(id)
    }

    @Test
    @Disabled("Highly linked. Mock resolver or menuItem repository")
    fun `update - happy path - update all fields`() {
        val id = 3L
        val persistedCartItem = cartItemEntity(id = id)
        val newCartItem = cartItem(
            id = id,
            quantity = 3,
            menuItem = menuItem(id = 2)
        )

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.of(persistedCartItem))

        val result = cartItemService.update(id, newCartItem.copy(id = null))

        assertEquals(newCartItem, result)
        verify(cartItemRepository).findById(id)
    }

    @Test
    @Disabled("Highly linked. Mock resolver or menuItem repository")
    fun `update - happy path - update a few fields`() {
        val id = 3L
        val persistedCartItem = cartItemEntity(id = id)
        val newCartItem = cartItem(
            id = id,
            quantity = 3,
            menuItem = null
        )

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.of(persistedCartItem))

        val result = cartItemService.update(id, newCartItem.copy(id = null))

        with(result) {
            assertEquals(persistedCartItem.id, result.id)
            assertEquals(newCartItem.quantity, quantity)
            assertEquals(persistedCartItem.menuItem, menuItem)
        }
        verify(cartItemRepository).findById(id)
    }

    @Test
    fun `update - fail - cartItem not found`() {
        val id = 3L
        val newCartItem = cartItem(id = id)

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { cartItemService.update(id, newCartItem.copy(id = null)) }

        verify(cartItemRepository).findById(id)
    }

    @Test
    fun `update - happy path - menuItem not found`() {
        val id = 3L
        val persistedCartItem = cartItemEntity(id = id)
        val newCartItem = cartItem(
            id = id,
            quantity = 3,
            menuItem = menuItem(id = 2)
        )

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.of(persistedCartItem))

        assertThrows<EntityNotFoundException> { cartItemService.update(id, newCartItem.copy(id = null)) }

        verify(cartItemRepository).findById(id)
    }

    @Test
    fun `delete - happy path`() {
        val id = 5L
        val persistedCartItem = cartItemEntity(id = id)

        whenever(cartItemRepository.findById(id)).thenReturn(Optional.of(persistedCartItem))

        cartItemService.delete(id)

        verify(cartItemRepository).findById(id)
        verify(cartItemRepository).deleteById(id)
    }

    @Test
    fun `delete - fail - not found`() {
        val id = 5L
        whenever(cartItemRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> { cartItemService.delete(id) }

        verify(cartItemRepository).findById(id)
        verify(cartItemRepository, times(0)).deleteById(id)
    }

}