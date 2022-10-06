package com.comname.cafecrm.domain.entity.resolver

import com.comname.cafecrm.domain.entity.UserEntity
import com.comname.cafecrm.domain.model.User
import com.comname.cafecrm.exception.EntityNotFoundException
import com.comname.cafecrm.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(propagation = Propagation.MANDATORY)
class UserEntityResolver(
    private val userEntityResolver: UserEntityResolver,
    private val userRepository: UserRepository
) : Resolver<UserEntity, User>{

    override fun toEntity(model: User) =
        with(model) {
            UserEntity(
                quantity = quantity,
                user = user?.let { userEntityResolver.toEntity(it) }
            )
        }

    override fun toModel(entity: UserEntity) =
        with(entity) {
            User(
                id = id,
                quantity = quantity,
                user = user?.let { userEntityResolver.toEntity(it) }
            )
        }

    override fun merge(old: UserEntity, new: User) =
        old.apply {
            val newUser = new.user?.id?.let { userId ->
                userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException(UserEntity::class, userId)
            }
            quantity = new.quantity ?: old.quantity
            user = newUser ?: old.user
        }

}