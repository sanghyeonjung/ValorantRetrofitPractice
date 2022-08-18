package com.example.retrofitstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitstudy.MainActivity.Companion.api_key
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    companion object {
        var BaseUrl = "https://kr.api.riotgames.com/"
        var api_key = "RGAPI-b5e28ca9-42a7-4b04-828f-e3972748a4b8"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit =
            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(RetrofitService::class.java)
        service.getValContent(api_key, "ko-KR")
            .enqueue(object : Callback<ContentDto> {
            override fun onResponse(call: Call<ContentDto>, response: Response<ContentDto>) {
                val valInfo = response.body()
                val valVersion = valInfo?.version
                val valCharacter = valInfo?.characters //arraylist type : ContentItemDto
                val valMap = valInfo?.maps
                valVersion?.let { Log.e("valVersion", it) }
                for (i in 0 until (valCharacter?.size ?: 0)) {
                    valCharacter?.get(i)?.name?.let { Log.e("valCharacter", it) }
                }
                for (i in 0 until (valMap?.size ?: 0)) {
                    valMap?.get(i)?.name?.let { Log.e("valMap", it) }
                }
            }

            override fun onFailure(call: Call<ContentDto>, t: Throwable) {
                Log.e("error", "fail to get data")
            }
        })
    }
}

interface RetrofitService {
    @GET("/val/content/v1/contents")
    fun getValContent(
        @Query("api_key") api_key: String,
        @Query("local") local: String
    ): Call<ContentDto>
}