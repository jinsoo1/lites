package com.theshine.android.lites.ui.view.main.home.bluetooth

import android.Manifest
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.App
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.ui.view.main.home.bluetooth.util.BluetoothUtils
import com.theshine.android.lites.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import kotlin.concurrent.schedule

class BleRepository {

    private val TAG = "Central"

    // ble manager
    val bleManager: BluetoothManager =
        App.appContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    // ble adapter
    val bleAdapter: BluetoothAdapter?
        get() = bleManager.adapter

    // ble Gatt
    private var bleGatt: BluetoothGatt? = null

    // scan results
    var scanResults: ArrayList<BluetoothDevice>? = ArrayList()
    var scanDevice : BluetoothDevice? = null

//    var statusTxt: String = ""
    var txtRead: String = ""

//    var isStatusChange: Boolean = false
    var isTxtRead: Boolean = false
    var isDevice : Boolean = false
    fun fetchReadText() = flow {
        while (true) {
            coroutineScope {
                kotlinx.coroutines.delay(300)
            }
            if (isTxtRead) {
                emit(txtRead)
                isTxtRead = false
            }
        }
    }.flowOn(Dispatchers.Default)

//    fun fetchStatusText() = flow {
//        while (true) {
//            if (isStatusChange) {
//                emit(statusTxt)
//                isStatusChange = false
//            }
//        }
//    }.flowOn(Dispatchers.Default)

    fun fetchDevice() = flow {
        while (true) {
            coroutineScope {
                kotlinx.coroutines.delay(300)
            }
            if (isDevice) {
                emit(scanDevice)
                isDevice = false
            }
        }
    }.flowOn(Dispatchers.Default)


    val requestEnableBLE = MutableLiveData<Event<Boolean>>()
    val isScanning = MutableLiveData(Event(false))
    val isConnect = MutableLiveData(Event(false))
    val listUpdate = MutableLiveData<Event<ArrayList<BluetoothDevice>?>>()
    val scrollDown = MutableLiveData<Event<Boolean>>()


    @RequiresApi(Build.VERSION_CODES.M)
    fun startScan() {
        // check ble adapter and ble enabled
        if (bleAdapter == null || !bleAdapter?.isEnabled!!) {
            requestEnableBLE.postValue(Event(true))
//            statusTxt = "Scanning Failed: ble not enabled"
//            isStatusChange = true
            return
        }
        //scan filter
        val filters: MutableList<ScanFilter> = ArrayList()
        val scanFilter: ScanFilter = ScanFilter.Builder()
            .setDeviceName("LITES")
            .build()
        filters.add(scanFilter)
        // scan settings
        // set low power scan mode
        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
            .setReportDelay(0)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .build()
        // start scan
        if (ActivityCompat.checkSelfPermission(
                App.appContext,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            Log.d(TAG, "PERMISSION_GRANTED")
            pairedDeviceList()
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        bleAdapter?.bluetoothLeScanner?.startScan(filters, settings, BLEScanCallback)
        //bleAdapter?.bluetoothLeScanner?.startScan(BLEScanCallback)

//        statusTxt = "Scanning...."
//        isStatusChange = true
        isScanning.postValue(Event(true))
        Log.d(TAG, "BLE Scan!")
        Timer("SettingUp", false).schedule(3000) { stopScan() }
    }

    fun stopScan() {
        if (ActivityCompat.checkSelfPermission(
                App.appContext,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        bleAdapter?.bluetoothLeScanner?.stopScan(BLEScanCallback)
        isScanning.postValue(Event(false))
//        statusTxt = "Scan finished. Click on the name to connect to the device."
//        isStatusChange = true


        scanResults = ArrayList() //list 초기화
        Log.d(TAG, "BLE Stop!")
    }

    /**
     * BLE Scan Callback
     */
    private val BLEScanCallback =
        object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                super.onScanResult(callbackType, result)
                if (ActivityCompat.checkSelfPermission(
                        App.appContext,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
//                 TODO: Consider calling
//                    ActivityCompat#requestPermissions
//                 here to request the missing permissions, and then overriding
//                   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                          int[] grantResults)
//                 to handle the case where the user grants the permission. See the documentation
//                 for ActivityCompat#requestPermissions for more details.
                    return
                }
                Log.d(TAG, "Remote device name: " + result.device.name)
                if(result.device.name == "LITES"){
                    //connectDevice(result.device)
                    scanDevice = result.device
                    isDevice = true
                }
                stopScan()

                addScanResult(result)
            }

            override fun onBatchScanResults(results: List<ScanResult>) {
                for (result in results) {
                    addScanResult(result)
                }
            }

            override fun onScanFailed(_error: Int) {
                Log.d(TAG, "BLE scan failed with code $_error")
//                statusTxt = "BLE scan failed with code $_error"
//                isStatusChange = true
            }

            /**
             * Add scan result
             */
            private fun addScanResult(result: ScanResult) {
                // get scanned device
                val device = result.device
                // get scanned device MAC address
                val deviceAddress = device.address
                val deviceName = if (ActivityCompat.checkSelfPermission(
                        App.appContext,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                } else device.name


                // add the device to the result list
                for (dev in scanResults!!) {
                    if (dev.address == deviceAddress) return
                }
                scanResults?.add(result.device)
                // log
//                statusTxt = "add scanned device: $deviceAddress"
//                isStatusChange = true
                listUpdate.postValue(Event(scanResults))
            }
        }

    /**
     * BLE gattClientCallback
     */
    private val gattClientCallback: BluetoothGattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (status == BluetoothGatt.GATT_FAILURE) {
                disconnectGattServer()
                return
            } else if (status != BluetoothGatt.GATT_SUCCESS) {
                disconnectGattServer()
                return
            }
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // update the connection status message

//                statusTxt = "Connected"
//                isStatusChange = true
                Log.d(TAG, "Connected to the GATT server")
                if (ActivityCompat.checkSelfPermission(
                        App.appContext,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                disconnectGattServer()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)

            // check if the discovery failed
            if (status != BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "Device service discovery failed, status: $status")
                return
            }
            // log for successful discovery
            Log.d(TAG, "Services discovery is successful")
            isConnect.postValue(Event(true))
            // find command characteristics from the GATT server
            val respCharacteristic = gatt?.let { BluetoothUtils.findResponseCharacteristic(it) }
            // disconnect if the characteristic is not found
            if (respCharacteristic == null) {
                Log.e(TAG, "Unable to find cmd characteristic")
                disconnectGattServer()
                return
            }
            if (ActivityCompat.checkSelfPermission(
                    App.appContext,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            gatt.setCharacteristicNotification(respCharacteristic, true)
            // UUID for notification
            val descriptor: BluetoothGattDescriptor = respCharacteristic.getDescriptor(
                UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG)
            )
            descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            gatt.writeDescriptor(descriptor)
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            //Log.d(TAG, "characteristic changed: " + characteristic.uuid.toString())
            readCharacteristic(characteristic)
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "Characteristic written successfully")
            } else {
                Log.e(TAG, "Characteristic write unsuccessful, status: $status")
                disconnectGattServer()
            }
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "Characteristic read successfully")
                readCharacteristic(characteristic)
            } else {
                Log.e(TAG, "Characteristic read unsuccessful, status: $status")
                // Trying to read from the Time Characteristic? It doesnt have the property or permissions
                // set to allow this. Normally this would be an error and you would want to:
                // disconnectGattServer()
            }
        }

