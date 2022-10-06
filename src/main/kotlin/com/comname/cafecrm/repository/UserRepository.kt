package com.comname.cafecrm.repository

import com.comname.cafecrm.domain.entity.CartItemEntity
import com.comname.cafecrm.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<CartItemEntity, Long> {
    //abstract fun save(userEntity: UserEntity): UserEntity
}