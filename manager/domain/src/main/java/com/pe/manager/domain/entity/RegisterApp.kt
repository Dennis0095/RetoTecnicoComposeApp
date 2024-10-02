package com.pe.manager.domain.entity

data class RegisterApp(
    val idApp: Int = 1,
    val nameApp: String = "",
    val version: String = "",
    val usersActive: String = "",
    val cpuResource: String = "",
    val memoryResource: String = "",
    val storageResource: String = "",
    val networkConsumptionResource: String = "",
    val costs: String = "",
    val comments: String = "",
    val keyActivities: String = "",
    val numberOfIncidents: String = "",
    var typeApp: TypeApp? = null,
    var frequency: Frequency? = null,
    var priority: Priority? = null,
    var statusApp: StatusApp? = null,
)
