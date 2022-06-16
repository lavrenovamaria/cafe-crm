package com.comname.cafecrm.repository

import com.comname.cafecrm.domain.entity.CartItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItemEntity, Long>