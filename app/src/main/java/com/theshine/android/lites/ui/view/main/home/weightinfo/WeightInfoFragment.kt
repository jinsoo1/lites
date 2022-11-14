package com.theshine.android.lites.ui.view.main.home.weightinfo

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentWeightInfoBinding
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.home.weightinfo.ingredient.IngredientActivity
import com.theshine.android.lites.util.EventObserver
import org.jetbrains.anko.support.v4.intentFor
import org.koin.android.viewmodel.ext.android.sharedViewModel

class WeightInfoFragment : BaseVmFragment<FragmentWeightInfoBinding>(
    R.layout.fragment_weight_info,
    WeightInfoViewModel::class.java
) {
    override val viewModel by lazy { vm as WeightInfoViewModel }
    val activityViewModel by sharedViewModel<MainViewModel>()
    override fun initFragment() {
        Log.d("Fragment2", "Fragment")
        viewModel.setObserves()

        viewModel.setMyPetToken(activityViewModel.myPet.value)
        binding.textView2.text = activityViewModel.myPet.value!!.name + "(이)의 체중정보가 없습니다.\n체중계를 연동해 체중정보를 저장해주세요."

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

            if(result.resultCode == RESULT_OK){
                viewModel.getMyPetWeight(viewModel.myPetWeight.value?.petToken)
            }

        }

    }

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    private fun WeightInfoViewModel.setObserves(){

        action.observe(this@WeightInfoFragment, EventObserver{
            when(it){
                WeightInfoViewModel.WeightInfoAction.INGREDIENT ->{
                    val intent = Intent(requireContext(), IngredientActivity::class.java)
                    intent.putExtra("petToken", viewModel.myPetWeight.value?.petToken,)
                    intent.putExtra("name", viewModel.myPetWeight.value?.name)
                    resultLauncher.launch(intent)
                }
            }
        })

        petToken.observe(this@WeightInfoFragment, Observer {
            getMyPetWeight(it)
        })

        myPetWeight.observe(this@WeightInfoFragment, Observer {
            getBCSData()

            when(it.type){
                "강아지" ->{
                    setDogImageView(it.bcs)
                }
                "고양이" ->{
                    setCatImageView(it.bcs)
                }
            }
        })


    }

    fun setDogImageView(bsc : Int){
        binding.apply {
            when(bsc){
                1 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_1)
                    ivBcsS2.setImageResource(R.drawable.dog_top_1)
                    tvState.text = "이며 매우 말랐어요!"
                    tvMinuteMy.text = "0분 ~ 30분"
                }
                2 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_2)
                    ivBcsS2.setImageResource(R.drawable.dog_top_2)
                    tvState.text = "이며 말랐어요!"
                    tvMinuteMy.text = "0분 ~ 30분"
                }
                3 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_3)
                    ivBcsS2.setImageResource(R.drawable.dog_top_3)
                    tvState.text = "이며 어느정도 말랐어요!"
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                4 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_4)
                    ivBcsS2.setImageResource(R.drawable.dog_top_4)
                    tvState.text = "이며 조금 말랐어요!"
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                5 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_5)
                    ivBcsS2.setImageResource(R.drawable.dog_top_5)
                    tvState.text = "이며 표준이에요."
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                6 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_6)
                    ivBcsS2.setImageResource(R.drawable.dog_top_6)
                    tvState.text = "이며 조금 쪘어요!"
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                7 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_7)
                    ivBcsS2.setImageResource(R.drawable.dog_top_7)
                    tvState.text = "이며 어느정도 쪘어요!"
                    tvMinuteMy.text = "1시간 ~ 1시간 30분"
                }
                8 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_8)
                    ivBcsS2.setImageResource(R.drawable.dog_top_8)
                    tvState.text = "이며 쪘어요!"
                    tvMinuteMy.text = "1시간 ~ 1시간 30분"
                }
                9 ->{
                    ivBcsS1.setImageResource(R.drawable.dog_side_9)
                    ivBcsS2.setImageResource(R.drawable.dog_top_9)
                    tvState.text = "이며 매우 쪘어요!"
                    tvMinuteMy.text = "1시간 ~ 1시간 30분"
                }
            }
        }

    }

    fun setCatImageView(bsc : Int){
        binding.apply {
            when(bsc){
                1 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_1)
                    ivBcsS2.setImageResource(R.drawable.cat_top_1)
                    tvState.text = "이며 매우 말랐어요!"
                    tvMinuteMy.text = "0분 ~ 30분"
                }
                2 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_2)
                    ivBcsS2.setImageResource(R.drawable.cat_top_1)
                    tvState.text = "이며 말랐어요!"
                    tvMinuteMy.text = "0분 ~ 30분"
                }
                3 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_3)
                    ivBcsS2.setImageResource(R.drawable.cat_top_3)
                    tvState.text = "이며 어느정도 말랐어요!"
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                4 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_4)
                    ivBcsS2.setImageResource(R.drawable.cat_top_4)
                    tvState.text = "이며 조금 말랐어요!"
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                5 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_5)
                    ivBcsS2.setImageResource(R.drawable.cat_top_5)
                    tvState.text = "이며 표준이에요."
                    tvMinuteMy.text = "30분 ~ 1시간"}

                6 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_6)
                    ivBcsS2.setImageResource(R.drawable.cat_top_6)
                    tvState.text = "이며 조금 쪘어요!"
                    tvMinuteMy.text = "30분 ~ 1시간"
                }
                7 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_7)
                    ivBcsS2.setImageResource(R.drawable.cat_top_7)
                    tvState.text = "이며 어느정도 쪘어요!"
                    tvMinuteMy.text = "1시간 ~ 1시간 30분"
                }
                8 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_8)
                    ivBcsS2.setImageResource(R.drawable.cat_top_8)
                    tvState.text = "이며 쪘어요!"
                    tvMinuteMy.text = "1시간 ~ 1시간 30분"
                }
                9 ->{
                    ivBcsS1.setImageResource(R.drawable.cat_side_9)
                    ivBcsS2.setImageResource(R.drawable.cat_top_9)
                    tvState.text = "이며 매우 쪘어요!"
                    tvMinuteMy.text = "1시간 ~ 1시간 30분"
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()

    }

}