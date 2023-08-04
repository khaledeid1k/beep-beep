package org.thechance.service_restaurant.domain.usecase.restaurant

import org.thechance.service_restaurant.domain.entity.Address
import org.thechance.service_restaurant.domain.entity.Category
import org.thechance.service_restaurant.domain.entity.Cuisine
import org.thechance.service_restaurant.domain.entity.Restaurant

interface ManageRestaurantUseCase {
    suspend fun getRestaurants(page: Int, limit: Int): List<Restaurant>
    suspend fun getCategoriesInRestaurant(restaurantId: String): List<Category>
    suspend fun getCuisinesInRestaurant(restaurantId: String): List<Cuisine>
    suspend fun deleteCategoriesInRestaurant(restaurantId: String, categoryIds: List<String>): Boolean
    suspend fun addCategoryToRestaurant(restaurantId: String, categoryIds: List<String>): Boolean

}