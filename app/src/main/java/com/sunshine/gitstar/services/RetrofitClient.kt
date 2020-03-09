package com.sunshine.gitstar.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    private var mRetrofitClient: Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit
    {
        if(mRetrofitClient == null)
        {
            synchronized(RetrofitClient::class.java)
            {
                if(mRetrofitClient == null)
                {
                    mRetrofitClient = Retrofit.Builder().baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
        return mRetrofitClient!!
    }
}