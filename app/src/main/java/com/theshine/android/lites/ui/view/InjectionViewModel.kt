package com.theshine.android.lites.ui.view

import com.theshine.android.lites.ui.view.info.InfoViewModel
import com.theshine.android.lites.ui.view.info.bcs.BcsViewModel
import com.theshine.android.lites.ui.view.info.namebirth.NameViewModel
import com.theshine.android.lites.ui.view.info.ingredient.IngredientViewModel
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
import com.theshine.android.lites.ui.view.main.home.bluetooth.BleRepository
import com.theshine.android.lites.ui.view.main.home.graph.GraphViewModel
import com.theshine.android.lites.ui.view.main.home.main.HomeMainViewModel
import com.theshine.android.lites.ui.view.main.home.weightinfo.WeightInfoViewModel
import com.theshine.android.lites.ui.view.main.home.weightinfo.ingredient.IngredientActViewModel
import com.theshine.android.lites.ui.view.main.myPage.MyPageNavViewModel
import com.theshine.android.lites.ui.view.main.myPage.MyPageViewModel
import com.theshine.android.lites.ui.view.main.myPage.activity.ActivityChatViewModel
import com.theshine.android.lites.ui.view.main.myPage.activity.ActivitySharedInfoViewModel
import com.theshine.android.lites.ui.view.main.myPage.activity.ActivityViewModel
import com.theshine.android.lites.ui.view.main.myPage.event.EventViewModel
import com.theshine.android.lites.ui.view.main.myPage.event.EventViewModels
import com.theshine.android.lites.ui.view.main.myPage.info.MyPageInfoViewModel
import com.theshine.android.lites.ui.view.main.myPage.inquiry.InquiryViewModel
import com.theshine.android.lites.ui.view.main.myPage.management.ManagementViewModel
import com.theshine.android.lites.ui.view.main.myPage.notice.NoticeViewModel
import com.theshine.android.lites.ui.view.main.myPage.profile.ProfileEditViewModel
import com.theshine.android.lites.ui.view.main.myPage.setting.SettingViewModel
import com.theshine.android.lites.ui.view.main.search.SearchViewModel
import com.theshine.android.lites.ui.view.main.search.detail.SearchDetailViewModel
import com.theshine.android.lites.ui.view.main.store.StoreViewModel
import com.theshine.android.lites.ui.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {




    //로그인 페이지 및 초기화면페이지
    viewModel { SplashViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }


    //펫 정보 페이지
    viewModel { NameViewModel() }
    viewModel { IngredientViewModel() }
    viewModel { ScaleViewModel(get()) }
    viewModel { SelectViewModel() }
    viewModel { VarietyViewModel() }
    viewModel { BcsViewModel() }
    viewModel { InfoViewModel(get()) }

    /**
     * 메인페이지
     */

    viewModel { MainViewModel() }

    //커뮤니티
    viewModel { CommunityViewModel() }
    viewModel { ChatterListViewModel() }
    viewModel { ChatterViewModel() }
    viewModel { FeedListViewModel() }
    viewModel { FeedViewModel() }
    viewModel { PostWriteViewModel() }
    viewModel { SharedInfoViewModel() }
    viewModel { SharedInfoListViewModel() }

    //홈
    viewModel { HomeViewModel() }
    viewModel { GraphViewModel(get()) }
    viewModel { HomeMainViewModel(get(), get()) }
    viewModel { WeightInfoViewModel(get()) }
    viewModel { IngredientActViewModel(get()) }

    viewModel { SearchViewModel() }
    viewModel { SearchDetailViewModel() }

    viewModel { StoreViewModel() }

    //마이페이지
    viewModel { EventViewModel(get()) }
    viewModel { MyPageInfoViewModel() }
    viewModel { InquiryViewModel() }
    viewModel { ManagementViewModel() }
    viewModel { NoticeViewModel() }
    viewModel { SettingViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { MyPageNavViewModel(get()) }
    viewModel { ActivityViewModel() }
    viewModel { ActivityChatViewModel() }
    viewModel { ActivitySharedInfoViewModel() }
    viewModel { ProfileEditViewModel(get()) }




    viewModel { EventViewModels() }

}

val repositoryModule = module{
    single{
        BleRepository()
    }
}
