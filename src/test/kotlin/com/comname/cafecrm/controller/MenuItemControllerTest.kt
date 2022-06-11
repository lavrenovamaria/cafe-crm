package com.comname.cafecrm.controller

import com.comname.cafecrm.BaseControllerTest
import com.comname.cafecrm.domain.converter.toDto
import com.comname.cafecrm.domain.dto.IdDto
import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.domain.menuItem
import com.comname.cafecrm.service.MenuItemService
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(MenuItemController::class)
class MenuItemControllerTest : BaseControllerTest() {

    @MockBean
    private lateinit var menuItemService: MenuItemService

    @Test
    fun `create - happy path`() {
        val id = 3L

        whenever(menuItemService.create()).thenReturn(id)

        val result = objectMapper.readValue<IdDto>(
            mockMvc.perform(MockMvcRequestBuilders.post("/menu-items"))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(id.toDto(), result)
        verify(menuItemService).create()
    }

    @Test
    fun `get - happy path`() {
        val id = 3L
        val menuItem = menuItem(id = id)

        whenever(menuItemService.get(id)).thenReturn(menuItem)

        val result = objectMapper.readValue<MenuItemDto>(
            mockMvc.perform(MockMvcRequestBuilders.get("/menu-items/$id"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(menuItem.toDto(), result)
        verify(menuItemService).get(id)
    }

    @Test
    fun `getMenu - happy path`() {
        val menuItemList = listOf(menuItem(), menuItem())

        whenever(menuItemService.getAll()).thenReturn(menuItemList)

        val result = objectMapper.readValue<List<MenuItemDto>>(
            mockMvc.perform(MockMvcRequestBuilders.get("/menu-items"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
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
                MockMvcRequestBuilders.patch("/menu-items/$id")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(menuItem.toDto()))
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(updatedMenuItem.toDto(), result)
        verify(menuItemService).update(id, menuItem)
    }

    @Test
    fun `delete - happy path`() {
        val id = 3L

        mockMvc.perform(MockMvcRequestBuilders.delete("/menu-items/$id"))
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(menuItemService).delete(id)
    }

}