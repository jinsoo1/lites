package com.theshine.android.lites.ui.view.info.namebirth

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import gun0912.tedbottompicker.util.RealPathUtil
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.databinding.FragmentInfoNameBinding
import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.util.EventObserver
import kotlinx.android.synthetic.main.activity_profile_edit.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*

class NameFragment : BaseVmFragment<FragmentInfoNameBinding>(
    R.layout.fragment_info_name,
    NameViewModel::class.java
) {
    override val viewModel by lazy { vm as NameViewModel }
    val activityViewModel by sharedViewModel<InfoViewModel>()

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun initFragment() {

        viewModel.setObserves()


        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

            if(result.resultCode == RESULT_OK){
                Log.d("resultLauncher", result.data.toString())
                val intent = result.data
                val uri = intent?.data
                Log.d("resultLauncher URI", getPath(requireContext(), uri!!)!!)
                viewModel.settingUri(uri)
                activityViewModel.profileImg.value = getPath(requireContext(), uri)!!
            }

        }

    }



    fun NameViewModel.setObserves(){
        action.observe(viewLifecycleOwner, EventObserver{
            when(it){
                NameViewModel.NameActions.NEXT -> {

                    activityViewModel.name.value = binding.etName.text.toString()
                    activityViewModel.birth.value = binding.etBirth.text.toString()
                    Log.d("정보입력테스트", "이름 : "+binding.etName.text.toString())
                    Log.d("정보입력테스트", "생년월일 : "+binding.etBirth.text.toString())

                    val action = NameFragmentDirections.actionNameFragmentToVarietyFragment()
                    findNavController().navigate(action)

                }

                NameViewModel.NameActions.BIRTH ->{
                    val myDatePicker =
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            val monthOfYear = month+1
                            val monthDay : String = if(monthOfYear < 10){
                                "0$monthOfYear"
                            }else{
                                monthOfYear.toString()
                            }
                            val days : String = if(dayOfMonth < 10){
                                "0$dayOfMonth"
                            }else{
                                dayOfMonth.toString()
                            }
                            binding.etBirth.text = "$year-$monthDay-$days"




                        }

                    val dialog = DatePickerDialog(
                        requireContext(),
                        myDatePicker,
                        2010,
                        7,
                        25
                    ).show()
                }

                NameViewModel.NameActions.GALLERY ->{
                    val intent = Intent()
                    intent.type = "image/*"
                    intent.action = Intent.ACTION_GET_CONTENT
                    resultLauncher.launch(intent)
                }
            }
        })

        selected.observe(this@NameFragment, Observer {
            binding.layoutNext.isEnabled = it
        })

        name.observe(this@NameFragment, Observer {
            if(binding.etName.text.isNotEmpty() && birth.value !=  null){
                trueSelected()
            }else{
                falseSelected()
            }
        })

        birth.observe(this@NameFragment, Observer {
            if(binding.etName.text.isNotEmpty() && birth.value !=  null){
                trueSelected()
            }else{
                falseSelected()
            }
        })

    }






    private fun getPath(context: Context?, uri: Uri): String? {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(
                context,
                uri
            )
        ) {
            // ExternalStorageProvider
            if (RealPathUtil.isExternalStorageDocument(uri)) {
                Log.d("asdasdqwe1", uri.toString())
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getDataDirectory().toString() + "/" + split[1]
                } else {
                    Toast.makeText(
                        context,
                        "Could not get file path. Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (RealPathUtil.isDownloadsDocument(uri)) {
                Log.d("asdasdqwe2", uri.toString())
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return RealPathUtil.getDataColumn(context, contentUri, null, null)
            } else if (RealPathUtil.isMediaDocument(uri)) {
                Log.d("asdasdqwe3", uri.toString())
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                contentUri = if ("image" == type) {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                } else {
                    MediaStore.Files.getContentUri("external")
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                    split[1]
                )
                return RealPathUtil.getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            Log.d("asdasdqwe4-1", uri.toString())
            Log.d("asdasdqwe4", RealPathUtil.getDataColumn(context, uri, null, null))
            return RealPathUtil.getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            Log.d("asdasdqwe5", uri.path!!)
            return uri.path
        }
        return null
    }


}