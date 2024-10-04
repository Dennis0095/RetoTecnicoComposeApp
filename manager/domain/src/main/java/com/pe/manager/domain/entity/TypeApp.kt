package com.pe.manager.domain.entity

enum class TypeApp(val id: Int, val description: String) {
    SECURITY(1, "Seguridad"),
    OPERATION(2, "Operacional"),
    FINANCE(3, "Finanzas"),
    PRODUCTIVITY(4, "Productividad"),
    LOGISTICS(5, "Logística");

    companion object {
        fun getTypeApp(description: String): TypeApp {
            val default: TypeApp = SECURITY
            return entries.find { it.description == description } ?: default
        }
    }
}