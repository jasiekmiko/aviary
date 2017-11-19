package eu.bluehawkqs.aviary.api.models

import java.time.LocalDate

data class Tournament(
    val id: Int = 0,
    val name: String,
    val description: String,
    val location: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val registrationDeadline: LocalDate,
    val size: Int,
    val currentUserAttending: Boolean)