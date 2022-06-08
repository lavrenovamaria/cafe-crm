package com.comname.cafecrm.controller

import com.comname.cafecrm.BaseControllerTest
import com.comname.cafecrm.domain.converter.toDto
import com.comname.cafecrm.domain.dto.MenuItemDto
import com.comname.cafecrm.domain.menuItem
import com.comname.cafecrm.service.MenuService
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MenuController::class)
class MenuControllerTest : BaseControllerTest() {

    @MockBean
    private lateinit var menuService: MenuService

    @Test
    fun `getMenu - happy path`() {
        val menuItemList = listOf(menuItem(), menuItem())

        whenever(menuService.getMenu()).thenReturn(menuItemList)

        val result = objectMapper.readValue<List<MenuItemDto>>(
            mockMvc.perform(get("/menu"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.contentAsByteArray
        )

        assertEquals(menuItemList.map { it.toDto() }, result)
    }

}