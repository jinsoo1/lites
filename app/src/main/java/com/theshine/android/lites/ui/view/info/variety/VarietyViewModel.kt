package com.theshine.android.lites.ui.view.info.variety

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event

class VarietyViewModel : BaseViewModel() {

    val action: MutableLiveData<Event<VarietyActions>> = MutableLiveData()

    private val _dogVariety : MutableLiveData<List<VarietyData>> = MutableLiveData(listOf())
    val dogVariety : MutableLiveData<List<VarietyData>> get() = _dogVariety

    private val _varietySelect : MutableLiveData<Event<VarietyData>> = MutableLiveData()
    val varietySelect : MutableLiveData<Event<VarietyData>> get() = _varietySelect

    private val _genderSelect : MutableLiveData<Boolean> = MutableLiveData(false)
    val genderSelect : MutableLiveData<Boolean> get() = _genderSelect

    private val _neutralizationSelect : MutableLiveData<Boolean> = MutableLiveData(false)
    val neutralizationSelect : MutableLiveData<Boolean> get() = _neutralizationSelect

    val nextState : MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        var varietyData : MutableList<VarietyData> = mutableListOf()
        dog_variety.forEach {
            varietyData.add(VarietyData(it))
        }
        _dogVariety.value = varietyData
    }

    fun next(){
        action.value = Event(VarietyActions.NEXT)
    }

    fun clickFemale(){
        action.value = Event(VarietyActions.FEMALE)

    }

    fun clickMale(){
        action.value = Event(VarietyActions.MALE)

    }

    fun clickO(){
        action.value = Event(VarietyActions.O)

    }

    fun clickX(){
        action.value = Event(VarietyActions.X)

    }

    fun selectedGender(){
        _genderSelect.value = true
    }

    fun selectedNeutralizationSelect(){
        _neutralizationSelect.value = true
    }

    fun selectedItem(item : VarietyData){
        _varietySelect.value = Event(item)
        _dogVariety.value?.forEach {
            it.isChecked.set(false)
        }

        item.isChecked.set(true)

    }

    enum class VarietyActions {
        NEXT, FEMALE, MALE, O, X
    }


    companion object{
        @JvmStatic
        @BindingAdapter("bindVarietyList")
        fun bindVarietyList(rv : RecyclerView, list : List<VarietyData>){
            val adapter = rv.adapter as VarietyListAdapter
            adapter.let {
                it.updateItems(list)
            }
        }

    }
}