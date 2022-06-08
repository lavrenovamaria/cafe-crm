package com.comname.cafecrm.repository

import com.comname.cafecrm.domain.entity.MenuItemEntity
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class MenuItemRepository {

    fun getAll() = generateMenu()

    private fun generateMenu(numberOfItems: Long = 15) =
        (0..numberOfItems)
            .map { id ->
                MenuItemEntity(
                    id = id,
                    name = dishNames[(id % 8).toInt()],
                    category = "category",
                    ingredients = words.take(8).joinToString(", "),
                    isAvailable = Random.nextInt() % 4 != 1,
                    weight = (Random.nextInt() % 80) * 100L,
                    price = (Random.nextInt() % 60) * 100L,
                    image = "https://picsum.photos/id/$id/300/300"
                )
            }

    val dishNames = "Beef Stroganoff · Beef Wellington · Caesar Salad · Chicken Marengo · Delmonico Steak (and Delmonico Potatoes) · Eggs Benedict · Lobster Newburg · Peach Melba".split(" · ")
    val words = ("Suspendisse potenti. Cras imperdiet tristique nunc sed bibendum. Pellentesque vel justo sed turpis gravida sollicitudin ac vitae urna. Mauris ut dolor malesuada, commodo tellus sit amet, tincidunt tellus. Pellentesque varius dignissim lorem, ac tristique purus rhoncus at. Ut porttitor mollis massa, non dignissim libero fermentum at. Sed semper, diam et iaculis venenatis, velit turpis elementum ex, laoreet tristique tortor enim vel libero. Pellentesque convallis, justo at tristique pellentesque, massa enim tempus felis, ut euismod sem diam quis ipsum. Duis egestas neque sed felis eleifend dignissim.\n" +
            "\n" +
            "Proin vehicula turpis ut lectus sagittis, ac vehicula dolor egestas. Donec vestibulum dolor luctus tempus pharetra. Pellentesque sem augue, tincidunt eu mattis vel, bibendum a velit. Nam ac enim laoreet, hendrerit metus facilisis, laoreet magna. Phasellus dapibus mattis diam, eu sagittis odio accumsan at. Suspendisse cursus nisl eget consequat suscipit. Nam in nunc condimentum, aliquam quam quis, pulvinar ante. In ultrices justo et nunc scelerisque sagittis. Donec sodales posuere sagittis. Curabitur ullamcorper eros eu ex elementum, sit amet dignissim purus pretium. Duis at erat vulputate, blandit leo id, lacinia quam.\n" +
            "\n" +
            "Nam non turpis erat. Proin sodales placerat nisi, vel volutpat nisi egestas vel. Nunc in sem lorem. Quisque lacinia interdum justo, at pulvinar turpis rhoncus ac. In elementum nisi nec purus aliquam porttitor. Cras vel luctus massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus elementum, est non vestibulum efficitur, orci neque vestibulum arcu, pretium cursus quam lectus sit amet justo. Sed non dignissim mi. Morbi sodales neque vitae orci luctus, in bibendum est varius. Donec eu leo in massa porta volutpat quis quis velit. Phasellus non sem et ligula scelerisque pellentesque. Integer eros dui, malesuada in facilisis sit amet, feugiat vitae diam. Vestibulum bibendum, nisi non porta venenatis, ex ipsum consequat tellus, sit amet elementum mi justo eget libero.").split(" ")
}