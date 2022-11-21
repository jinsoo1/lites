package com.theshine.android.lites.ui.view.info.namebirth

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.main.community.post.uritype
import com.theshine.android.lites.util.EventObserver
import com.theshine.android.lites.util.MediaUtil.Companion.getPath
import com.theshine.android.lites.util.MediaUtil.Companion.resizeBitmapImage
import gun0912.tedbottompicker.TedRxBottomPicker
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.support.v4.toast
import org.koin.android.viewmodel.ext.android.sharedViewModel


class NameFragment : BaseVmFragment<FragmentInfoNameBinding>(
    R.layout.fragment_info_name,
    NameViewModel::class.java
) {
    override val viewModel by lazy { vm as NameViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun initFragment() {

        binding.btnBack.setOnClickListener{
            requireActivity().onBackPressed()
        }

        viewModel.setObserves()


    }



    fun NameViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                NameViewModel.NameActions.NEXT -> {

                    activityViewModel.name.value = binding.etName.text.toString()
                    activityViewModel.birth.value = binding.etBirth.text.toString()
                    Log.d("정보입력테스트", "이름 : "+binding.etName.text.toString())
                    Log.d("정보입력테스트", "생년월일 : "+binding.etBirth.text.toString())

                    val action = NameFragmentDirections.actionNameFragmentToVarietyFragment()
                    findNavController().navigate(action)

                }

                NameViewModel.NameActions.BIRTH ->{
                    val myDatePicker =
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            val monthOfYear = month+1
                            val monthDay : String = if(monthOfYear < 10){
                                "0$monthOfYear"
                            }else{
                                monthOfYear.toString()
                            }
                            val days : String = if(dayOfMonth < 10){
                                "0$dayOfMonth"
                            }else{
                                dayOfMonth.toString()
                            }
                            binding.etBirth.text = "$year-$monthDay-$days"

                        }

                    val dialog = DatePickerDialog(
                        requireContext(),
                        myDatePicker,
                        2010,
                        7,
                        25
                    ).show()
                }

                NameViewModel.NameActions.GALLERY ->{
                    startLocationPermissionRequest()

                }
            }
        })

        selected.observe(this@NameFragment, Observer {
            binding.layoutNext.isEnabled = it
        })

        name.observe(this@NameFragment, Observer {
            if(binding.etName.text.isNotEmpty() && birth.value !=  null){
                trueSelected()
            }else{
                falseSelected()
            }
        })

        birth.observe(this@NameFragment, Observer {
            if(binding.etName.text.isNotEmpty() && birth.value !=  null){
                trueSelected()
            }else{
                falseSelected()
            }
        })

    }

    private var singleImageDisposable: Disposable? = null
    private var selectedUri : Uri? = null
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){

            singleImageDisposable = TedRxBottomPicker.with(requireActivity())
                .setSelectedUri(selectedUri)
                .setPeekHeight(1200)
                .show()
                .subscribe({ it ->
                    viewModel.settingUri(it)
                    activityViewModel.profileImg.value = getPath(requireContext(), resizeBitmapImage(it, 1280, 1280, requireActivity())!!)
                }, Throwable::printStackTrace)

        }else{
            //권한 획득 거부시
            toast("프로필 설정을 할 수 없습니다.")
        }
    }

    // Ex. Launching ACCESS_FINE_LOCATION permission.
    private fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onDestroy() {
        if (singleImageDisposable != null && !singleImageDisposable?.isDisposed!!) {
            singleImageDisposable!!.dispose();
        }
        super.onDestroy()
    }




}