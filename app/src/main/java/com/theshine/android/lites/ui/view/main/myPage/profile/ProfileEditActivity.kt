package com.theshine.android.lites.ui.view.main.myPage.profile

import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.*
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.bcs.BcsViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyData
import com.theshine.android.lites.ui.view.info.variety.VarietyListAdapter
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.EventObserver
import org.checkerframework.common.returnsreceiver.qual.This
import org.koin.android.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import kotlin.math.absoluteValue

class ProfileEditActivity: BaseVmActivity<ActivityProfileEditBinding>(
    R.layout.activity_profile_edit,
    ProfileEditViewModel::class.java
) {
    override val viewModel by lazy { vm as ProfileEditViewModel }
    override val toolbarId = 0


    private val petToken: String by lazy { intent.getStringExtra("petToken") ?: "" }
    private val name: String by lazy { intent.getStringExtra("name") ?: "" }
    private val birth: String by lazy { intent.getStringExtra("birth") ?: "" } //생년월일
    private val variety: String by lazy { intent.getStringExtra("variety") ?: "" } //생년월일
    private val profileImage: String by lazy { intent.getStringExtra("profileImage") ?: "" }
    private val gender: Int by lazy { intent.getIntExtra("gender", 0)}
    private val neutralization: Int by lazy { intent.getIntExtra("neutralization", 0)}
    private val bcs: Int by lazy { intent.getIntExtra("bcs", 0)}

    override fun initActivity() {

        binding.rvBcs.adapter = BcsListAdapter(viewModel)

        binding.rvVariety.adapter = VarietyListAdapterr(viewModel)

        BottomSheetBehavior.from(binding.sheetBcs).state = BottomSheetBehavior.STATE_HIDDEN
        BottomSheetBehavior.from(binding.sheetVariety).state = BottomSheetBehavior.STATE_HIDDEN

        viewModel.petToken.value = petToken
        viewModel.name.value = name
        viewModel.birth.value = birth
        viewModel.variety.value = variety
        viewModel.profileImage.value = profileImage
        viewModel.gender.value = gender
        viewModel.neutralization.value = neutralization
        viewModel.bcsString.value = bcs.toString()+"단계"

        viewModel.bcs.value = bcs


        if (gender == 0){
            //여아
            binding.tvFemale.setBackgroundResource(R.drawable.bg_red_stroke_1dp_radius_6dp)
            binding.tvMale.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)

        }else{
            //남아
            binding.tvFemale.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
            binding.tvMale.setBackgroundResource(R.drawable.bg_red_stroke_1dp_radius_6dp)
        }

        if (neutralization == 0){
            //중성화 안함
            binding.btnO.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
            binding.btnX.setBackgroundResource(R.drawable.bg_green_stroke_1dp_radius_6dp)
        }else{
            //중성화 함
            binding.btnO.setBackgroundResource(R.drawable.bg_green_stroke_1dp_radius_6dp)
            binding.btnX.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
        }


        binding.btnVariety.setOnClickListener {
            bottomSheetVarietyState()
        }

        binding.btnBcs.setOnClickListener {
            bottomSheetState()

        }

        viewModel.setObserves()
    }

    fun ProfileEditViewModel.setObserves(){
        action.observe(lifecycleOwner, EventObserver{
            when(it){
                ProfileEditViewModel.ProfileEditActions.UPDATE -> {

                    viewModel.updateProfile()


                }
                ProfileEditViewModel.ProfileEditActions.FEMALE -> {
                    viewModel.gender.value = 0
                    Log.d("프로필수정", "gender 값 : "+viewModel.gender.value.toString())
                    binding.tvFemale.setBackgroundResource(R.drawable.bg_red_stroke_1dp_radius_6dp)
                    binding.tvMale.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                }
                ProfileEditViewModel.ProfileEditActions.MALE -> {
                    viewModel.gender.value = 1
                    Log.d("프로필수정", "gender 값 : "+viewModel.gender.value.toString())
                    binding.tvFemale.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                    binding.tvMale.setBackgroundResource(R.drawable.bg_red_stroke_1dp_radius_6dp)
                }
                ProfileEditViewModel.ProfileEditActions.O -> {
                    viewModel.neutralization.value = 1
                    binding.btnO.setBackgroundResource(R.drawable.bg_green_stroke_1dp_radius_6dp)
                    binding.btnX.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                }
                ProfileEditViewModel.ProfileEditActions.X -> {
                    viewModel.neutralization.value = 0
                    binding.btnO.setBackgroundResource(R.drawable.bg_square_f9f8_radius_6dp)
                    binding.btnX.setBackgroundResource(R.drawable.bg_green_stroke_1dp_radius_6dp)
                }
                ProfileEditViewModel.ProfileEditActions.BIRTH -> {
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
                            viewModel.birth.value = "$year-$monthDay-$days"
                            Log.d("프로필수정", "벌쓰데이 : "+"$year-$monthDay-$days")

                        }

                    val dialog = DatePickerDialog(
                        this@ProfileEditActivity,
                        myDatePicker,
                        2015,
                        7,
                        25
                    ).show()

                }
            }
            petToken.observe(this@ProfileEditActivity, Observer {
                initProfileData(it)
            })
        })

        finishAction.observe(lifecycleOwner, EventObserver{
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()

        })

        bcsSelect.observe(lifecycleOwner, EventObserver{
            BottomSheetBehavior.from(binding.sheetBcs).state =
                BottomSheetBehavior.STATE_HIDDEN
            binding.tvBcs.text = it.bcs.toString()+"단계"
            viewModel.bcsString.value = it.bcs.toString()+"단계"
            viewModel.bcs.value = it.bcs

        })

        varietySelect.observe(lifecycleOwner, EventObserver{
            BottomSheetBehavior.from(binding.sheetVariety).state =
                BottomSheetBehavior.STATE_HIDDEN
            binding.tvVariety.text = it.variety
        })
    }

    fun bottomSheetState(){
        if (BottomSheetBehavior.from(binding.sheetBcs).state == BottomSheetBehavior.STATE_HALF_EXPANDED ||
            BottomSheetBehavior.from(binding.sheetBcs).state == BottomSheetBehavior.STATE_COLLAPSED
        ) {
            BottomSheetBehavior.from(binding.sheetBcs).state =
                BottomSheetBehavior.STATE_HIDDEN



        } else if (BottomSheetBehavior.from(binding.sheetBcs).state == BottomSheetBehavior.STATE_HIDDEN ||
            BottomSheetBehavior.from(binding.sheetBcs).state == BottomSheetBehavior.STATE_COLLAPSED
        ) {
            BottomSheetBehavior.from(binding.sheetBcs).state =
                BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }

    fun bottomSheetVarietyState(){
        if (BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_HALF_EXPANDED ||
            BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_COLLAPSED
        ) {
            BottomSheetBehavior.from(binding.sheetVariety).state =
                BottomSheetBehavior.STATE_HIDDEN



        } else if (BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_HIDDEN ||
            BottomSheetBehavior.from(binding.sheetVariety).state == BottomSheetBehavior.STATE_COLLAPSED
        ) {
            BottomSheetBehavior.from(binding.sheetVariety).state =
                BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }

    override fun onResume() {
        super.onResume()
    }
}

class BcsListAdapter(vm: ProfileEditViewModel) : BaseRecyclerAdapter<BcsData, ItemBcsBinding>(
    R.layout.item_bcs, vm
)

class VarietyListAdapterr(vm : ProfileEditViewModel) : BaseRecyclerAdapter<VarietyData, ItemVarietyEditBinding>(
    R.layout.item_variety_edit, vm
)