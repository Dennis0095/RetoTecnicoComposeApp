package com.pe.manager.domain.entity

data class RegisterApp(
    val idApp: Int = 1,
    val nameApp: String = "",
    val version: String = "",
    val usersActive: String = "",
    val costs: String = "",
    val comments: String = "",
    val keyActivities: String = "",
    val numberOfIncidents: String = "",
    val recommendations: String = "",
    val showRecommendation: Boolean = false,
    var typeApp: TypeApp? = null,
    var frequency: Frequency? = null,
    var priority: Priority? = null,
    var statusApp: StatusApp? = null,
)
