package com.theshine.android.lites.ui.view.main.home.graph

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetGraphData
import com.theshine.android.lites.data.common.model.PetYearData
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo

class GraphViewModel(
    private val petDataSource: PetDataSource
) : BaseViewModel() {

    private val _action : MutableLiveData<Event<GraphAction>> = MutableLiveData()
    val action : LiveData<Event<GraphAction>> get() = _action

    private val _myPetName : MutableLiveData<String> = MutableLiveData()
    val myPetName : LiveData<String> get() = _myPetName

    private val _myPetData : MutableLiveData<List<PetGraphData>> = MutableLiveData()
    val myPetData : LiveData<List<PetGraphData>> get() = _myPetData

    private val _myPetYearData : MutableLiveData<List<PetYearData>> = MutableLiveData()
    val myPetYearData : LiveData<List<PetYearData>> = _myPetYearData



    fun getWeekPetData(){
        petDataSource.getWeekPetData()
            .map {
                it.map{
                    PetGraphData(
                        it.petToken,
                        it.weight,
                        it.createdAt,
                        it.wk_han
                    )
                }
            }
            .subscribe({
                _myPetData.value = it
                Log.d("getWeekPetData", it.toString())
                weekAction()
            },{
                Log.d("getWeekPetData E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    private fun getMonthPetData(){
        petDataSource.getMonthPetData()
            .map {
                it.map{
                    PetGraphData(
                        it.petToken,
                        it.weight,
                        it.createdAt,
                        it.wk_han
                    )
                }
            }
            .subscribe({
                _myPetData.value = it
                Log.d("getMonthPetData", it.toString())
                monthAction()
            },{
                Log.d("getMonthPetData E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    private fun getYearPetData(){
        petDataSource.getYearPetData()
            .map {
                it.map {
                    PetYearData(
                        it.petToken,
                        it.weight,
                        it.createdAt,
                        it.wk_han,
                        it.avg
                    )
                }
            }
            .subscribe({
                _myPetYearData.value = it
                Log.d("getYearPetData", it.toString())
                yearAction()
            },{
                Log.d("getYearPetData E ", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun setPetName(name : String){
        _myPetName.value = name
    }

    fun onClickWeek(){
        getWeekPetData()
    }

    fun onClickMonth(){
        getMonthPetData()
    }

    fun onClickYear(){
        getYearPetData()
    }

    private fun weekAction(){
        _action.value = Event(GraphAction.WEEK)
    }
    private fun monthAction(){
        _action.value = Event(GraphAction.MONTH)
    }

    private fun yearAction(){
        _action.value = Event(GraphAction.YEAR)
    }


    enum class GraphAction{
        WEEK, MONTH, YEAR
    }

}