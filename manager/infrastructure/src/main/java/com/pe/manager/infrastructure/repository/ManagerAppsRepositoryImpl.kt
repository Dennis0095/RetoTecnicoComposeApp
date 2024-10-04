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
) : ManagerAppsRepository {
    override suspend fun registerApp(registerApp: RegisterApp): Boolean {
        delay(2000)
        val listRegisteredApps = localStorage.getString(KEY_LIST_APPS)
        if (!listRegisteredApps.isNullOrEmpty()) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<RegisterApp>>() {}.type
            val arrayList: ArrayList<RegisterApp> = gson.fromJson(listRegisteredApps, type)


            val recommendations = StringBuilder()
            arrayList.forEach { app ->
                val commonActivities = registerApp.keyActivities.filter { activity ->
                    app.keyActivities.contains(activity)
                }

                if (commonActivities.isNotEmpty()) {
                    val commonActivitiesString = commonActivities.map { it.toString() }
                    recommendations.append("La actividad ${commonActivitiesString.joinToString("")} ya es realizada por la aplicación '${app.nameApp}'.\n")
                }
            }

            val recommend = if (recommendations.isNotEmpty()) {
                recommendations.toString()
            }else ""

            val newApp = registerApp.copy(
                idApp = arrayList.last().idApp + 1,
                recommendations = registerApp.recommendations + "\n - "+recommend
            )
            arrayList.add(newApp)

            val json = gson.toJson(arrayList)
            localStorage.putString(KEY_LIST_APPS, json)
        } else {
            val gson = Gson()
            val arrayList = ArrayList<RegisterApp>()
            arrayList.add(registerApp)
            val json = gson.toJson(arrayList)
            localStorage.putString(KEY_LIST_APPS, json)
        }
        return true
    }

    override suspend fun updateApp(appUpdate: RegisterApp): Boolean {
        delay(2000)
        val listRegisteredApps = localStorage.getString(KEY_LIST_APPS)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<RegisterApp>>() {}.type
        val arrayList: ArrayList<RegisterApp> = gson.fromJson(listRegisteredApps, type)

        val recommendations = StringBuilder()
        val newArrayList = arrayList.filter { it.idApp != appUpdate.idApp }
        newArrayList.forEach { app ->
            val commonActivities = appUpdate.keyActivities.filter { activity ->
                app.keyActivities.contains(activity)
            }

            if (commonActivities.isNotEmpty()) {
                val commonActivitiesString = commonActivities.map { it.toString() }
                recommendations.append("La actividad ${commonActivitiesString.joinToString("")} ya es realizada por la aplicación '${app.nameApp}'.\n")
            }
        }

        val recommend = if (recommendations.isNotEmpty()) {
            recommendations.toString()
        }else appUpdate.recommendations

        val appUpdateRecommendation = appUpdate.copy(
            recommendations = appUpdate.recommendations + "\n - "+recommend
        )
        //arrayList.add(appUpdateRecommendation)



        val index = arrayList.indexOfFirst { it.idApp == appUpdateRecommendation.idApp }
        if (index != -1) {
            arrayList[index] = appUpdateRecommendation
        }
        val json = gson.toJson(arrayList)
        localStorage.putString(KEY_LIST_APPS, json)
        return true
    }

    override suspend fun getApps(): ArrayList<RegisterApp> {
        val listRegisteredApps = localStorage.getString(KEY_LIST_APPS)

        return if (!listRegisteredApps.isNullOrEmpty()) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<RegisterApp>>() {}.type
            val arrayList: ArrayList<RegisterApp> = gson.fromJson(listRegisteredApps, type)
            arrayList
        } else {
            arrayListOf()
        }
    }

    override suspend fun getApp(id: Int): RegisterApp? {
        delay(500)
        val listRegisteredApps = localStorage.getString(KEY_LIST_APPS)

        return if (!listRegisteredApps.isNullOrEmpty()) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<RegisterApp>>() {}.type
            val arrayList: ArrayList<RegisterApp> = gson.fromJson(listRegisteredApps, type)
            arrayList.filter { it.idApp == id }.first()
        } else {
            null
        }
    }

    override suspend fun deleteApp(id: Int): Boolean {
        delay(2000)
        val listRegisteredApps = localStorage.getString(KEY_LIST_APPS)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<RegisterApp>>() {}.type
        val arrayList: ArrayList<RegisterApp> = gson.fromJson(listRegisteredApps, type)
        val newArray = arrayList.filter { it.idApp != id }

        val json = gson.toJson(newArray)
        localStorage.putString(KEY_LIST_APPS, json)
        return true
    }
}