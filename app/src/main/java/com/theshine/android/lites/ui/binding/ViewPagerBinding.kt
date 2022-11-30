package com.theshine.android.lites.ui.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.theshine.android.lites.util.FeedImagePagerAdpter


@BindingAdapter(value = ["bindFeedImageListToPager"], requireAll = true)
fun ViewPager2.bindFeedImageListToPager(urls: List<String>?) {
    if(this.adapter == null) {
        this.adapter = FeedImagePagerAdpter()

    }
    this.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    if(urls?.size != null){
        (adapter as FeedImagePagerAdpter?)?.updateItems(urls!!)
    }

}

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