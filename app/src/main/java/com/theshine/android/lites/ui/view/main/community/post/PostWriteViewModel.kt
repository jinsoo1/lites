package com.theshine.android.lites.ui.view.main.community.post

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.util.Event
import java.io.Serializable

class PostWriteViewModel: BaseViewModel() {

    val action: MutableLiveData<Event<PostWriteActions>> = MutableLiveData()

    val selectImages : MutableLiveData<uritype> = MutableLiveData()
    var picturesLiveData = ArrayList<SelectedImages>()
    val selectedPictures = ArrayList<uritype>()
    var selectMultiImage : MutableLiveData<Boolean> = MutableLiveData(false)

    fun addPhoto(){
        action.value = Event(PostWriteActions.GALLERY)
    }

    fun selectImage(path: uritype) {
        selectImages.postValue(path)

        if (selectMultiImage.value == false) {
            if (selectedPictures.isNullOrEmpty()) {
                selectedPictures.add(0, path)
            } else {
                selectedPictures[0] = path
            }
        } else if (!selectedPictures.contains(path)) {
            if(selectedPictures.size == 10){
                toast("최대 이미지는 10장입니다.")
            }else{
                selectedPictures.add(path)
                Log.d("addImage", "add $path")
                Log.d("addImagesize", selectedPictures.size.toString())
            }
        } else {
            Log.d("addImage", "rem $path")
            selectedPictures.remove(path)
        }
        refreshSelectList()
    }

    fun refreshSelectList() {
        for (data in picturesLiveData) {
            data.index.value = 0
        }

        for (i in 0 until selectedPictures.size) {
            val path = selectedPictures[i].uri
            for (data in picturesLiveData) {
                if (data.path.value!!.uri.equals(path)) {
                    data.index.postValue(i + 1)
                    continue
                }
            }
        }
    }



    enum class PostWriteActions {
        GALLERY
    }
}

data class uritype(
    val uri : String,
    val state: Boolean
) : Serializable


data class SelectedImages(val index: MutableLiveData<Int>, val path: MutableLiveData<uritype>) {
    companion object {
        fun create(index: Int, path: uritype): SelectedImages {
            return SelectedImages(MutableLiveData(index), MutableLiveData(path))
        }
    }
}