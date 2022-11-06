package com.example.foodapp.data.dataSource

import android.util.Log
import com.example.foodapp.data.entities.SepetYemekler
import com.example.foodapp.data.entities.Yemekler
import com.example.foodapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao: FoodDao) {
    suspend fun mainFoodYukle() : List<Yemekler> =
        withContext(Dispatchers.IO){
            //Log.e("yemek datasource","")
            fdao.mainFoodYukle().yemekler
        }
    suspend fun sepeteYemekEkle( yemek_adi:String,yemek_resim_adi:String, yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_ad:String,) {
        //Log.e("YEMEK data","${yemek_adi}-${yemek_fiyat}-${yemek_resim_adi}-${yemek_siparis_adet}-${kullanici_ad}")
        fdao.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_ad).success
        //Log.e("yemek datasourc sepet","${fdao.sepeteYemekEkle(yemek_adi,yemek_resim_adi, yemek_fiyat,yemek_siparis_adet,kullanici_ad).success}")
    }

    suspend fun sepettenYemekSil(sepet_yemek_id:String,kullanici_adi:String) = fdao.sepettenYemekSil(sepet_yemek_id, kullanici_adi)


    suspend fun cartFoodYukle( kullanici_adi:String): List<SepetYemekler> =
        withContext(Dispatchers.IO){
           // Log.e("yemek  sepet","${fdao.cartFoodYukle(kullanici_adi).sepet_yemekler}")
            fdao.cartFoodYukle("semra_dnmz").sepet_yemekler
        }

}