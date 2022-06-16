package com.comname.cafecrm.domain.entity.resolver

interface Resolver<Entity, Model> {

    fun toEntity(model: Model): Entity

    fun toModel(entity: Entity): Model

    fun merge(old: Entity, new: Model): Entity

}