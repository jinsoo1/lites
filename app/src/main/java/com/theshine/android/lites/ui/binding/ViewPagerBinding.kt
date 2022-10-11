package com.theshine.android.lites.ui.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

//@BindingAdapter(value = ["bindFeedbackToPager"], requireAll = true)
//fun ViewPager2.bindFeedbackToPager(item: List<ExerciseList>?) {
//
//    if(this.adapter == null && item != null) {
//        this.adapter = FeedbackPagerAdapter(item)
//    }
//
//    this.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//    if(item != null){
//        (adapter as FeedbackPagerAdapter?)?.updateItems(item)
//    }
//
//}