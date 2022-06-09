package com.comname.cafecrm.domain.entity

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

}