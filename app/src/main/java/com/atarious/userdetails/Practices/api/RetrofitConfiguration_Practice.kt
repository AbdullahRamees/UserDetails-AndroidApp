package com.atarious.userdetails.Practices.api

import com.atarious.userdetails.Practices.Model.User
import com.atarious.userdetails.Practices.Model.userList_Practices
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

    interface UserApiServices_Practices {
        @GET("user")
        fun getUsers(): Call<List<userList_Practices>>

        @GET("user/{ID}")
        fun GetUser(@Path("ID") id: String): Call<User>

        companion object {
            val api_Url = "https://api.npoint.io/937440a7ace2bec27da2/"
            fun create(): UserApiServices_Practices {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(api_Url)
                    .build()
                return retrofit.create(UserApiServices_Practices::class.java)
            }
        }
    }
