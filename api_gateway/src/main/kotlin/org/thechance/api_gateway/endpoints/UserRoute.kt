package org.thechance.api_gateway.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.api_gateway.data.localizedMessages.LocalizedMessagesFactory
import org.thechance.api_gateway.data.model.restaurant.Restaurant
import org.thechance.api_gateway.data.model.restaurant.RestaurantRequestPermission
import org.thechance.api_gateway.data.security.TokenConfiguration
import org.thechance.api_gateway.endpoints.gateway.IIdentityGateway
import org.thechance.api_gateway.endpoints.gateway.IRestaurantGateway
import org.thechance.api_gateway.endpoints.utils.authenticateWithRole
import org.thechance.api_gateway.endpoints.utils.extractLocalizationHeader
import org.thechance.api_gateway.endpoints.utils.respondWithResult
import org.thechance.api_gateway.util.Claim.PERMISSION
import org.thechance.api_gateway.util.Claim.USERNAME
import org.thechance.api_gateway.util.Claim.USER_ID
import org.thechance.api_gateway.util.Role
import java.util.*

/**
 * Created by Aziza Helmy on 8/14/2023.
 */
fun Route.userRoutes(tokenConfiguration: TokenConfiguration) {
    val gateway: IIdentityGateway by inject()
    val restaurantGateway: IRestaurantGateway by inject()
    val localizedMessagesFactory by inject<LocalizedMessagesFactory>()

    post("/signup") {
        val params = call.receiveParameters()
        val fullName = params["fullName"]?.trim()
        val username = params["username"]?.trim()
        val password = params["password"]?.trim()
        val email = params["email"]?.trim()

        val (language, countryCode) = extractLocalizationHeader()

        val result = gateway.createUser(
            fullName = fullName.toString(),
            username = username.toString(),
            password = password.toString(),
            email = email.toString(),
            locale = Locale(language, countryCode)
        )
        val locale = Locale(language, countryCode)
        val successMessage = localizedMessagesFactory.createLocalizedMessages(locale).userCreatedSuccessfully

        respondWithResult(HttpStatusCode.Created, result, successMessage)
    }

    post("/login") {
        val params = call.receiveParameters()
        val userName = params["username"]?.trim().toString()
        val password = params["password"]?.trim().toString()

        val (language, countryCode) = extractLocalizationHeader()

        val token = gateway.loginUser(
            userName,
            password,
            tokenConfiguration,
            Locale(language, countryCode)
        )
        respondWithResult(HttpStatusCode.Created, token)
    }

    post("/restaurant/permission") {
        val requestedForm = call.receive<RestaurantRequestPermission>()
        val (language, countryCode) = extractLocalizationHeader()
        val result = restaurantGateway.createRequestPermission(
            requestedForm, Locale(language, countryCode)
        )
        respondWithResult(HttpStatusCode.Created, result)
    }

    authenticateWithRole(Role.END_USER) {
        get("/me") {
            val tokenClaim = call.principal<JWTPrincipal>()
            val id = tokenClaim?.payload?.getClaim(USER_ID).toString()
            respondWithResult(HttpStatusCode.OK, id)
        }

        post("/refresh-access-token") {
            val tokenClaim = call.principal<JWTPrincipal>()
            val userId = tokenClaim?.payload?.getClaim(USER_ID).toString()
            val username = tokenClaim?.payload?.getClaim(USERNAME).toString()
            val userPermission = tokenClaim?.payload?.getClaim(PERMISSION)?.asString()?.toInt() ?: 1
            val token = gateway.generateUserTokens(userId, username, userPermission, tokenConfiguration)
            respondWithResult(HttpStatusCode.Created, token)
        }
    }

    authenticateWithRole(Role.DASHBOARD_ADMIN) {

        get("/users") {
            val (language, countryCode) = extractLocalizationHeader()
            val page = call.parameters["page"]?.toInt() ?: 1
            val limit = call.parameters["limit"]?.toInt() ?: 20
            val result = gateway.getUsers(page = page, limit = limit, locale = Locale(language, countryCode))
            respondWithResult(HttpStatusCode.OK, result)
        }

        route("/user") {

            delete("/{id}") {
                val id = call.parameters["id"] ?: ""
                val (language, countryCode) = extractLocalizationHeader()
                val result = gateway.deleteUser(userId = id, locale = Locale(language, countryCode))
                respondWithResult(HttpStatusCode.OK, result)
            }
        }
    }
}

