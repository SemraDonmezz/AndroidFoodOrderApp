package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetail2Binding
import com.example.foodapp.ui.viewModel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detail2Fragment : Fragment() {
    private lateinit var tasarim:FragmentDetail2Binding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= DataBindingUtil.inflate(inflater,R.layout.fragment_detail2, container, false)
        tasarim.detail2Fragment=this

        val bundle:Detail2FragmentArgs by navArgs()
        val gelenYemek=bundle.detailYemek
        tasarim.foodDetail=gelenYemek

        Log.e("detay kullanıcı","${gelenYemek.yemek_adi}-${gelenYemek.yemek_fiyat}")
        tasarim.toolbarDetail="${gelenYemek.yemek_adi} Detay"
        tasarim.textViewDetailName.text="${gelenYemek.yemek_adi}"
        tasarim.textViewDetailPrice.text="${gelenYemek.yemek_fiyat} ₺"
        var url ="http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(400,400).into(tasarim.imageViewDetail)

        tasarim.textViewTotalAmouth.text="0 ₺"
        tasarim.textViewValue.text="0"
        tasarim.buttonDetailOnayla.setOnClickListener {
//            var yemek=ArrayList<SepetYemekler>()
//            val y1=SepetYemekler("",gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,tasarim.textViewValue.text.toString(),"semra_dnmz")
//            yemek.add(y1)

            val siparis_adet=tasarim.textViewValue.text.toString()
            val siparis=siparis_adet.toInt()
            Log.e("siparis adet detay","${siparis_adet}")
            if(siparis>0) {
                viewModel.sepeteYemekEkle(
                    gelenYemek.yemek_adi,
                    gelenYemek.yemek_resim_adi,
                    gelenYemek.yemek_fiyat,
                    siparis,
                    "semra_dnmz"
                )
                val gecis = Detail2FragmentDirections.detailToCardGecis()
                Navigation.findNavController(it).navigate(gecis)
            }else{
                Snackbar.make(it,"Ürün sayısı 0 olamaz!",Snackbar.LENGTH_SHORT).show()
            }
        }
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:DetailViewModel by viewModels()
        viewModel=tempViewModel
    }

    fun fabAddTikla(deger:String, yemekFiyat:Int):Int{
        Log.e("GİRDİ","M")
        var sayi=deger.toInt()
        if(sayi >= 0){
            sayi++
            tasarim.textViewValue.text=sayi.toString()
            TotalAmouth(sayi,yemekFiyat)
        }
        return sayi
    }
    fun fabMinusTikla(deger: String,yemekFiyat: Int):Int{
        Log.e("GİRDİ","Minus")
        var sayi=deger.toInt()
        if(sayi > 0){
            sayi--
            tasarim.textViewValue.text=sayi.toString()
            TotalAmouth(sayi,yemekFiyat)
        }else{
            tasarim.textViewValue.text="0"
            tasarim.textViewTotalAmouth.text="0 ₺"
        }
        return sayi
    }
    fun TotalAmouth(sayi: Int,yemekFiyat: Int){
        val toplamFiyat=yemekFiyat*sayi
        tasarim.textViewTotalAmouth.text="${toplamFiyat.toString()} ₺"
    }
}