package com.theshine.android.lites.ui.view.info.scale

import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.util.Event

class ScaleViewModel(
    private var petDataSource: PetDataSource
) : BaseViewModel() {

    val action: MutableLiveData<Event<scaleActions>> = MutableLiveData()

    fun next() {
        action.value = Event(scaleActions.NEXT)
    }

    fun goStore(){
        action.value = Event(scaleActions.STORE)
    }

    fun goCommunity(){
        action.value = Event(scaleActions.COMMUNITY)
    }


    enum class scaleActions {
        NEXT, STORE, COMMUNITY
    }
}