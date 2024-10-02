package com.pe.manager.domain.entity

enum class Priority(val id: Int, val description: String) {
    HIGH(1, "Alta"),
    MEDIUM(2, "Media"),
    LOW(3, "Baja");
    companion object{
        fun getPriority(description: String): Priority {
            val default: Priority = Priority.HIGH
            return entries.find { it.description == description } ?: default
        }
    }
}