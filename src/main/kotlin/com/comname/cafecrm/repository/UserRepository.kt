package com.comname.cafecrm.repository

import com.comname.cafecrm.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    //abstract fun save(userEntity: UserEntity): UserEntity
}