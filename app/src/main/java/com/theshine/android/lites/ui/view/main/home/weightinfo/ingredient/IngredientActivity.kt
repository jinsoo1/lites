package com.theshine.android.lites.ui.view.main.home.weightinfo.ingredient

import android.content.Intent
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityIngredientBinding
import com.theshine.android.lites.util.EventObserver

class IngredientActivity : BaseVmActivity<ActivityIngredientBinding>(
    R.layout.activity_ingredient,
    IngredientActViewModel::class.java
){
    override val viewModel by lazy { vm as IngredientActViewModel }
    override val toolbarId = 0

    val petToken by lazy {
        intent.getStringExtra("petToken")
    }
    val name by lazy {
        intent.getStringExtra("name")
    }

    override fun initActivity() {

        petToken?.let { viewModel.settingPetToken(it) }

        binding.tvName.text = name

        viewModel.setObserves()

    }

    fun IngredientActViewModel.setObserves(){

        action.observe(this@IngredientActivity, EventObserver {
            when(it){
                IngredientActViewModel.IngredientAction.FINISH ->{
                    val intent = Intent()
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        })

    }
}