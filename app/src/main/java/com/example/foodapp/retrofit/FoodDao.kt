package com.example.foodapp.retrofit

import com.example.foodapp.data.entities.SepetCRUDCevap
import com.example.foodapp.data.entities.SepetYemeklerCevap
import com.example.foodapp.data.entities.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("tumYemekleriGetir.php")
    suspend fun mainFoodYukle() : YemeklerCevap

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(@Field("yemek_adi")yemek_adi:String,
                                @Field("yemek_resim_adi")yemek_resim_adi:String,
                                @Field("yemek_fiyat")yemek_fiyat:Int,
                                @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                                @Field("kullanici_adi") kullanici_adi:String): SepetCRUDCevap

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun  cartFoodYukle(@Field("kullanici_adi") kullanici_adi:String): SepetYemeklerCevap



    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(@Field("sepet_yemek_id") yemek_id:String,
                                @Field("kullanici_adi") kullanici_adi:String):SepetCRUDCevap
}