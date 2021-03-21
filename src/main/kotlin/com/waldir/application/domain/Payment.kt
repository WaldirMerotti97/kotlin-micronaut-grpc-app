package com.waldir.application.domain

import java.time.LocalDateTime

data class Payment(
        val sourcePersonId: Int,
        val sourceAccountId: Int,
        val destinationPersonId: Int,
        val destinationAccountId: Int,
        val transferValue: Double,
        val date: LocalDateTime,
        val status: String
) {
}