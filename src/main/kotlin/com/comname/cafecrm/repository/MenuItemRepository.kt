package com.comname.cafecrm.repository

import com.comname.cafecrm.domain.entity.MenuItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemRepository : JpaRepository<MenuItemEntity, Long> {

    fun getAllBy(): List<MenuItemEntity>

}