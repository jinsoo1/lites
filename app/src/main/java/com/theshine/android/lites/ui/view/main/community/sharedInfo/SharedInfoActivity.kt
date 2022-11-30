package com.theshine.android.lites.ui.view.main.community.sharedInfo

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.data.common.model.CommentList
import com.theshine.android.lites.data.local.pref.PreferencesController
import com.theshine.android.lites.databinding.ActivitySharedInfoBinding
import com.theshine.android.lites.databinding.ItemCommentListBinding
import com.theshine.android.lites.ui.view.main.community.sharedInfo.dialog.MenuItemClickListener
import com.theshine.android.lites.ui.view.main.community.sharedInfo.dialog.ProfileBottomDialog
import com.theshine.android.lites.util.EventObserver

class SharedInfoActivity: BaseVmActivity<ActivitySharedInfoBinding>(
    R.layout.activity_shared_info,
    SharedInfoViewModel::class.java
) {

    override val viewModel by lazy { vm as SharedInfoViewModel }
    override val toolbarId : Int = R.id.toolbar

    private val feedToken by lazy { intent.getStringExtra("feedToken") }
    private val postState by lazy { intent.getStringExtra("postState") }

    override fun initActivity() {

        Log.d("feedPost", "feed : " + feedToken + "post : " + postState )
        viewModel.setObserves()
        viewModel.settingFeedToken(feedToken ?: "", postState ?: "")

        binding.apply {
            rvComments.apply {
                adapter = CommentAdapter(viewModel)
                layoutManager = LinearLayoutManager(context)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        binding.indicatorVp.setViewPager2(binding.ivSharedInfoPager)
    }

    private fun SharedInfoViewModel.setObserves(){
        feedToken.observe(this@SharedInfoActivity, Observer {
            getDetailFeedData()

        })

        action.observe(this@SharedInfoActivity, EventObserver{
            when(it){
                SharedInfoViewModel.DetailFeedAction.COMMENT ->{
                    setEmptyComment()
                    getComment(feedToken.value!!)
                }
                SharedInfoViewModel.DetailFeedAction.COMMENT_NOT_FIELD -> toast("댓글을 입력해주세요.")
                SharedInfoViewModel.DetailFeedAction.COMMENT_ERROR -> toast("댓글을 작성에 문제가 발생했습니다.")
                SharedInfoViewModel.DetailFeedAction.PROFILE ->{
                    val dialog = ProfileBottomDialog(this, this@SharedInfoActivity, object : MenuItemClickListener{
                        override fun blockUser() {
                            userBlock()

                        }

                        override fun unBlockUser() {
                            deleteBlockUser()

                        }

                        override fun userReport() {
                            showUserReportDialog()
                        }

                    })
                    dialog.show(supportFragmentManager, "")
                }
                SharedInfoViewModel.DetailFeedAction.FINISH -> finish()
                SharedInfoViewModel.DetailFeedAction.REPORT_COMPLETE -> toast("신고가 완료되었습니다.")
            }
        })

        detailData.observe(this@SharedInfoActivity, Observer {
            invalidateOptionsMenu()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuEdit -> toast("수정하기")
            R.id.menuDelete -> toast("삭제하기")
            R.id.menuReportPost -> showReportDialog()
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()

        menuInflater.inflate(
            if(viewModel.detailData.value?.userToken == PreferencesController.userInfoPref.userToken) R.menu.menu_post_user
            else R.menu.menu_post_viewer,
            menu
        )

        return super.onCreateOptionsMenu(menu)
    }
    private fun showReportDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("피드신고")
            .setView(R.layout.dialog_input_report)
            .setPositiveButton("신고하기") { dialog, _ ->
                val input = (dialog as AlertDialog).findViewById<EditText>(R.id.etInputReportMsg)?.text.toString()
                if (input.isNotBlank()) viewModel.reportFeedPost(input) else toast("신고 사유를 입력해주세요.")
            }
            .show()
    }

    private fun showUserReportDialog(){
        MaterialAlertDialogBuilder(this)
            .setTitle("유저신고")
            .setView(R.layout.dialog_input_report)
            .setPositiveButton("신고하기") { dialog, _ ->
                val input = (dialog as AlertDialog).findViewById<EditText>(R.id.etInputReportMsg)?.text.toString()
                if (input.isNotBlank()) viewModel.reportUserPost(input) else toast("신고 사유를 입력해주세요.")
            }
            .show()
    }




}

class CommentAdapter(vm: SharedInfoViewModel) :
        BaseRecyclerAdapter<CommentList, ItemCommentListBinding>(
            R.layout.item_comment_list, vm
        )