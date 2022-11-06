package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFoodMainBinding
import com.example.foodapp.ui.adapter.FoodAdapter
import com.example.foodapp.ui.viewModel.FoodMainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodMainFragment : Fragment() {
    private lateinit var tasarim:FragmentFoodMainBinding
    private  lateinit var  viewModel:FoodMainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim= DataBindingUtil.inflate(inflater,R.layout.fragment_food_main, container, false)
        tasarim.foodMainFragment=this
        tasarim.toolbarMainTitle="Yemekler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarMain)

        Log.e("yemek","main acıldı")
        tasarim.rvMain.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            if ( it != null ) {
                val adapter = FoodAdapter(requireContext(),it,viewModel)
                tasarim.rvMain.adapter=adapter

            }else{
               Snackbar.make(tasarim.imageView,"ürün yok",Snackbar.LENGTH_SHORT).show()
            }
        }
        return tasarim.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:FoodMainViewModel by viewModels()
        viewModel = tempViewModel
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.mainFoodYukle()
//    }
}