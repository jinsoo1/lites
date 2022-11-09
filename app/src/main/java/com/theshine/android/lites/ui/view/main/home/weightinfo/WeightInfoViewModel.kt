package com.theshine.android.lites.ui.view.main.home.weightinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetInfo
import com.theshine.android.lites.data.common.model.PetWeight
import com.theshine.android.lites.data.remote.source.PetDataSource
import io.reactivex.rxkotlin.addTo
import kotlin.math.roundToInt

class WeightInfoViewModel(
    private val petDataSource: PetDataSource
): BaseViewModel() {

    private val _petToken : MutableLiveData<String?> = MutableLiveData()
    val petToken : LiveData<String?> get() = _petToken

    private val _bcs : MutableLiveData<Int?> = MutableLiveData()
    val bcs : LiveData<Int?> get() = _bcs

    private val _propreWeight : MutableLiveData<Double> = MutableLiveData(0.0)
    val propreWeight : LiveData<Double> get() = _propreWeight

    private val _myPetWeight : MutableLiveData<PetWeight> = MutableLiveData()
    val myPetWeight : LiveData<PetWeight> get() = _myPetWeight

    fun getMyPetWeight(petToken : String?){
        if(petToken == null ){
            toast("대표 반려동물 정보가 없습니다.")
            return
        }
        petDataSource.getMyPetWeight(petToken)
            .subscribe({

                _myPetWeight.value = PetWeight(
                    it.petToken,
                    it.weight,
                    it.createdAt,
                    it.name,
                    it.bcs
                )
            },{
                Log.d("getMyPetWeight E", it.toString())
            })
            .addTo(compositeDisposable)

    }


    fun setMyPetToken(petInfo : PetInfo?){
        _petToken.value = petInfo?.petToken
        _bcs.value = petInfo?.bcs
    }

    fun getBCSData(){
        val first : Double? = bcs.value?.minus(5)?.toDouble()
        val second : Double? = first?.times(10)
        val third : Double? = second?.plus(100)
        val fourth : Double  = third.let { 100.div(it!!) }
        val fifth : Double  = fourth.times(myPetWeight.value!!.weight)

        _propreWeight.value = "%.2f".format(fifth).toDouble()
        Log.d("getBCSData", fifth.toString())
    }
}