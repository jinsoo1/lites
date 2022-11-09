package com.theshine.android.lites.ui.view.main.search

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App
import com.theshine.android.lites.base.BaseViewModel
import com.theshine.android.lites.data.common.model.SearchData
import com.theshine.android.lites.data.remote.api.KakaoApi
import com.theshine.android.lites.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SearchViewModel: BaseViewModel() {
    val KAKAO_BASE_URL = "https:/dapi.kakao.com/"
    val API_KEY = "KakaoAK " + App.getString(R.string.rest_app_key)

    var kakaoApis = getClient()
    var getSearchItem = kakaoApis!!.create(KakaoApi::class.java)

    private val _action : MutableLiveData<Event<SearchAction>> = MutableLiveData()
    val action : LiveData<Event<SearchAction>> get() = _action

    private val _km : MutableLiveData<Int> = MutableLiveData(1000)
    val km : LiveData<Int> get() = _km

    private val _isSelected : MutableLiveData<Int> = MutableLiveData(1000)
    val isSelected : LiveData<Int> get() = _isSelected

    val arraySearch : MutableLiveData<ArrayList<SearchData>> = MutableLiveData(arrayListOf())
    private val _searchData : MutableLiveData<List<SearchData>> = MutableLiveData(listOf())
    val searchData : LiveData<List<SearchData>> get() = _searchData

    private val _page : MutableLiveData<Int> = MutableLiveData(1)
    val page : LiveData<Int> get() = _page

    private var retrofit : Retrofit? = null

    fun searchAnimalHospital(){
        getSearchItem.keywordSearch(
            API_KEY,
            "동물병원",
            "37.597302541999625",
            "127.05584834904478",
            _km.value!!,
            _page.value!!
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.documents.map {
                    SearchData(
                        it.id,
                        it.place_name,
                        it.category_name,
                        it.category_group_code,
                        it.category_group_name,
                        it.phone,
                        it.address_name,
                        it.road_address_name,
                        it.x,
                        it.y,
                        it.place_url,
                        it.distance+"m"
                    )
                }
            }
            .subscribe({
                arraySearch.value!!.addAll(it)
                _searchData.value = arraySearch.value
                Log.d("keywordSearch", it.toString())
            }, {
                Log.d("keywordSearch E", it.toString())
            })
            .addTo(compositeDisposable)
    }

    fun setKmSelected(int: Int){
        _searchData.value = listOf()
        arraySearch.value = arrayListOf()
        _km.value = int
        _isSelected.value = int
        pageInitialization()

    }

    fun setInitialization(){
        _searchData.value = listOf()
        arraySearch.value = arrayListOf()
        _km.value = 1000
        _isSelected.value = 1000
        pageInitialization()
    }

    fun actionKmOpen(){
        _action.value = Event(SearchAction.KM_OPEN)
    }
    fun actionKmClose(){
        _action.value = Event(SearchAction.KM_CLOSE)
    }
    fun pageInitialization(){
        _page.value = 1
    }

    fun getClient() : Retrofit? {

        var gson = GsonBuilder()
            .setLenient()
            .create()

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(KAKAO_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit

    }


    enum class SearchAction{
        KM_OPEN, KM_CLOSE
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bindSearchAnimal")
        fun bindSearchAnimal(rv: RecyclerView, list: List<SearchData>) {
            val adapter = rv.adapter as AnimalHospitalAdapter
            adapter.let {
                it.updateItems(list)
            }
        }
    }

}