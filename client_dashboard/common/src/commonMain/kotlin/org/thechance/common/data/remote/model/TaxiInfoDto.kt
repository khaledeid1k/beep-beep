package org.thechance.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxiInfoDto(
    @SerialName("driverUsername") val driverUsername: String,
    @SerialName("plateNumber") val plateNumber: String,
    @SerialName("color") val color: String,
    @SerialName("type") val type: String,
    @SerialName("seats") val seats: Int,
)