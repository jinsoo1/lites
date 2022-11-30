package com.theshine.android.lites.ui.view.main.community.sharedInfo

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.CommentList
import com.theshine.android.lites.data.common.model.DetailFeedData
import com.theshine.android.lites.data.common.model.ProfileData
import com.theshine.android.lites.data.remote.source.FeedDataSource
import com.theshine.android.lites.data.remote.source.UserDataSource
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.ext.StringExt.getAmericanAge
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.rxkotlin.addTo


class SharedInfoViewModel(
    private val feedDataSource: FeedDataSource,
    private val userDataSource: UserDataSource
): BaseViewModel() {

    private val _action : MutableLiveData<Event<DetailFeedAction>> = MutableLiveData()
    val action : LiveData<Event<DetailFeedAction>> get() = _action

    private val _feedToken : MutableLiveData<String> = MutableLiveData()
    val feedToken : LiveData<String> get() = _feedToken

    private val _postState : MutableLiveData<String> = MutableLiveData()
    val postState : LiveData<String> get() = _postState

    private val _detailData : MutableLiveData<DetailFeedData> = MutableLiveData()
    val detailData : LiveData<DetailFeedData> get() = _detailData

    val commentList: MutableLiveData<List<CommentList>> = MutableLiveData(listOf())


    private val _comment : MutableLiveData<String> = MutableLiveData("")
    val comment : MutableLiveData<String> get() = _comment

    private val _profile : MutableLiveData<ProfileData> = MutableLiveData()
    val profile : LiveData<ProfileData> get() = _profile

    fun settingFeedToken(token : String, state : String){
        _feedToken.value = token
        _postState.value = state
    }

    fun getComment(token: String){
        feedDataSource.getComment(token)
            .map {
                it.map {
                    CommentList(
                        it.commentToken,
                        it.name,
                        it.profileImg,
                        it.createdAt,
                        it.comment,
                        it.petToken,
                        it.userToken
                    ).apply {
                        this.isWriter.set(detailData.value!!.userToken == this.userToken)
                        this.isBlocked.set(it.isBlocked == 1)
                    }
                }
            }
            .subscribe({
                commentList.value = it
                Log.d("getComment", it.toString())
            },{
                Log.d("getComment E", it.toString())
            }
            )
            .addTo(compositeDisposable)
    }

    fun getDetailFeedData(){

        feedDataSource.detailFeed(feedToken.value!!, postState.value!!)
            .subscribe({
                Log.d("getDetailData", it.toString())
                _detailData.value = DetailFeedData(
                    it.feedToken,
                    it.title,
                    it.content,
                    it.createdAt,
                    it.name,
                    it.profileImg,
                    it.Photo,
                    it.petToken,
                    it.likeCount,
                    it.commentCount,
                    it.visitCount,
                    it.userToken
                )

                getComment(feedToken.value!!)

            },{
                Log.d("getDetailData E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun postComment(){
        feedToken.value?.let {

            if(comment.value?.isNotEmpty() == true){
                feedDataSource.postCommentToFeed(it, comment.value!!)
                    .subscribe({
                        if(it.success){
                            _action.value = Event(DetailFeedAction.COMMENT)
                        }else{
                            _action.value = Event(DetailFeedAction.COMMENT_ERROR)
                        }

                    },{
                        Log.d("postComment E ", it.toString())
                        _action.value = Event(DetailFeedAction.COMMENT_ERROR)
                    })
                    .addTo(compositeDisposable)
            }else{
                _action.value = Event(DetailFeedAction.COMMENT_NOT_FIELD)
            }
        }
    }

    fun setEmptyComment(){
        _comment.value = ""
    }

    fun getProfileData(){
        feedDataSource.getProfile(detailData.value!!.petToken)
            .subscribe({
                _profile.value = ProfileData(
                        it.petToken,
                        it.profileImg,
                        it.name,
                    getAmericanAge(it.birth),
                        it.gender == 1,
                        it.weight,
                        it.variety,
                        it.userToken
                    ).apply{
                    this.isBlocked.set(it.isBlocked == 1)
                }
                _action.value = Event(DetailFeedAction.PROFILE)
               Log.d("getProfileData", it.toString())
            },{
                Log.d("getProfileData E ", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun getItemProfileData(petToken : String){
        feedDataSource.getProfile(petToken)
            .subscribe({
                _profile.value = ProfileData(
                    it.petToken,
                    it.profileImg,
                    it.name,
                    getAmericanAge(it.birth),
                    it.gender == 1,
                    it.weight,
                    it.variety,
                    it.userToken
                ).apply{
                    this.isBlocked.set(it.isBlocked == 1)
                }
                _action.value = Event(DetailFeedAction.PROFILE)
                Log.d("getProfileData", it.toString())
            },{
                Log.d("getProfileData E ", it.toString())
            })
            .addTo(compositeDisposable)
    }

    //피드신고
    fun reportFeedPost(reportMsg: String) {
        val feedToken = feedToken.value ?: return
        feedDataSource
            .reportFeed(feedToken, reportMsg)
            .onUI {
                _action.value = Event(DetailFeedAction.REPORT_COMPLETE)
            }
            .addTo(compositeDisposable)
    }

    fun reportUserPost(reportMsg: String) {
        val title = profile.value!!.name+"님을 신고합니다."
        val badUserToken = profile.value!!.userToken
        Log.d("badUserToken", badUserToken)
        feedDataSource
            .reportBadUser(title, reportMsg, badUserToken)
            .onUI {
                _action.value = Event(DetailFeedAction.REPORT_COMPLETE)
            }
            .addTo(compositeDisposable)
    }

    //차단
    fun userBlock(){
        userDataSource
            .blockUser(_profile.value!!.userToken)
            .onUI {

                toast(_profile.value!!.name + "님을 차단하였습니다.")
                getComment(feedToken.value!!)
            }
            .addTo(compositeDisposable)
    }

    fun deleteBlockUser() {
        // TODO("차단 취소 api 연동")

        userDataSource
            .unblockUser(_profile.value!!.userToken)
            .onUI {
                toast(_profile.value!!.name + "님을 차단 해제하였습니다.")
                getComment(feedToken.value!!)
            }
            .addTo(compositeDisposable)


    }

    enum class DetailFeedAction{
        COMMENT, COMMENT_NOT_FIELD, COMMENT_ERROR, PROFILE, FINISH, REPORT_COMPLETE
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindSharedInfo")
        fun bindSharedInfo(view: RecyclerView, list: List<CommentList>) {
            val adapter = view.adapter as CommentAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }
    }

}