package com.theshine.android.lites.util.ext

import com.theshine.android.lites.base.App
import com.theshine.android.lites.data.remote.model.DataResponse
import com.theshine.android.lites.data.remote.model.VoidResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.java.KoinJavaComponent
import retrofit2.HttpException
import retrofit2.Retrofit

fun <T> Single<T>.onUI(onNext: (T) -> Unit): Disposable {
    return this.onUI(onNext, DEFAULT_ERROR_HANDLE)
}

fun <T> Single<T>.onUI(onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {

    return this
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onNext, onError)
}

fun <T> Single<DataResponse<T>>.mapData() : Single<T> {
    return this.map { it.data }
}

fun <T> Single<DataResponse<T>>.mapVoidResponse() : Single<T> {
    return this.map { it.data }
}

fun <T> Single<T>.observeOnMainThread() : Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

private val DEFAULT_ERROR_HANDLE: (Throwable) -> Unit = {

    if (it is HttpException) {
        val error = try {
            KoinJavaComponent.get(Retrofit::class.java)
                .responseBodyConverter<VoidResponse>(
                    VoidResponse::class.java,
                    VoidResponse::class.java.annotations
                )
                .convert(it.response()?.errorBody())
        } catch (e: Exception) {
            null
        }

        App.toast(error?.message ?: it.localizedMessage)
    } else {
        val isNetworkConnected: Boolean = App.checkInternetConnection(App.appContext)
        if (!isNetworkConnected) {
            App.toast("인터넷 연결을 확인해주세요.")
        } else {
            it.printStackTrace()
        }
    }

}