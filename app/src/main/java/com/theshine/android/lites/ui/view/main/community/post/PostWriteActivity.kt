package com.theshine.android.lites.ui.view.main.community.post

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.databinding.ActivityPostWriteBinding
import com.theshine.android.lites.util.EventObserver

class PostWriteActivity : BaseVmActivity<ActivityPostWriteBinding>(
    R.layout.activity_post_write,
    PostWriteViewModel::class.java
) {

    override val viewModel by lazy { vm as PostWriteViewModel }
    override val toolbarId = 0

    private val postState: Boolean? by lazy { intent.getBooleanExtra("postState", false) } // false = 수다방에 포스팅, true = 정보공유방에 포스팅
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    var uri: Uri? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        binding.test.setImageURI(result.data?.data)

    }

    override fun initActivity() {

        Log.d("테스트", "어디서왔누? :: "+postState.toString())

        viewModel.setObserves()
    }

    private fun PostWriteViewModel.setObserves(){
        action.observe(lifecycleOwner, EventObserver{
            when (it) {
                PostWriteViewModel.PostWriteActions.GALLERY -> {
                    //갤러리 오픈

                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = MediaStore.Images.Media.CONTENT_TYPE
                    intent.type = "image/*"
                    getContent.launch(intent)

                }
            }
        })
    }
}


