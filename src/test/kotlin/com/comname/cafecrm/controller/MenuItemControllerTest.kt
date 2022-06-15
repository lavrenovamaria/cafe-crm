package com.comname.cafecrm.controller

import com.comname.cafecrm.BaseControllerTest
import com.comname.cafecrm.domain.converter.toDto
import com.comname.cafecrm.domain.dto.IdDto
import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.menuItem
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.exception.EntityNotPersistedException
import com.comname.cafecrm.exception.ErrorInfo
import com.comname.cafecrm.service.MenuItemService
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

@WebMvcTest(MenuItemController::class, excludeAutoConfiguration = [SecurityAutoConfiguration::class])
class MenuItemControllerTest : BaseControllerTest() {

    @MockBean
    private lateinit var menuItemService: MenuItemService

    @Test
    fun `create - happy path`() {
        val id = 3L

        whenever(menuItemService.create()).thenReturn(id)

        val result = objectMapper.readValue<IdDto>(
            mockMvc.perform(post("$API_PREFIX/menu-items"))
                .andExpect(status().isCreated)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(id.toDto(), result)
        verify(menuItemService).create()
    }

    @Test
    fun `create - fail - not persisted`() {
        val expectedMessage = "MenuItemEntity is not persisted"

        whenever(menuItemService.create()).thenThrow(EntityNotPersistedException(MenuItemEntity::class))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(post("$API_PREFIX/menu-items"))
                .andExpect(status().isInternalServerError)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(menuItemService).create()
    }

    @Test
    fun `get - happy path`() {
        val id = 3L
        val menuItem = menuItem(id = id)

        whenever(menuItemService.get(id)).thenReturn(menuItem)

        val result = objectMapper.readValue<MenuItemDto>(
            mockMvc.perform(get("$API_PREFIX/menu-items/$id"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(menuItem.toDto(), result)
        verify(menuItemService).get(id)
    }

    @Test
    fun `get - fail - not found`() {
        val id = 3L
        val expectedMessage = "MenuItemEntity with id = $id is not found"

        whenever(menuItemService.get(id)).thenThrow(EntityNotFoundException(MenuItemEntity::class, id))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(get("$API_PREFIX/menu-items/$id"))
                .andExpect(status().isNotFound)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(menuItemService).get(id)
    }


    @Test
    fun `getMenu - happy path`() {
        val menuItemList = listOf(menuItem(), menuItem())

        whenever(menuItemService.getAll()).thenReturn(menuItemList)

        val result = objectMapper.readValue<List<MenuItemDto>>(
            mockMvc.perform(get("$API_PREFIX/menu-items"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(menuItemList.map { it.toDto() }, result)
    }

    @Test
    fun `update - happy path`() {
        val id = 3L
        val menuItem = menuItem(id = id)
        val updatedMenuItem = menuItem.copy(name = "new")

        whenever(menuItemService.update(id, menuItem)).thenReturn(updatedMenuItem)

        val result = objectMapper.readValue<MenuItemDto>(
            mockMvc.perform(
                patch("$API_PREFIX/menu-items/$id")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(menuItem.toDto()))
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(updatedMenuItem.toDto(), result)
        verify(menuItemService).update(id, menuItem)
    }

    @Test
    fun `update - fail - not found`() {
        val id = 3L
        val menuItem = menuItem(id = id)
        val expectedMessage = "MenuItemEntity with id = $id is not found"

        whenever(menuItemService.update(id, menuItem)).thenThrow(EntityNotFoundException(MenuItemEntity::class, id))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(
                patch("$API_PREFIX/menu-items/$id")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(menuItem.toDto())))
                .andExpect(status().isNotFound)
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(menuItemService).update(id, menuItem)
    }

    @Test
    fun `delete - happy path`() {
        val id = 3L

        mockMvc.perform(delete("$API_PREFIX/menu-items/$id"))
            .andExpect(status().isNoContent)

        verify(menuItemService).delete(id)
    }

    @Test
    fun `delete - fail - not found`() {
        val id = 3L
        val expectedMessage = "MenuItemEntity with id = $id is not found"

        whenever(menuItemService.delete(id)).thenThrow(EntityNotFoundException(MenuItemEntity::class, id))

        val result = objectMapper.readValue<ErrorInfo>(
            mockMvc.perform(delete("$API_PREFIX/menu-items/$id"))
                .andExpect(status().isNotFound)
                .andReturn().response.contentAsByteArray
        )

        assertEquals(expectedMessage, result.message)
        verify(menuItemService).delete(id)
    }

}