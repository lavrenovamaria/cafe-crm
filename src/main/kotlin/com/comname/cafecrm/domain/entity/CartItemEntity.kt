package com.comname.cafecrm.domain.entity

import javax.persistence.*

@Entity
@Table(name = "cart_item")
class CartItemEntity(
    @Column(name = "quantity")
    var quantity: Long? = null,
    @OneToOne
    @JoinColumn(name = "menu_item_id")
    var menuItem: MenuItemEntity? = null
) : BaseEntity<Long>() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_generator")
    @SequenceGenerator(name = "cart_item_generator", sequenceName = "cart_item_sequence", allocationSize = 1)
    override var id: Long? = null

}