        /**
         * Log the value of the characteristic
         * @param characteristic
         */

        private fun readCharacteristic(characteristic: BluetoothGattCharacteristic) {

            val msg = characteristic.getStringValue(0)

            txtRead = msg
            isTxtRead = true

            Log.d(TAG, "read!!!!: $msg")
        }


    }

    /**
     * Connect to the ble device
     */
    fun connectDevice(device: BluetoothDevice?) {
        // update the status
//        statusTxt = "Connecting to ${device?.address}"
//        isStatusChange = true

        bleGatt = device?.connectGatt(App.appContext, false, gattClientCallback)
    }


    /**
     * Disconnect Gatt Server
     */
    fun disconnectGattServer() {
        Log.d(TAG, "Closing Gatt connection")
        // disconnect and close the gatt
        if (bleGatt != null) {
            bleGatt!!.disconnect()
            bleGatt!!.close()
//            statusTxt = "Disconnected"
//            isStatusChange = true
            isConnect.postValue(Event(false))
        }
    }

    fun writeData(cmdByteArray: ByteArray) {
        val cmdCharacteristic = BluetoothUtils.findCommandCharacteristic(bleGatt!!)
        // disconnect if the characteristic is not found
//        if (cmdCharacteristic == null) {
//            Log.e(TAG, "Unable to find cmd characteristic")
//            disconnectGattServer()
//            return
//        }

        cmdCharacteristic?.value = cmdByteArray
        Log.d(TAG, cmdByteArray.toString())
        val success: Boolean = if (ActivityCompat.checkSelfPermission(
                App.appContext,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            Log.d(TAG, "PERMISSION_GRANTED")
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        } else {
            bleGatt!!.writeCharacteristic(cmdCharacteristic)
        }
        // check the result
        if (!success) {
            Log.e(TAG, "Failed to write command")
        }
    }

    private lateinit var m_pairedDevices: Set<BluetoothDevice>

    private fun pairedDeviceList() {

        m_pairedDevices = bleAdapter!!.bondedDevices
        val deviceList: ArrayList<BluetoothDevice> = ArrayList()
        val nameList: ArrayList<String> = ArrayList()

        if (!m_pairedDevices.isEmpty()) {
            for (device: BluetoothDevice in m_pairedDevices) {
                deviceList.add(device)
                nameList.add(device.name + "(" + device.address + ")")
                Log.d(TAG, "" + device.name)
                scanResults?.add(device)
//                statusTxt = "add scanned device: ${device.address}"
//                isStatusChange = true
                listUpdate.postValue(Event(scanResults))
            }
        } else {
            toast("페어링된 장치를 찾을 수 없습니다.")
        }


    }

}