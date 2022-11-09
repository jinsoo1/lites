package com.theshine.android.lites.ui.view.info.ingredient

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.databinding.FragmentInfoIngredientBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.bcs.BcsFragmentDirections
import com.theshine.android.lites.util.EventObserver
import io.reactivex.rxkotlin.addTo
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class IngredientFragment : BaseVmFragment<FragmentInfoIngredientBinding>(
    R.layout.fragment_info_ingredient,
    IngredientViewModel::class.java
) {
    override val viewModel by lazy { vm as IngredientViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    override fun initFragment() {

        binding.tvName.text = activityViewModel.name.value.toString()
        if (binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty()){
            binding.layoutNext.setBackgroundColor(Color.parseColor("#DCF5E7"))
            Log.d("테스트123","isEnabled!!"+binding.layoutNext.isEnabled)
        }

        Log.d("테스트123","test!!"+binding.etMoisture.text!!)

        viewModel.setObserves()

    }

    fun IngredientViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                IngredientViewModel.IngredientActions.SKIP -> {
                    val action = IngredientFragmentDirections.actionIngredientFragmentToScaleFragment()
                    findNavController().navigate(action)
                }

                IngredientViewModel.IngredientActions.NEXT -> {
                    Log.d("테스트123","다음")
                    Log.d("테스트123", "모이스처 : "+binding.etMoisture.text.toString())

                    if (binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty()){
                        val action = IngredientFragmentDirections.actionIngredientFragmentToScaleFragment()
                        findNavController().navigate(action)

                        activityViewModel.moisture.value = binding.etMoisture.text.toString()
                        activityViewModel.protein.value = binding.etProtein.text.toString()
                        activityViewModel.fat.value = binding.etFat.text.toString()
                        activityViewModel.fiber.value = binding.etFiber.text.toString()
                        activityViewModel.ash.value = binding.etAsh.text.toString()
                        Log.d("테스트123", binding.etMoisture.text.toString()
                                +binding.etProtein.text.toString()
                                +binding.etFat.text.toString()
                                +binding.etFiber.text.toString()
                                +binding.etAsh.text.toString())
                    } else {

                            toast("사료성분을 모두 적어주세요")

                    }

//                    petDataSource.insertPet(
//                        activityViewModel.type.value!!,
//                        activityViewModel.name.value!!,
//                        activityViewModel.birth.value!!,
//                        activityViewModel.variety.value!!,
//                        activityViewModel.gender.value!!,
//                        activityViewModel.neutralization.value!!,
//                        activityViewModel.bcs.value!!,
//                        activityViewModel.moisture.value!!,
//                        activityViewModel.protein.value!!,
//                        activityViewModel.fat.value!!,
//                        activityViewModel.fiber.value!!,
//                        activityViewModel.ash.value!!
//                    )
//                        .subscribe({
//                            Log.d("정보입력테스트", "최종 정보@@@ "+it.toString())
//                            if(it.success){
//                                val action = IngredientFragmentDirections.actionIngredientFragmentToScaleFragment()
//                                findNavController().navigate(action)
//                            }
//                        },{
//                            Log.d("정보입력테스트 E", it.toString())
//                            it.printStackTrace()
//                        })
//                        .addTo(viewModel.compositeDisposable)

                }
            }
        })

        moisture.observe(this@IngredientFragment, Observer {
            viewModel.selectedNext()
            Log.d("테스트123",it.toString())
        })

        protein.observe(this@IngredientFragment, Observer {
            viewModel.selectedNext()
            Log.d("테스트123","protein"+it.toString())
        })

        fat.observe(this@IngredientFragment, Observer {
            viewModel.selectedNext()
            Log.d("테스트123","fat"+it.toString())
        })

        fiber.observe(this@IngredientFragment, Observer {
            viewModel.selectedNext()
            Log.d("테스트123","fiber"+it.toString())
        })


//        moisture.observe(this@IngredientFragment, Observer {
//        if(binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty()){
//            viewModel.selectedNext()
//            Log.d("테스트123","selectedNext")
//        }else{
//            viewModel.unSelectedNext()
//            Log.d("테스트123","unSelectedNext")
//         }
//        })
//
//       protein.observe(this@IngredientFragment, Observer {
//            if(binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty()){
//                selectedNext()
//                Log.d("테스트123","selectedNext")
//            }else{
//                unSelectedNext()
//                Log.d("테스트123","unSelectedNext")
//            }
//        })
//
//        fat.observe(this@IngredientFragment, Observer {
//            if(binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty()){
//                selectedNext()
//            }else{
//                unSelectedNext()
//                Log.d("테스트123","unSelectedNext")
//            }
//        })
//
//        fiber.observe(this@IngredientFragment, Observer {
//            if(binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty()){
//                selectedNext()
//            }else{
//                unSelectedNext()
//                Log.d("테스트123","unSelectedNext")
//            }
//        })

//        ash.observe(this@IngredientFragment, Observer {
//            if(binding.etMoisture.text.isNotEmpty() && binding.etProtein.text.isNotEmpty() && binding.etFat.text.isNotEmpty() && binding.etFiber.text.isNotEmpty() && binding.etAsh.text.isNotEmpty()){
//                selectedNext()
//            }else{
//                selectedNext()
//            }
//        })


//        selected.observe(this@IngredientFragment, Observer {
//            binding.layoutNext.isEnabled = it
//            Log.d("테스트123","boolean값 : "+it.toString())
//        })

    }
}