package com.example.foodapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(var frepo: FoodRepository):ViewModel() {
fun sepet(){
    CoroutineScope(Dispatchers.Main).launch(){
        Log.e("detay to view gecti","gecti")
    }

}
    fun sepeteYemekEkle( yemek_adi:String,yemek_resim_adi:String, yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_ad:String){
        CoroutineScope(Dispatchers.Main).launch {
            Log.e("detay to view gecti","$yemek_siparis_adet-$kullanici_ad")
            frepo.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_ad)
        }
    }
}
