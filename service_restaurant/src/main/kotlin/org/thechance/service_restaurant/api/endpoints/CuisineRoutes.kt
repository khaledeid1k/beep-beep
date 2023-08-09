package org.thechance.service_restaurant.api.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.service_restaurant.api.models.CuisineDto
import org.thechance.service_restaurant.api.models.mappers.toDto
import org.thechance.service_restaurant.api.models.mappers.toEntity
import org.thechance.service_restaurant.api.utils.extractInt
import org.thechance.service_restaurant.api.utils.extractString
import org.thechance.service_restaurant.domain.usecase.IDiscoverRestaurantUseCase
import org.thechance.service_restaurant.domain.usecase.IManageCuisineUseCase
import org.thechance.service_restaurant.utils.MissingParameterException
import org.thechance.service_restaurant.utils.NOT_FOUND

fun Route.cuisineRoutes() {

    val manageCuisine: IManageCuisineUseCase by inject()
    val discoverRestaurant: IDiscoverRestaurantUseCase by inject()

    get("cuisines") {
        val page = call.parameters.extractInt("page") ?: 1
        val limit = call.parameters.extractInt("limit") ?: 10
        val cuisines = manageCuisine.getCuisines(page, limit)
        call.respond(HttpStatusCode.OK, cuisines.toDto())
    }

    route("cuisine") {

        get("/{id}/meals") {
            val id = call.parameters.extractString("id") ?: throw NotFoundException()
            val meals = discoverRestaurant.getMealsByCuisine(id).toDto()
            call.respond(HttpStatusCode.OK, meals)
        }

        post {
            val cuisine = call.receive<CuisineDto>()
            val isAdded = manageCuisine.addCuisine(cuisine.toEntity())
            if (isAdded) call.respond(HttpStatusCode.Created, "Added Successfully")
        }

        put {
            val cuisine = call.receive<CuisineDto>()
            val isUpdated = manageCuisine.updateCuisine(cuisine.toEntity())
            if (isUpdated) call.respond(HttpStatusCode.OK, "Updated Successfully")
        }

        delete("/{id}") {
            val id = call.parameters.extractString("id") ?: throw MissingParameterException(NOT_FOUND)
            val isDeleted = manageCuisine.deleteCuisine(id)
            if (isDeleted) call.respond(HttpStatusCode.OK, "Deleted Successfully")
        }

    }

}