package com.theshine.android.lites.data.common

import android.util.Log
import androidx.databinding.ktx.BuildConfig
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.theshine.android.lites.BuildConfig.BASE_URL
import com.theshine.android.lites.data.common.interceptor.ErrorInterceptor
import com.theshine.android.lites.data.common.interceptor.HeaderInterceptor
import com.theshine.android.lites.data.remote.api.AuthApi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor(LoggerInterceptor()).apply {
            level = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single { HeaderInterceptor() }

    single { ErrorInterceptor() }

    single {
        GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<HeaderInterceptor>())
            .addInterceptor(get<ErrorInterceptor>())
            .build()
    }

    single { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) }

    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .baseUrl("https://ec2-43-200-215-225.ap-northeast-2.compute.amazonaws.com:5500")
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    //Api추가시에 한줄씩 추가
    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }

}

class LoggerInterceptor : HttpLoggingInterceptor.Logger{
    companion object {
        const val LOG_DIVIDER = "================================================================"
    }

    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun log(message: String) {
        val trimMessage = message.trim { it <= ' ' }
        if (trimMessage.startsWith("{") && trimMessage.endsWith("}")
            || trimMessage.startsWith("[") && trimMessage.endsWith("]")
        ) {
            try {
                val prettyPrintJson = gson.toJson(JsonParser().parse(message))
                Log.e("Network Response", LOG_DIVIDER + "\n" + prettyPrintJson + "\n" + LOG_DIVIDER)
            } catch (e: Exception) {
                Log.d("Network Response", message, null)
            }
        } else {
            Log.e("Network Response", message, null)
        }
    }
}