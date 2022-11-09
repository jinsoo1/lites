package com.theshine.android.lites.ui.view.main.search

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.theshine.android.lites.R
import com.theshine.android.lites.base.BaseRecyclerAdapter
import com.theshine.android.lites.base.BaseVmFragment
import com.theshine.android.lites.data.common.model.SearchData
import com.theshine.android.lites.databinding.FragmentMypageBinding
import com.theshine.android.lites.databinding.FragmentSearchBinding
import com.theshine.android.lites.databinding.ItemSearchAnimalBinding
import com.theshine.android.lites.ui.view.main.myPage.MyPageViewModel
import com.theshine.android.lites.util.EventObserver

class SearchFragment : BaseVmFragment<FragmentSearchBinding>(
    R.layout.fragment_search,
    SearchViewModel::class.java
) {
    override val viewModel by lazy { vm as SearchViewModel }

    override fun initFragment() {

        binding.apply {
            rvHospitalList.apply {
                adapter = AnimalHospitalAdapter(viewModel)

                addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val lastVisibleItemPosition =
                            (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                        val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                        if(viewModel.searchData.value!!.size == 15 || viewModel.searchData.value!!.size == 30){
                            if(lastVisibleItemPosition == itemTotalCount){
                                if(viewModel.page.value!! < 3){
                                    viewModel.page.value?.plus(1)
                                    //viewModel.searchAnimalHospital()
                                }
                            }
                        }
                    }
                })
            }

        }

        BottomSheetBehavior.from(binding.sheetKm).state = BottomSheetBehavior.STATE_HIDDEN

        viewModel.setObserves()

    }

    private fun SearchViewModel.setObserves(){
        action.observe(this@SearchFragment, EventObserver{
            when(it){
                SearchViewModel.SearchAction.KM_OPEN ->{
                    if (BottomSheetBehavior.from(binding.sheetKm).state == BottomSheetBehavior.STATE_HIDDEN ||
                        BottomSheetBehavior.from(binding.sheetKm).state == BottomSheetBehavior.STATE_COLLAPSED
                    ) {
                        BottomSheetBehavior.from(binding.sheetKm).state =
                            BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
                }

                SearchViewModel.SearchAction.KM_CLOSE ->{
                    BottomSheetBehavior.from(binding.sheetKm).state = BottomSheetBehavior.STATE_HIDDEN
                }
            }

        })

        page.observe(this@SearchFragment, Observer {
            viewModel.searchAnimalHospital()
        })

        km.observe(this@SearchFragment, Observer {

        })
    }
}

class AnimalHospitalAdapter(vm : SearchViewModel) : BaseRecyclerAdapter<SearchData, ItemSearchAnimalBinding>(
    R.layout.item_search_animal, vm
)