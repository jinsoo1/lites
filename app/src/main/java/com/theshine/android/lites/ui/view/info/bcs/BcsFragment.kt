package com.theshine.android.lites.ui.view.info.bcs

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.remote.source.PetDataSource
import com.theshine.android.lites.databinding.FragmentInfoBcsBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.util.EventObserver
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BcsFragment : BaseVmFragment<FragmentInfoBcsBinding>(
    R.layout.fragment_info_bcs,
    BcsViewModel::class.java
) {
    override val viewModel by lazy { vm as BcsViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    private val petDataSource: PetDataSource by inject()

    override fun initFragment() {

        Log.d("정보입력테스트", activityViewModel.type.toString()+activityViewModel.name.toString()+activityViewModel.birth.toString()+
                activityViewModel.height.toString()+activityViewModel.waist.toString())

        viewModel.setObserves()

    }

    fun BcsViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                BcsViewModel.BcsActions.NEXT -> {
                    petDataSource.insertPet(
                        activityViewModel.type.value!!,
                        activityViewModel.name.value!!,
                        activityViewModel.birth.value!!,
                        activityViewModel.variety.value!!,
                        activityViewModel.gender.value!!,
                        activityViewModel.neutralization.value!!,
                        activityViewModel.height.value!!,
                        activityViewModel.waist.value!!,
                        activityViewModel.bcs.value!!
                    )
                    .subscribe({
                        Log.d("정보입력테스트", it.toString())
                               if(it.success){
                                   val action = BcsFragmentDirections.actionBcsFragmentToScaleFragment()
                                   findNavController().navigate(action)
                               }
                    },{
                        Log.d("정보입력테스트 E", it.toString())
                        it.printStackTrace()
                    })
                    .addTo(viewModel.compositeDisposable)

                }
            }
        })

        bcs.observe(this@BcsFragment, Observer {
            activityViewModel.bcs.value = it
            if(it != null){
                selectedNext()
            }else{
                unSelectedNext()
            }

        })

        selected.observe(this@BcsFragment, Observer {
            binding.layoutNext.isEnabled = it
        })
    }
}