package com.theshine.android.lites.ui.view

import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.bcs.BcsViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.physical.PhysicalViewModel
import com.theshine.android.lites.ui.view.info.scale.ScaleViewModel
import com.theshine.android.lites.ui.view.info.select.SelectViewModel
import com.theshine.android.lites.ui.view.info.variety.VarietyViewModel
import com.theshine.android.lites.ui.view.login.LoginViewModel
import com.theshine.android.lites.ui.view.main.MainViewModel
import com.theshine.android.lites.ui.view.main.community.CommunityViewModel
import com.theshine.android.lites.ui.view.main.community.chatter.ChatterListViewModel
import com.theshine.android.lites.ui.view.main.community.chatter.ChatterViewModel
import com.theshine.android.lites.ui.view.main.community.feed.FeedListViewModel
import com.theshine.android.lites.ui.view.main.community.feed.FeedViewModel
import com.theshine.android.lites.ui.view.main.community.post.PostWriteViewModel
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoListViewModel
import com.theshine.android.lites.ui.view.main.community.sharedInfo.SharedInfoViewModel
import com.theshine.android.lites.ui.view.main.home.HomeViewModel
import com.theshine.android.lites.ui.view.main.home.graph.GraphViewModel
import com.theshine.android.lites.ui.view.main.home.main.HomeMainViewModel
import com.theshine.android.lites.ui.view.main.home.weightinfo.WeightInfoViewModel
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.ui.view.main.myPage.MyPageViewModel
import com.theshine.android.lites.ui.view.main.myPage.event.EventViewModel
import com.theshine.android.lites.ui.view.main.myPage.info.MyPageInfoViewModel
import com.theshine.android.lites.ui.view.main.myPage.inquiry.InquiryViewModel
import com.theshine.android.lites.ui.view.main.myPage.management.ManagementViewModel
import com.theshine.android.lites.ui.view.main.myPage.notice.NoticeViewModel
import com.theshine.android.lites.ui.view.main.myPage.setting.SettingViewModel
import com.theshine.android.lites.ui.view.main.search.SearchViewModel
import com.theshine.android.lites.ui.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { CommunityViewModel() }
    viewModel { SearchViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { MyPageNavViewModel() }
    viewModel { EventViewModel() }
    viewModel { MyPageInfoViewModel() }

    viewModel { InfoViewModel() }
    viewModel { InquiryViewModel() }
    viewModel { ManagementViewModel() }
    viewModel { NoticeViewModel() }
    viewModel { SettingViewModel() }
    viewModel { LoginViewModel() }
    viewModel { NameViewModel() }
    viewModel { PhysicalViewModel() }
    viewModel { ScaleViewModel() }
    viewModel { SelectViewModel() }
    viewModel { VarietyViewModel() }
    viewModel { BcsViewModel() }
    viewModel { ChatterViewModel() }
    viewModel { FeedViewModel() }
    viewModel { SharedInfoViewModel() }
    viewModel { ChatterListViewModel() }
    viewModel { FeedListViewModel() }
    viewModel { SharedInfoListViewModel() }
    viewModel { PostWriteViewModel() }

    viewModel { GraphViewModel() }
    viewModel { HomeMainViewModel() }
    viewModel { WeightInfoViewModel() }

    viewModel { SplashViewModel(get()) }

}
