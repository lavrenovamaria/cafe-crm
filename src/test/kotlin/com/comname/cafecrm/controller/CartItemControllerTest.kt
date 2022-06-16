package com.comname.cafecrm.controller

import com.comname.cafecrm.BaseControllerTest
import com.comname.cafecrm.domain.cartItem
import com.comname.cafecrm.domain.dto.CartItemDto
import com.comname.cafecrm.domain.dto.IdDto
import com.comname.cafecrm.domain.dto.toDto
import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.exception.ErrorInfo
import com.comname.cafecrm.service.CartItemService
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CartItemController::class, excludeAutoConfiguration = [SecurityAutoConfiguration::class])
class CartItemControllerTest : BaseControllerTest() {

    @MockBean
    private lateinit var cartItemService: CartItemService

    @Test
    fun `create - happy path`() {
        val id = 3L

        whenever(cartItemService.create()).thenReturn(id)

        val result = objectMapper.readValue<IdDto>(
            mockMvc.perform(post("$API_PREFIX/cart-items"))
                .andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(id.toDto(), result)
        verify(cartItemService).create()
    }

    @Test
    fun `create - fail - not persisted`() {
        val expectedMessage = "CartItemEntity is not persisted"

        whenever(cartItemService.create()).thenThrow(EntityNotPersistedException(CartItemEntity::class))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(post("$API_PREFIX/cart-items"))
                .andExpect(status().isInternalServerError)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(cartItemService).create()
    }

    @Test
    fun `get - happy path`() {
        val id = 3L
        val cartItem = cartItem(id = id)

        whenever(cartItemService.get(id)).thenReturn(cartItem)

        val result = objectMapper.readValue<CartItemDto>(
            mockMvc.perform(get("$API_PREFIX/cart-items/$id"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(cartItem.toDto(), result)
        verify(cartItemService).get(id)
    }

    @Test
    fun `get - fail - not found`() {
        val id = 3L
        val expectedMessage = "CartItemEntity with id = $id is not found"

        whenever(cartItemService.get(id)).thenThrow(EntityNotFoundException(CartItemEntity::class, id))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(get("$API_PREFIX/cart-items/$id"))
                .andExpect(status().isNotFound)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(cartItemService).get(id)
    }

    @Test
    fun `update - happy path`() {
        val id = 3L
        val cartItem = cartItem(id = id)
        val updatedCartItem = cartItem.copy(quantity = 3)

        whenever(cartItemService.update(id, cartItem)).thenReturn(updatedCartItem)

        val result = objectMapper.readValue<CartItemDto>(
            mockMvc.perform(
                patch("$API_PREFIX/cart-items/$id")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(cartItem.toDto()))
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(updatedCartItem.toDto(), result)
        verify(cartItemService).update(id, cartItem)
    }

    @Test
    fun `update - fail - not found`() {
        val id = 3L
        val cartItem = cartItem(id = id)
        val expectedMessage = "CartItemEntity with id = $id is not found"

        whenever(cartItemService.update(id, cartItem)).thenThrow(EntityNotFoundException(CartItemEntity::class, id))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(
                patch("$API_PREFIX/cart-items/$id")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(cartItem.toDto())))
                .andExpect(status().isNotFound)
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(cartItemService).update(id, cartItem)
    }

    @Test
    fun `delete - happy path`() {
        val id = 3L

        mockMvc.perform(delete("$API_PREFIX/cart-items/$id"))
            .andExpect(status().isNoContent)

        verify(cartItemService).delete(id)
    }

    @Test
    fun `delete - fail - not found`() {
        val id = 3L
        val expectedMessage = "CartItemEntity with id = $id is not found"

        whenever(cartItemService.delete(id)).thenThrow(EntityNotFoundException(CartItemEntity::class, id))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(delete("$API_PREFIX/cart-items/$id"))
                .andExpect(status().isNotFound)
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(cartItemService).delete(id)
    }

}