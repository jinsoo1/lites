package com.theshine.android.lites.ui.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.model.response.PetResponse
import com.theshine.android.lites.data.remote.source.PetDataSource
import io.reactivex.rxkotlin.addTo

class MainViewModel(
    private val petDataSource: PetDataSource
) : BaseViewModel() {

    val dd : MutableLiveData<PetResponse> = MutableLiveData()


    init {
        petDataSource.getMyPet()
            .subscribe({
                Log.d("getMyPet", it.toString())
                dd.value = it
            },{
                Log.d("getMyPet E ", it.toString())
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }
}