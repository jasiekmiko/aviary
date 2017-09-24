package eu.bluehawkqs.aviary.api.models

import java.time.LocalDate

data class Person(val id: Int = 0, val firstName: String, val lastName: String, val dob: LocalDate, val gender: String)