package com.example.foodapp.data.repository

import android.util.Log
import com.example.foodapp.data.dataSource.FoodDataSource
import com.example.foodapp.data.entities.SepetYemekler
import com.example.foodapp.data.entities.Yemekler

class FoodRepository(var fds:FoodDataSource) {

    suspend fun mainFoodYukle() : List<Yemekler> = fds.mainFoodYukle()


    suspend fun sepeteYemekEkle( yemek_adi:String,yemek_resim_adi:String,
                                 yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_ad:String) =
        fds.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_ad)

    suspend fun sepettenYemekSil(sepet_yemek_id:String,kullanici_adi:String) =
        fds.sepettenYemekSil(sepet_yemek_id,kullanici_adi)

    suspend fun cartFoodYukle( kullanici_adi:String): List<SepetYemekler> =
        fds.cartFoodYukle(kullanici_adi)

}