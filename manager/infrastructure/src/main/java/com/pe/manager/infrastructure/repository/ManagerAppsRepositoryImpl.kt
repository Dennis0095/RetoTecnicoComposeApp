package com.pe.manager.infrastructure.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.repository.ManagerAppsRepository
import com.pe.manager.infrastructure.di.LocalStorage
import com.pe.manager.infrastructure.di.LocalStorage.Companion.KEY_LIST_APPS
import kotlinx.coroutines.delay

class ManagerAppsRepositoryImpl(
    private val localStorage: LocalStorage
): ManagerAppsRepository {
    override suspend fun registerApp(registerApp: RegisterApp): Boolean {
        delay(2000)
        val listRegisteredApps = localStorage.getString(KEY_LIST_APPS)
        if(!listRegisteredApps.isNullOrEmpty()){
            Log.d("DMA_LECTOR", " ya existe = ")
            val gson = Gson()
            val type = object : TypeToken<ArrayList<RegisterApp>>() {}.type
            val arrayList: ArrayList<RegisterApp> = gson.fromJson(listRegisteredApps, type)
            val app = registerApp.copy(
                idApp = arrayList.last().idApp + 1
            )
            arrayList.add(app)

            val json = gson.toJson(arrayList)
            localStorage.putString(KEY_LIST_APPS, json)
        }else{
            Log.d("DMA_LECTOR", " primera vez = ")
            val gson = Gson()
            val arrayList = ArrayList<RegisterApp>()
            arrayList.add(registerApp)
            val json = gson.toJson(arrayList)
            localStorage.putString(KEY_LIST_APPS, json)
        }
        Log.d("DMA_LECTOR", " guardado = " + localStorage.getString(KEY_LIST_APPS))
        return true
    }
}