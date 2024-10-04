package com.pe.manager.domain.entity

enum class Frequency(val id: Int, val description: String) {
    DAILY(1, "Diario"),
    WEEKLY(2, "Semanal"),
    MONTHLY(3, "Mensual"),
    RARELY(4, "Rara vez");

    companion object {
        fun getFrequency(description: String): Frequency {
            val default: Frequency = Frequency.DAILY
            return Frequency.values().find { it.description == description } ?: default
        }
    }
}