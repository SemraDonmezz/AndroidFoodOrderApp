package com.example.foodapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entities.SepetYemekler
import com.example.foodapp.data.entities.Yemekler
import com.example.foodapp.databinding.CartCardDesignBinding
import com.example.foodapp.ui.AppDataStore
import com.example.foodapp.ui.viewModel.FoodCartViewModel

class FoodCartAdapter (var mContext: Context,
                       var sepetYemekListesi:List<SepetYemekler>,
                       var viewModel: FoodCartViewModel
): RecyclerView.Adapter<FoodCartAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CartCardDesignBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CartCardDesignBinding

        init {
            this.tasarim = tasarim
          //  viewModel.cartFoodYukle("semra_dnmz")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim: CartCardDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.cart_card_design, parent, false
        )
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemekListesi.get(position)
        val t = holder.tasarim
        t.foodCartNesnesi = sepetYemek


        t.textViewSepetIsim.text = sepetYemek.yemek_adi
        t.textViewFiyat.text = "Urün fiyat: ${sepetYemek.yemek_fiyat} ₺"
        t.textViewSiparis.text="Ürün Adet: ${sepetYemek.yemek_siparis_adet.toInt()}"

      //  Log.e("cart yemek", "${sepetYemek.sepet_yemek_id}-${sepetYemek.yemek_adi}")
        var url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(200, 200).into(t.imageViewSepet)
        t.imageViewDelete.setOnClickListener {
            sepettenYemekSil(sepetYemek.sepet_yemek_id, sepetYemek.kullanici_adi)
        }

    }
    override fun getItemCount(): Int {
        return sepetYemekListesi.size
    }
    fun sepettenYemekSil(sepet_yemek_id: String, kullanici_adi: String) {
        Log.e("yemek", "$sepet_yemek_id-$kullanici_adi")
        viewModel.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
    }

}