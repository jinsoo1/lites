package com.theshine.android.lites.ui.view.main.search.detail

import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.data.common.model.SearchData
import com.theshine.android.lites.databinding.ActivitySearchDetailBinding
import com.theshine.android.lites.util.EventObserver
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class SearchDetailActivity : BaseVmActivity<ActivitySearchDetailBinding>(
    R.layout.activity_search_detail,
    SearchDetailViewModel::class.java
) {
    override val viewModel by lazy { vm as SearchDetailViewModel }

    override val toolbarId = 0



    override fun initActivity() {
        viewModel.setHospitalData(intent.getSerializableExtra("detail") as SearchData)

        var kakaoMapView: MapView? = MapView(this)
        binding.apply {
            mapView.addView(kakaoMapView)

            kakaoMapView?.apply {
                setMapCenterPointAndZoomLevel(
                    MapPoint.mapPointWithGeoCoord(viewModel.hospitalDetail.value?.y!!.toDouble(), viewModel.hospitalDetail.value?.x!!.toDouble()),
                    3,
                    true)
                val marker = MapPOIItem()
                marker.itemName = viewModel.hospitalDetail.value!!.place_name
                marker.mapPoint = MapPoint.mapPointWithGeoCoord(viewModel.hospitalDetail.value?.y!!.toDouble(), viewModel.hospitalDetail.value?.x!!.toDouble())
                marker.markerType = MapPOIItem.MarkerType.BluePin
                addPOIItem(marker)
            }
        }




        viewModel.setObserves()
    }

    fun SearchDetailViewModel.setObserves(){
        action.observe(this@SearchDetailActivity, EventObserver{
            when(it){
                SearchDetailViewModel.SearchDetailAction.URL -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.hospitalDetail.value?.place_url))
                    startActivity(intent)
                }
                SearchDetailViewModel.SearchDetailAction.CALL -> startLocationPermissionRequest()

                SearchDetailViewModel.SearchDetailAction.FINISH -> finish()
            }
        })
    }


    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){
            //권한 획득시
            if(viewModel.hospitalDetail.value?.phone == null|| viewModel.hospitalDetail.value?.phone == ""){
                toast("해당병원은 전화번호를 확인할 수 없습니다.")
            }else{
                val intent = Intent("android.intent.action.DIAL", Uri.parse("tel:"+viewModel.hospitalDetail.value!!.phone))
                startActivity(intent)
            }

        }else{
            //권한 획득 거부시
            toast("통화권한 거부시 전화걸기 기능을 사용 할 수 없습니다.")
        }
    }

    // Ex. Launching ACCESS_FINE_LOCATION permission.
    private fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
    }



}