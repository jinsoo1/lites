package com.theshine.android.lites.ui.view.main.community.post

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.data.remote.source.AuthDataSource
import com.theshine.android.lites.data.remote.source.FeedDataSource
import com.theshine.android.lites.databinding.ActivityPostWriteBinding
import com.theshine.android.lites.databinding.ItemImageBinding
import com.theshine.android.lites.ui.view.info.InfoActivity
import com.theshine.android.lites.ui.view.main.community.region.RegionSelectActivity
import com.theshine.android.lites.util.EventObserver
import com.theshine.android.lites.util.MediaUtil
import com.theshine.android.lites.util.MediaUtil.Companion.getPath
import com.theshine.android.lites.util.MediaUtil.Companion.resizeBitmapImage
import gun0912.tedbottompicker.TedRxBottomPicker
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import java.io.File

class PostWriteActivity : BaseVmActivity<ActivityPostWriteBinding>(
    R.layout.activity_post_write,
    PostWriteViewModel::class.java
) {

    override val viewModel by lazy { vm as PostWriteViewModel }
    override val toolbarId = 0

    private val feedDataSource: FeedDataSource by inject()

    private val postState: Boolean by lazy { intent.getBooleanExtra("postState", false) } // false = 수다방에 포스팅, true = 정보공유방에 포스팅
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun initActivity() {


        viewModel.settingCategory(postState)

        binding.apply {
            rvImageList.apply {
                adapter = ImageListAdapter(viewModel)
            }
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

            if(result.resultCode == RESULT_OK){
                Log.d("resultLauncher1", result.data?.getIntExtra("regionInt",0).toString())
                Log.d("resultLauncher2", result.data?.getStringExtra("region").toString())

                viewModel.settingRegion(result.data?.getStringExtra("region")!!)
            }

        }

        Log.d("테스트", "어디서왔누? :: "+postState.toString())

        viewModel.setObserves()
    }

    private fun PostWriteViewModel.setObserves(){
        action.observe(lifecycleOwner, EventObserver{
            when (it) {
                PostWriteViewModel.PostWriteActions.GALLERY -> {
                    //갤러리 오픈
                    startLocationPermissionRequest()
                }

                PostWriteViewModel.PostWriteActions.REGION ->{
                    val intent = Intent(this@PostWriteActivity, RegionSelectActivity::class.java)
                    intent.putExtra("init", 1)
                    resultLauncher.launch(intent)
                }

                PostWriteViewModel.PostWriteActions.UPLOAD ->{

                    if(binding.selectorArea.text.isEmpty()){
                        toast("지역을 설정해주세요.")
                        return@EventObserver
                    }
                    if(binding.etTitle.text.isEmpty()){
                        toast("제목을 입력해주세요.")
                        return@EventObserver
                    }
                    if(binding.etDescription.text.isEmpty()){
                        toast("내용을 입력해주세요.")
                        return@EventObserver
                    }

                    feedUpload()
                }

                PostWriteViewModel.PostWriteActions.BACK ->{
                    finish()
                }
            }
        })
        listUrl.observe(this@PostWriteActivity, Observer {
            Log.d("URILIST", it.toString())
        })
    }


    private var multiImageDisposable: Disposable? = null
    private var selectedUriList : MutableList<Uri> = mutableListOf()
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){

            multiImageDisposable = TedRxBottomPicker.with(this)
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
                .setSelectedUriList(selectedUriList)
                .showMultiImage()
                .subscribe({ it ->
                    selectedUriList = it;
                    val uriList : MutableList<String> = mutableListOf()
                    selectedUriList.forEach { it ->
                        uriList.add(it.toString())
                    }
                    viewModel.settingUriList(uriList)
                }, Throwable::printStackTrace)

        }else{
            //권한 획득 거부시
            toast("프로필 설정을 할 수 없습니다.")
        }
    }

    // Ex. Launching ACCESS_FINE_LOCATION permission.
    private fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }


    lateinit var file: File
    fun feedUpload(){
        var photo : ArrayList<String> = ArrayList()

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.isLoading.postValue(true)
            withContext(Dispatchers.IO){
                viewModel.listUrl.value!!.forEach {
                    photo.add(getPath(this@PostWriteActivity, resizeBitmapImage(it!!.toUri(), 1280, 1280, this@PostWriteActivity)!!)!!)
                }

                feedDataSource.feedUpload(
                    viewModel.title.value!!.toRequestBody(),
                    viewModel.content.value!!.toRequestBody(),
                    viewModel.category.value!!.toRequestBody(),
                    viewModel.region.value!!.toRequestBody(),
                    photo.map {
                        file = File(it)
                        MultipartBody.Part.createFormData(
                            "photo",
                            file.name,
                            file.asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    }
                )
                    .subscribe({
                        toast("업로드 성공!")
                        viewModel.isLoading.postValue(false)
                        val intent = Intent()
                        setResult(RESULT_OK, intent)
                        finish()
                    },{
                        Log.d("feedUpload E", it.toString())
                        viewModel.isLoading.postValue(false)
                    })
                    .addTo(viewModel.compositeDisposable)

                delay(300)
            }
        }

    }



}

class ImageListAdapter(vm : PostWriteViewModel) : BaseRecyclerAdapter<String, ItemImageBinding>(
    R.layout.item_image, vm
){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        (holder.binding as ItemImageBinding).apply {
            ivCancel.setOnClickListener{
                vm!!.deleteImage(position)
            }
        }

    }
}


