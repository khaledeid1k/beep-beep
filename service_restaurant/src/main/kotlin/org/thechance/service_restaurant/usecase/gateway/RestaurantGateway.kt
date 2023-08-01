package org.thechance.service_restaurant.usecase.gateway

import org.thechance.service_restaurant.entity.Category
import org.thechance.service_restaurant.entity.Restaurant

interface RestaurantGateway {

    //region Category
    suspend fun getCategories(): List<Category>
    suspend fun getCategory(categoryId: String): Category?
    suspend fun addCategory(category: Category): Boolean
    suspend fun updateCategory(category: Category): Boolean
    suspend fun deleteCategory(categoryId: String): Boolean
    suspend fun addRestaurantsToCategory(categoryId: String, restaurantIds: List<String>): Boolean
    //endregion

    //region restaurant
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun getRestaurant(id: String): Restaurant?
    suspend fun addRestaurant(restaurant: Restaurant): Boolean
    suspend fun updateRestaurant(restaurant: Restaurant): Boolean
    suspend fun deleteRestaurant(restaurantId: String): Boolean
    //endregion

}