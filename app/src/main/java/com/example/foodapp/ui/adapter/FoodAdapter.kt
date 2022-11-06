package com.example.foodapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entities.Yemekler
import com.example.foodapp.databinding.MainCardDesignBinding
import com.example.foodapp.ui.fragment.FoodMainFragmentDirections
import com.example.foodapp.ui.viewModel.FoodMainViewModel
import com.google.android.material.snackbar.Snackbar

class FoodAdapter (var mContext: Context,
                   var yemekListesi:List<Yemekler>,
                   var viewModel: FoodMainViewModel
): RecyclerView.Adapter<FoodAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: MainCardDesignBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:MainCardDesignBinding
        init {
            this.tasarim = tasarim
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim:MainCardDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.main_card_design ,parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemekListesi.get(position)
        val t = holder.tasarim
        t.foodNesnesi = yemek

        Log.e("Yemek Ad:","${yemek.yemek_adi}")
        t.textViewFoodName.text="${yemek.yemek_adi}"
        t.textViewFoodPrice.text="${yemek.yemek_fiyat} ₺"

        var url ="http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).into(t.imageViewMain)

        t.imageViewMain.setOnClickListener {
           val gecis=FoodMainFragmentDirections.gecis2(detailYemek = yemek)
           Navigation.findNavController(it).navigate(gecis)
          //  Log.e("YEMEK tıkla","${yemek.kullanici_adi}")
        }
        var siparis_adet=0
        t.buttonSepeteEkle.setOnClickListener {
            Snackbar.make(it,"Sepetinize 1 ürün eklendi", Snackbar.LENGTH_SHORT).show()
            val yemek_ad=yemek.yemek_adi
            val yemek_resim_adi=yemek.yemek_resim_adi
            val yemek_fiyat=yemek.yemek_fiyat
            siparis_adet ++
            Log.e("yemek","$yemek_ad-$yemek_resim_adi-$yemek_fiyat-$siparis_adet-")
            viewModel.sepet()
            viewModel.sepeteYemekEkle(yemek_ad,yemek_resim_adi,yemek_fiyat,1,"semra_dnmz")
           // val y=SepetYemekler(,yemek_ad,yemek_resim_adi,yemek_fiyat,siparis_adet,"semra_dnmz")

        }
    }
    override fun getItemCount(): Int {
        return yemekListesi.size
    }
//    fun sepeteYemekEkle(yemek_ad:String,yemek_resim_adi:String,yemek_fiyat:Int,siparis_adet:Int,kullanici_ad:String){
//        Log.e("yemek","$yemek_ad-$yemek_resim_adi-$yemek_fiyat-$siparis_adet-$kullanici_ad")
//       viewModel.sepeteYemekEkle(yemek_ad,yemek_resim_adi,yemek_fiyat,siparis_adet,kullanici_ad)
//    }
}