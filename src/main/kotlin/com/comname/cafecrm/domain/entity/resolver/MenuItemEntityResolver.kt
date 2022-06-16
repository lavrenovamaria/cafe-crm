package com.comname.cafecrm.domain.entity.resolver

import com.comname.cafecrm.domain.entity.MenuItemEntity
import com.comname.cafecrm.domain.model.MenuItem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(propagation = Propagation.MANDATORY)
class MenuItemEntityResolver : Resolver<MenuItemEntity, MenuItem>{

    override fun toEntity(model: MenuItem) =
        with(model) {
            MenuItemEntity(
                name = name,
                category = category,
                ingredients = ingredients,
                isAvailable = isAvailable,
                weight = weight,
                price = price,
                image = image
            )
        }

    override fun toModel(entity: MenuItemEntity) =
        with(entity) {
            MenuItem(
                id = id,
                name = name,
                category = category,
                ingredients = ingredients,
                isAvailable = isAvailable,
                weight = weight,
                price = price,
                image = image
            )
        }

    override fun merge(old: MenuItemEntity, new: MenuItem) =
        old.apply {
            name = new.name ?: old.name
            category = new.category ?: old.category
            ingredients = new.ingredients ?: old.ingredients
            isAvailable = new.isAvailable ?: old.isAvailable
            weight = new.weight ?: old.weight
            price = new.price ?: old.price
            image = new.image ?: old.image
        }

}