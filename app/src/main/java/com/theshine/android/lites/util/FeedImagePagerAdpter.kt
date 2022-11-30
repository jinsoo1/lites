package com.theshine.android.lites.util

import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.databinding.ItemFeedImageListPagerBinding

class FeedImagePagerAdpter : BaseRecyclerAdapter<String, ItemFeedImageListPagerBinding>(
    R.layout.item_feed_image_list_pager
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

}