package com.theshine.android.lites.ui.view.main.community.region

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityRegionSelectBinding
import com.theshine.android.lites.ui.view.main.community.post.PostWriteActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.intentFor

class RegionSelectActivity : BaseVmActivity<ActivityRegionSelectBinding>(
    R.layout.activity_region_select,
    RegionSelectViewModel::class.java
){
    override val viewModel by lazy { vm as RegionSelectViewModel }
    override val toolbarId = 0

    private val setInit by lazy { intent.getIntExtra("init", 0) }

    override fun initActivity() {

        if(setInit == 1){
            binding.layoutAll.visibility = View.GONE
        }

        viewModel.selectBtn(setInit)

        binding.apply {
            tvAll.isSelected = true
            tvSeoul.isSelected = true
            tvGyeonggi.isSelected = true
            tvBusan.isSelected = true
            tvDaegu.isSelected = true
            tvIncheon.isSelected = true
            tvGwangju.isSelected = true
            tvDaejeon.isSelected = true
            tvUlsan.isSelected = true
            tvSejong.isSelected = true
            tvGangwon.isSelected = true
            tvChungcheong1.isSelected = true
            tvChungcheong2.isSelected = true
            tvJeolla1.isSelected = true
            tvJeolla2.isSelected = true
            tvGyeongsang1.isSelected = true
            tvGyeongsang2.isSelected = true
            binding.tvJeju.isSelected = true
        }

        viewModel.setObserves()

    }


    private fun RegionSelectViewModel.setObserves(){
        action.observe(this@RegionSelectActivity, EventObserver{
            when(it){
                RegionSelectViewModel.RegionAction.NEXT ->{
                    val intent = Intent()
                    intent.putExtra("regionInt", selectedButton.value)
                    intent.putExtra("region", selectedRegion.value)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                RegionSelectViewModel.RegionAction.FINISH ->{
                    finish()
                }
            }
        })
        selectedButton.observe(this@RegionSelectActivity, Observer {
            when(it){
                0 -> selectRegion("전체지역")
                1 -> selectRegion("서울특별시")
                2 -> selectRegion("경기도")
                3 -> selectRegion("부산광역시")
                4 -> selectRegion("대구광역시")
                5 -> selectRegion("인천광역시")
                6 -> selectRegion("광주광역시")
                7 -> selectRegion("대전광역시")
                8 -> selectRegion("울산광역시")
                9 -> selectRegion("세종특별자치시")
                10 -> selectRegion("강원도")
                11 -> selectRegion("충청북도")
                12 -> selectRegion("충청남도")
                13 -> selectRegion("전라북도")
                14 -> selectRegion("전라남도")
                15 -> selectRegion("경상북도")
                16 -> selectRegion("경상남도")
                17 -> selectRegion("제주특별자치도")
            }
        })

    }


}