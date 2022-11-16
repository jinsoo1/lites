package com.theshine.android.lites.ui.view.main.home.main

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.PetData
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.ui.view.main.home.bluetooth.BleRepository
import com.theshine.android.lites.util.Event
import io.reactivex.rxkotlin.addTo
import java.util.ArrayList

class HomeMainViewModel(
    private val bleRepository: BleRepository,
    private val petDataSource: PetDataSource
) : BaseViewModel() {

//    val statusTxt: LiveData<String>
//        get() = bleRepository.fetchStatusText().asLiveData(viewModelScope.coroutineContext)
    val readTxt: LiveData<String>
        get() = bleRepository.fetchReadText().asLiveData(viewModelScope.coroutineContext)
    val scanDevice : LiveData<BluetoothDevice?>
        get() = bleRepository.fetchDevice().asLiveData(viewModelScope.coroutineContext)
    val isAndroid10 : LiveData<Boolean>
        get() = bleRepository.fetchAndroid10().asLiveData(viewModelScope.coroutineContext)

    //ble adapter
    private val bleAdapter: BluetoothAdapter?
        get() = bleRepository.bleAdapter


    val requestEnableBLE : LiveData<Event<Boolean>>
        get() = bleRepository.requestEnableBLE
    val listUpdate : LiveData<Event<ArrayList<BluetoothDevice>?>>
        get() = bleRepository.listUpdate

    val _isScanning: LiveData<Event<Boolean>>
        get() = bleRepository.isScanning
    var isScanning = ObservableBoolean(false)
    val _isConnect: LiveData<Event<Boolean>>
        get() = bleRepository.isConnect
    var isConnect = ObservableBoolean(false)

    private val _weight : MutableLiveData<String> = MutableLiveData("0.0")
    val weight : LiveData<String> get() = _weight
    private val _temperature : MutableLiveData<String> = MutableLiveData()
    val temperature : LiveData<String> get() = _temperature
    private val _humidity : MutableLiveData<String> = MutableLiveData()
    val humidity : LiveData<String> get() = _humidity


    private val _myPet : MutableLiveData<PetData> = MutableLiveData()
    val myPet : LiveData<PetData> get() = _myPet

    init {
        petDataSource.getMyPet()
            .subscribe({
                Log.d("getMyPet ", it.toString())
                _myPet.value = PetData(
                    it.petToken,
                    it.type,
                    it.name,
                    it.birth,
                    it.variety,
                    it.gender,
                    it.neutering,
                    it.bcs,
                    it.profileImg,
                    it.moisture,
                    it.protein,
                    it.fat,
                    it.fiber,
                    it.ash
                )
            },{
                Log.d("getMyPet E ", it.toString())
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }

    /**
     *  Start BLE Scan
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun onClickScan(){
        Log.d("Central", "onClick")
        bleRepository.startScan()
    }
    fun onClickDisconnect(){
        bleRepository.disconnectGattServer()
    }
    fun connectDevice(bluetoothDevice: BluetoothDevice?){
        bleRepository.connectDevice(bluetoothDevice)
    }

    fun setBLEData(item : String){
        val data = item.split(",")
        _weight.value = data[0].replace("W:", "")
        _temperature.value = data[1]
        _humidity.value = data[2]

        savePetWeight(weight.value!!.toDouble())

    }


    private fun savePetWeight(weight : Double){
        petDataSource.savePetWeight(myPet.value!!.petToken, weight)
            .subscribe({
                Log.d("savePetWeight", it.toString())
            },{
                Log.d("savePetWeightE", it.toString())
            })
            .addTo(compositeDisposable)
    }


    fun onClickWrite(){

        val cmdBytes = ByteArray(1)
        cmdBytes[0] = 'g'.code.toByte()
//        cmdBytes[1] = 2
        if(isConnect.get()) {
            bleRepository.writeData(cmdBytes)
        }

    }

    fun setting0(){
        val cmdBytes = ByteArray(1)
        cmdBytes[0] = 's'.code.toByte()
//        cmdBytes[1] = 2
        if(isConnect.get()) {
            bleRepository.writeData(cmdBytes)
        }
    }


}