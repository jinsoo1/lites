package com.theshine.android.lites.ui.view.main.community.sharedInfo.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.data.local.pref.PreferencesController
import com.theshine.android.lites.databinding.DialogBottomsheetProfileBinding
import com.theshine.android.lites.ui.view.activity.ActivityUserActivity
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoViewModel
import org.jetbrains.anko.support.v4.intentFor


class ProfileBottomDialog(val vm : SharedInfoViewModel, val mContext : Context, val listener: MenuItemClickListener) : BottomSheetDialogFragment(){

    lateinit var binding : DialogBottomsheetProfileBinding

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_bottomsheet_profile, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if(vm.profile.value!!.isBlocked.get()) {
            binding.layoutMiddle.visibility = View.GONE
            binding.tvBlock.visibility = View.VISIBLE
            binding.btnFeed.visibility = View.GONE
        }else{
            binding.layoutMiddle.visibility = View.VISIBLE
            binding.tvBlock.visibility = View.GONE
            binding.btnFeed.visibility = View.VISIBLE
        }

        if(PreferencesController.userInfoPref.userToken != vm.profile.value!!.userToken) {
            val toolbar: Toolbar? = dialog?.findViewById(binding.toolbar.id)
            if(vm.profile.value!!.isBlocked.get()) {
                toolbar?.inflateMenu(R.menu.menu_unprofile)
            }else{
                toolbar?.inflateMenu(R.menu.menu_profile)
            }
            toolbar?.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menuBAD -> {
                        listener.userReport()
                        true
                    }
                    R.id.menuBLOCK -> {
                        listener.blockUser()
                        dismiss()
                        true
                    }
                    R.id.menuUNBLOCK ->{
                        listener.unBlockUser()
                        dismiss()
                        true
                    }
                    else -> false
                }
            }
        }


        binding.tvName.text = vm.profile.value!!.name

        binding.tvAge.text = vm.profile.value!!.birth + "살"
        if(vm.profile.value!!.gender){
            binding.ivGender.setImageResource(R.drawable.ic_male)
        }else{
            binding.ivGender.setImageResource(R.drawable.ic_female)
        }
        if(!vm.profile.value!!.weight.equals(null)){
            binding.tvWeight.text = vm.profile.value!!.weight + "kg"
        }else{
            binding.tvWeight.visibility = View.GONE
        }

        binding.tvVariety.text = vm.profile.value!!.variety



        vm.profile.value!!.profileImg.let {
            Glide.with(mContext)
                .load(it)
                .circleCrop()
                .placeholder(R.drawable.illust_dog)
                .error(R.drawable.illust_dog)
                .into(binding.ivProfile)
        }

        binding.btnFeed.setOnClickListener {
            if(PreferencesController.userInfoPref.userToken == vm.profile.value!!.userToken) {
                startActivity(
                    intentFor<ActivityUserActivity>(
                        "userToken" to PreferencesController.userInfoPref.userToken,
                        "my" to true
                    )
                )
            }else{
                startActivity(
                    intentFor<ActivityUserActivity>(
                        "userToken" to vm.profile.value!!.userToken,
                    )
                )
            }

        }

    }




}