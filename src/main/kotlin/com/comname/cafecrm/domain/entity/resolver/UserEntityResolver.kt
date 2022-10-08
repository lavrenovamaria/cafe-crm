package com.comname.cafecrm.domain.entity.resolver

import com.comname.cafecrm.domain.entity.UserEntity
import com.comname.cafecrm.domain.model.User
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.repository.UserRepository
import liquibase.pro.packaged.it
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(propagation = Propagation.MANDATORY)
class UserEntityResolver(
    private val userEntityResolver: UserEntityResolver?,//?
    private val userRepository: UserRepository
) : Resolver<UserEntity, User>{

    override fun toModel(entity: UserEntity) =
        with(entity) {
            User(
                id = id
            )
        }

    override fun merge(old: UserEntity, new: User) =
        old.apply {
            val newUser = new.id?.let { userId ->
                userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException(UserEntity::class, userId)
            }
            id = new.id ?: old.id
        }

    override fun toEntity(model: User): UserEntity {
        TODO("Not yet implemented")
    }
}