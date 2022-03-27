package com.atarious.userdetails.Practices.Model



data class User (
    val id:Number,
    val first_name:String,
    val last_name:String,
    val email:String,
    val gender:String,
    val ip_address:String,
    val date_of_birth:String,
    val Image:String,
    val Address: Data,
    val Company:String
)
data class Data(
    val Street:String,
    val Suite:String,
    val City:String
)
