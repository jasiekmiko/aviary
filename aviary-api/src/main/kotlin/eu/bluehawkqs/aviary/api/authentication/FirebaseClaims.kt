package eu.bluehawkqs.aviary.api.authentication

import io.jsonwebtoken.Claims

@Suppress("unused")
class FirebaseClaims(private val claims: Claims) {

    val uid: String
        get() = claims["user_id"] as String

    val issuer: String
        get() = claims["iss"] as String

    val name: String
        get() = claims["name"] as String

    val picture: String
        get() = claims["picture"] as String

    val email: String
        get() = claims["email"] as String

    val isEmailVerified: Boolean
        get() = java.lang.Boolean.valueOf(claims["email_verified"] as String)!!

}