package com.comname.cafecrm.service

import com.comname.cafecrm.BaseServiceTest
import com.comname.cafecrm.domain.converter.toModel
import com.comname.cafecrm.domain.menuItemEntity
import com.comname.cafecrm.repository.MenuItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

class MenuServiceTest : BaseServiceTest() {

    @Autowired
    private lateinit var menuService: MenuService

    @MockBean
    private lateinit var menuItemRepository: MenuItemRepository

    @Test
    fun `getMenu - happy path`() {
        val menuItemList = listOf(menuItemEntity(), menuItemEntity())

        whenever(menuItemRepository.getAllBy()).thenReturn(menuItemList)

        val result = menuService.getMenu()

        assertEquals(menuItemList.map { it.toModel() }, result)
    }
}