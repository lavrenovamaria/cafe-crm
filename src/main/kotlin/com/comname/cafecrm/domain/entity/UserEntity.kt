package com.comname.cafecrm.domain.entity

import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity() : BaseEntity<Long>() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_generator", allocationSize = 1)
    override var id: Long? = null

}