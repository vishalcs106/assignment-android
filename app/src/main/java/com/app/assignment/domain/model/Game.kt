package com.app.assignment.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String = "",
    @SerialName("appUrl")
    val appUrl: String = "",
    @SerialName("gameForm")
    val gameForm: String = "",
    @SerialName("packageId")
    val packageId: String = ""
)
