package com.application.nasapicturesapp.viewmodel


import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.respository.local.NasaAppDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.lang.reflect.Type


class MainGridModel(application: Application) : AndroidViewModel(application), LifecycleOwner {

    private val TAG = MainGridModel::class.simpleName
    var bgScope = CoroutineScope(Dispatchers.IO)

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    @SuppressLint("StaticFieldLeak")
    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    private var db: NasaAppDb
    var allPictures = MutableLiveData<List<PicturesModel>>()

    init {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        db = NasaAppDb.invoke(application)

        db.picturesDao().getAllPictures().observe(this, Observer {
            if (it.isNullOrEmpty()) {
                generateDataFromRaw()
            } else {
                allPictures.postValue(it)

            }

        })


    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onCleared() {
        super.onCleared()
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    private fun generateDataFromRaw() {
        val ip: InputStream = context.resources.openRawResource(R.raw.data)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(ip, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                ip.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val jsonString: String = writer.toString()
        Log.d(TAG, "JSON $jsonString")
        val gson = Gson()
        val listType: Type = object : TypeToken<List<PicturesModel?>?>() {}.type
        val picturesData: List<PicturesModel> =
            gson.fromJson(jsonString, listType)

        bgScope.launch {
            db.picturesDao().addAllPictures(picturesData)
        }


    }

}