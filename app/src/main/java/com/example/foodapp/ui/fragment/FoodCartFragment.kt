package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entities.SepetYemekler
import com.example.foodapp.databinding.FragmentFoodCartBinding
import com.example.foodapp.ui.adapter.FoodAdapter
import com.example.foodapp.ui.adapter.FoodCartAdapter
import com.example.foodapp.ui.viewModel.FoodCartViewModel
import com.example.foodapp.ui.viewModel.FoodMainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodCartFragment : Fragment() {
    private  lateinit var tasarim:FragmentFoodCartBinding
    private lateinit var viewModel:FoodCartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim=DataBindingUtil.inflate(inflater,R.layout.fragment_food_cart, container, false)
        tasarim.foodCartFragment=this
        tasarim.toolbarCart.title="Sepetim"

        var toplam=0
        val gecis=FoodCartFragmentDirections.foodCartToCart()
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { list:List<SepetYemekler> ->
            if (list != null) {
                val aggregate = list.groupingBy(SepetYemekler::yemek_adi)
                    .aggregate { _, accumulator: SepetYemekler?, element: SepetYemekler, _ ->
                        accumulator?.let {
                            it.copy(
                                 yemek_fiyat = it.yemek_fiyat + (element.yemek_fiyat * element.yemek_siparis_adet.toInt()),
                                 yemek_siparis_adet = (it.yemek_siparis_adet.toInt()+ element.yemek_siparis_adet.toInt()).toString()
                            )
                        } ?: element
                    }
//                for(x in aggregate.values.toList() ){
//                    for(i in it){
//                        if(x.yemek_adi==i.yemek_adi){
//                            x.yemek_fiyat=x.yemek_fiyat+i.yemek_fiyat*i.yemek_siparis_adet.toInt()
//                            x.yemek_siparis_adet=(x.yemek_siparis_adet.toInt()+i.yemek_siparis_adet.toInt()).toString()
//                        }
//                    }
//                }
                val adapter = FoodCartAdapter(requireContext(), aggregate.values.toList(), viewModel)
                tasarim.rvCart.adapter = adapter

               toplam=sumTotalPrice(aggregate.values.toList())
                tasarim.textViewSepetToplam.text="Toplam: ${toplam} ₺  "
                val item=aggregate.values.toList()
                tasarim.buttonSepetOnayla.setOnClickListener {
                    Snackbar.make(it,"Sepetiniz Onaylandı",Snackbar.LENGTH_LONG).show()
                    cartDelete( list )
                    //tasarim.rvCart.visibility=View.GONE
                    Navigation.findNavController(it).navigate(gecis)
                }
            }

            {
//                val adapter = FoodCartAdapter(requireContext(),list, viewModel)
//                tasarim.rvCart.adapter = adapter
            }
        }
        return tasarim.root
    }

    fun cartDelete(list: List<SepetYemekler>){
        for(item in list){
                viewModel.sepettenYemekSil(item.sepet_yemek_id,item.kullanici_adi)
        }
    }
    fun sumTotalPrice(list: List<SepetYemekler>):Int{
        var sum=0
        for(item in list){
            sum=sum+item.yemek_fiyat
        }
        return sum
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodCartViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onResume() {
        super.onResume()
        viewModel.cartFoodYukle("semra_dnmz" )
    }
}