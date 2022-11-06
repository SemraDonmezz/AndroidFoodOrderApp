package com.example.foodapp.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import kotlin.properties.ReadOnlyProperty

class AppDataStore(var context:Context) {
    val Context.ds:DataStore<Preferences> by preferencesDataStore("bilgi")
    companion object{
        val URUNFIYAT_KEY= intPreferencesKey("URUNFIYAT")
//        suspend fun kayitAd(ad:String){
//            context.ds.edit {
//                it[AD_KEY]=ad
//            }
//        }

    }


}