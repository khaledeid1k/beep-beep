package org.thechance.service_restaurant.usecase.address

import org.koin.core.annotation.Single
import org.thechance.service_restaurant.api.usecases.DeleteAddressesInRestaurantUseCase
import org.thechance.service_restaurant.usecase.gateway.RestaurantGateway

@Single
class DeleteAddressesInRestaurantUseCaseImpl(
    private val restaurantGateway: RestaurantGateway
) : DeleteAddressesInRestaurantUseCase {
    override suspend fun invoke(restaurantId: String, addressesIds: List<String>): Boolean {
        return restaurantGateway.deleteAddressesInRestaurant(restaurantId, addressesIds)
    }

}