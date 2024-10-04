package com.pe.manager.domain.entity

enum class StatusApp(val id: Int, val description: String) {
    IN_USE(1, "En uso"),
    OBSOLETE(2, "Obsoleto"),
    REDUNDANT(3, "Reduntante"),
    INACTIVE(4, "Inactivo");

    companion object {
        fun getStatusApp(description: String): StatusApp {
            val default: StatusApp = IN_USE
            return entries.find { it.description == description } ?: default
        }
    }
}