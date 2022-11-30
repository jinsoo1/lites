package com.theshine.android.lites.ui.view.main.community.post

import android.util.Log
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.recyclerview.widget.RecyclerView
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.remote.source.FeedDataSource
import com.theshine.android.lites.util.Event
import com.theshine.android.lites.util.MediaUtil.Companion.getPath
import com.theshine.android.lites.util.MediaUtil.Companion.resizeBitmapImage
import io.reactivex.rxkotlin.addTo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PostWriteViewModel(): BaseViewModel() {

    val action: MutableLiveData<Event<PostWriteActions>> = MutableLiveData()

    private val _listUri : MutableLiveData<List<String?>> = MutableLiveData(listOf())
    val listUrl : MutableLiveData<List<String?>> get() = _listUri

    private val _toDay : MutableLiveData<String> = MutableLiveData()
    val toDay : LiveData<String> get() = _toDay

    private val _region : MutableLiveData<String> = MutableLiveData()
    val region : LiveData<String> get() = _region

    private val _title : MutableLiveData<String> = MutableLiveData("")
    val title : MutableLiveData<String> get() = _title

    private val _content : MutableLiveData<String> = MutableLiveData("")
    val content : MutableLiveData<String> get() = _content

    private val _category : MutableLiveData<String> = MutableLiveData()
    val category : LiveData<String> get() = _category

    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        val now = System.currentTimeMillis()
        val date = Date(now)

        val nowDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        _toDay.value = nowDate.format(date)
    }

    fun addPhoto(){
        action.value = Event(PostWriteActions.GALLERY)
    }

    fun settingUriList(list : List<String>){
        _listUri.value = list
    }

    fun settingRegion(region : String){
        _region.value = region
    }

    fun settingCategory(category : Boolean){
        _category.value = if(category){
            "정보공유"
        } else{
            "수다방"
       }
    }

    fun selectRegion(){
        action.value = Event(PostWriteActions.REGION)
    }

    fun onClickBack(){
        action.value = Event(PostWriteActions.BACK)
    }

    fun uploadFeed(){
        action.value = Event(PostWriteActions.UPLOAD)
    }

    fun deleteImage(position : Int){
        val list : ArrayList<String?> = arrayListOf()
        list.addAll( _listUri.value!!)
        list.removeAt(position)

        _listUri.value = list
    }



    enum class PostWriteActions {
        GALLERY, REGION, UPLOAD, BACK
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindImageList")
        fun bindImageList(rv: RecyclerView, list: List<String>) {
            val adapter = rv.adapter as ImageListAdapter?
            adapter?.let {
                it.updateItems(list)
            }
        }

    }
}