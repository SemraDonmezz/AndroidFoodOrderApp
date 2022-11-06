package com.example.foodapp.retrofit

class ApiUtils {
    companion object {
        var BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

        fun getFoodDao() : FoodDao {
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }
    }
}