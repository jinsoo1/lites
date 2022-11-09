package com.theshine.android.lites.ui.view.main.home.main

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentHomeMainBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.home.bluetooth.PERMISSIONS
import com.theshine.android.lites.ui.view.main.home.bluetooth.REQUEST_ALL_PERMISSION
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeMainFragment: BaseVmFragment<FragmentHomeMainBinding>(
    R.layout.fragment_home_main,
    HomeMainViewModel::class.java
) {
    override val viewModel by lazy { vm as HomeMainViewModel }

    val activityViewModel by sharedViewModel<MainViewModel>()

    override fun initFragment() {

        Log.d("Fragment1", "Fragment")
        startLocationPermissionRequest()

    }


    private fun HomeMainViewModel.setObserves(){

        requestEnableBLE.observe(this@HomeMainFragment, Observer {
            it.getContentIfNotHandled()?.let {
                requestEnableBLE()
            }
        })

        _isScanning.observe(this@HomeMainFragment, Observer {
            it.getContentIfNotHandled()?.let{ scanning->
                viewModel.isScanning.set(scanning)
            }
        })

        _isConnect.observe(this@HomeMainFragment, Observer {
            it.getContentIfNotHandled()?.let{ connect->
                isConnect.set(connect)
            }
        })

        scanDevice.observe(this@HomeMainFragment, Observer {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("`위시켓`에서 사용자의\n블루투스 연동을 요청합니다.")
                .setMessage("체중계 사용을 위해서 위시켓에서 블루투스 연동을 요청합니다. 허용하지 않을 시 체중 정보가 사용자의 휴대전화에 저장되지 않습니다.")
                .setNegativeButton("취소"){ dialog, _ ->

                }
                .setPositiveButton("허용") { dialog, _ ->
                    connectDevice(it)

                }
                .show()
        })

        readTxt.observe(this@HomeMainFragment, Observer {
            Log.d("Central T", it)
            setBLEData(it)
        })

        myPet.observe(this@HomeMainFragment, Observer {
            activityViewModel.myPetSetting(it)
        })


    }


    private val requestEnableBleResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // do somthing after enableBleRequest
        }
    }
    private fun requestEnableBLE() {
        val bleEnableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        requestEnableBleResult.launch(bleEnableIntent)
    }

//    private fun hasPermissions(context: Context?, permissions: Array<String>): Boolean {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
//            for (permission in permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission)
//                    != PackageManager.PERMISSION_GRANTED) {
//                    Log.d("Central", "123")
//                    return false
//                }
//            }
//        }
//        return true
//    }
    // Permission check
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            Log.d("DEBUG", "${it.key} = ${it.value}")
        }
    }

    // Ex. Launching ACCESS_FINE_LOCATION permission.
    private fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(PERMISSIONS)
    }


    override fun onResume() {
        super.onResume()
        // finish app if the BLE is not supported
        viewModel.setObserves()
        Log.d("FragmentResume1", "Fragment")
        if (!requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            requireActivity().finish()
        }
    }

}