package org.thechance.service_restaurant.entity



data class Meal(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Int? = null,
    val isDeleted: Boolean = false,
    val cuisines: List<String>
)
