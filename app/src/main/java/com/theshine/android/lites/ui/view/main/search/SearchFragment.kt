package com.theshine.android.lites.ui.view.main.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SearchData
import com.theshine.android.lites.databinding.FragmentSearchBinding
import com.theshine.android.lites.databinding.ItemSearchAnimalBinding
import com.theshine.android.lites.ui.view.info.InfoActivity
import com.theshine.android.lites.ui.view.main.MainActivity
import com.theshine.android.lites.ui.view.main.search.detail.SearchDetailActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class SearchFragment : BaseVmFragment<FragmentSearchBinding>(
    R.layout.fragment_search,
    SearchViewModel::class.java
) {
    override val viewModel by lazy { vm as SearchViewModel }


    private var mLocationRequest: LocationRequest? = null
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity().applicationContext)
    }

    override fun initFragment() {

        startLocationPermissionRequest()


        binding.apply {
            rvHospitalList.apply {
                adapter = AnimalHospitalAdapter(viewModel)

                addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val lastVisibleItemPosition =
                            (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                        val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                        if(viewModel.searchData.value!!.size == 15 || viewModel.searchData.value!!.size == 30){
                            if(lastVisibleItemPosition == itemTotalCount){
                                if(viewModel.page.value!! < 3){
                                    viewModel.plusPage()
                                    //viewModel.searchAnimalHospital()
                                }
                            }
                        }
                    }
                })
            }

        }

        BottomSheetBehavior.from(binding.sheetKm).state = BottomSheetBehavior.STATE_HIDDEN



    }

    private fun SearchViewModel.setObserves(){
        action.observe(this@SearchFragment, EventObserver{
            when(it){
                SearchViewModel.SearchAction.KM_OPEN ->{
                    if (BottomSheetBehavior.from(binding.sheetKm).state == BottomSheetBehavior.STATE_HIDDEN ||
                        BottomSheetBehavior.from(binding.sheetKm).state == BottomSheetBehavior.STATE_COLLAPSED
                    ) {
                        BottomSheetBehavior.from(binding.sheetKm).state =
                            BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
                }

                SearchViewModel.SearchAction.KM_CLOSE ->{
                    BottomSheetBehavior.from(binding.sheetKm).state = BottomSheetBehavior.STATE_HIDDEN
                }
            }

        })

        page.observe(this@SearchFragment, Observer {
            viewModel.searchAnimalHospital()
        })

        km.observe(this@SearchFragment, Observer {

        })

        actionItem.observe(this@SearchFragment, EventObserver{
            val intent = Intent(requireContext(), SearchDetailActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)

        })
    }


    private val UPDATE_INTERVAL = 500.toLong()
    private val FASTEST_INTERVAL: Long = 500

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {

        mLocationRequest = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = UPDATE_INTERVAL
            fastestInterval = FASTEST_INTERVAL
        }

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(requireActivity())
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationClient.requestLocationUpdates(mLocationRequest!!, locationCallback, Looper.myLooper()!!)

    }


    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Log.d("ourActivity", "onLocationResult")
            locationResult.lastLocation.run {

                val position = LatLng(latitude, longitude)
                viewModel.setPosition(position)

                Log.d("locationResult", position.toString())



                removeLocationCallback()
            }
        }
    }

    fun removeLocationCallback() {
        fusedLocationClient.removeLocationUpdates(locationCallback)

        viewModel.setObserves()
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){
            //권한 획득시
            startLocationUpdates()
            viewModel.isLoading.value = true
        }else{
            //권한 획득 거부시
            toast("위치권한 거부시 병원찾기 기능을 사용 할 수 없습니다.")
        }
    }

    // Ex. Launching ACCESS_FINE_LOCATION permission.
    private fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

class AnimalHospitalAdapter(vm : SearchViewModel) : BaseRecyclerAdapter<SearchData, ItemSearchAnimalBinding>(
    R.layout.item_search_animal, vm
)