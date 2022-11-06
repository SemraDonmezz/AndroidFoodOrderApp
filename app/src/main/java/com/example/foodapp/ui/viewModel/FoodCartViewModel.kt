package com.example.foodapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.entities.SepetYemekler
import com.example.foodapp.data.entities.Yemekler
import com.example.foodapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCartViewModel @Inject constructor(var frepo: FoodRepository): ViewModel() {
    var sepetYemeklerListesi= MutableLiveData<List<SepetYemekler>>()
    init {
        cartFoodYukle(kullanici_adi = "semra_dnmz")
    }
    fun cartFoodYukle(kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            Log.e("cart view","a")
            sepetYemeklerListesi.value=frepo.cartFoodYukle(kullanici_adi)
        }
    }
    fun sepettenYemekSil(sepet_yemek_id:String,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.sepettenYemekSil(sepet_yemek_id,kullanici_adi)
            cartFoodYukle(kullanici_adi = "semra_dnmz")
        }
    }
}