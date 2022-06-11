package com.comname.cafecrm.domain.entity

import com.comname.cafecrm.domain.model.MenuItem
import javax.persistence.*

@Entity
@Table(name = "menu_item")
class MenuItemEntity(
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "category")
    var category: String? = null,
    @Column(name = "ingredients")
    var ingredients: String? = null,
    @Column(name = "is_available")
    var isAvailable: Boolean? = null,
    @Column(name = "weight")
    var weight: Long? = null,
    @Column(name = "price")
    var price: Long? = null,
    @Column(name = "image")
    var image: String? = null
) : BaseEntity<Long>() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_generator")
    @SequenceGenerator(name = "menu_item_generator", sequenceName = "menu_item_sequence", allocationSize = 1)
    override var id: Long? = null

    fun merge(new: MenuItem) =
        this.apply {
            name = new.name ?: this.name
            category = new.category ?: this.category
            ingredients = new.ingredients ?: this.ingredients
            isAvailable = new.isAvailable ?: this.isAvailable
            weight = new.weight ?: this.weight
            price = new.price ?: this.price
            image = new.image ?: this.image
        }
}