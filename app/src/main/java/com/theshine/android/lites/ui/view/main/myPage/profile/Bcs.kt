package com.theshine.android.lites.ui.view.main.myPage.profile

import androidx.databinding.ObservableBoolean

data class BcsData(
    val bcs: Int,
    val bcsString: String = bcs.toString()+" 단계"
){
    val isChecked : ObservableBoolean = ObservableBoolean(false)
}

val bcs_step = listOf(
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9
)