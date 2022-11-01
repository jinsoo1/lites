package com.theshine.android.lites.ui.view.login

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.kakao.sdk.auth.network.RxAuthOperations
import com.kakao.sdk.common.model.ApiError
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.rx
import com.theshine.android.lites.R
import com.theshine.android.lites.base.App.Companion.toast
import com.theshine.android.lites.base.BaseVmActivity
import com.theshine.android.lites.data.local.UserLoginLocalDataSource
import com.theshine.android.lites.data.remote.model.response.UserResponse
import com.theshine.android.lites.data.remote.source.AuthDataSource
import com.theshine.android.lites.databinding.ActivityLoginBinding
import com.theshine.android.lites.util.EventObserver
import com.theshine.android.lites.util.ext.onUI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class LoginActivity: BaseVmActivity<ActivityLoginBinding>(
    R.layout.activity_login,
    LoginViewModel::class.java
) {

    override val viewModel by lazy { vm as LoginViewModel }
    override val toolbarId = 0

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    //구글로그인 초기설정
    private lateinit var gso : GoogleSignInOptions
    private lateinit var googleSignInIntent : GoogleSignInClient

    private val authDataSource: AuthDataSource by inject()
    private val userLoginLocalDataSource: UserLoginLocalDataSource by inject()

    override fun initActivity() {


        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("408063195293-84uhokupbenf7au3v1dd0qajn8hv85pj.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInIntent = GoogleSignIn.getClient(this@LoginActivity, gso)
        signOut()

        resultGoogleLogin()

        viewModel.setObserves()

        val keyHash = Utility.getKeyHash(this)
        Log.d("로그인","해시 키 값 : ${keyHash}")

    }

    fun LoginViewModel.setObserves(){

        action.observe(lifecycleOwner, EventObserver{
            when(it){
                LoginViewModel.LoginActions.KAKAO -> {
                    //카카오 로그인
                    loginKakao()
                    Log.d("테스트", "테스트")
                }

                LoginViewModel.LoginActions.GOOGLE ->{
                    //구글로그인
                    loginGoogle()
                }

                LoginViewModel.LoginActions.LOGIN ->{
                    //로그인성공

                }
            }
        })
    }

    private fun loginKakao(){
        Log.d("테스트", "테스트123")
        Single.just(UserApiClient.instance.isKakaoTalkLoginAvailable(this))
            .flatMap { available ->
                if (available) UserApiClient.rx.loginWithKakaoTalk(this)
                else UserApiClient.rx.loginWithKakaoAccount(this)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                UserApiClient.rx.me()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ user ->
                        user.kakaoAccount?.email?.run {
                            Log.d("kakaoEmail", user.kakaoAccount!!.email!!)
                            authDataSource
                                .loginByKaKao(user.id.toString(), user.kakaoAccount?.email!!)
                                .subscribe({
                                    Log.d("kakaoAccount1", it.toString())
                                    onLoginSuccess(it)
                                },{
                                    Log.d("kakaoAccount1 E", it.toString())
                                })
                                .addTo(viewModel.compositeDisposable)
                        } ?: scopesKakao()
                    }, { error ->
                        error.printStackTrace()
                    })
                    .addTo(viewModel.compositeDisposable)
            }, { error ->
                Log.d("kakaoE", error.toString())
                error.printStackTrace()
            })
            .addTo(viewModel.compositeDisposable)

    }

    // 카카오 미설치 기기에서 이메일 수집 추가하는 함수
    fun scopesKakao(){
        UserApiClient.rx.me()
            .flatMap { user ->

                var scopes = mutableListOf<String>()

                if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
                if (user.kakaoAccount?.birthdayNeedsAgreement == true) { scopes.add("birthday") }
                if (user.kakaoAccount?.birthyearNeedsAgreement == true) { scopes.add("birthyear") }
                if (user.kakaoAccount?.genderNeedsAgreement == true) { scopes.add("gender") }
                if (user.kakaoAccount?.phoneNumberNeedsAgreement == true) { scopes.add("phone_number") }
                if (user.kakaoAccount?.profileNeedsAgreement == true) { scopes.add("profile") }
                if (user.kakaoAccount?.ageRangeNeedsAgreement == true) { scopes.add("age_range") }
                if (user.kakaoAccount?.ciNeedsAgreement == true) { scopes.add("account_ci") }

                if (scopes.count() > 0) {
                    Log.d("scopes", "사용자에게 추가 동의를 받아야 합니다.")

                    // OpenID Connect 사용 시
                    // scope 목록에 "openid" 문자열을 추가하고 요청해야 함
                    // 해당 문자열을 포함하지 않은 경우, ID 토큰이 재발급되지 않음
                    // scopes.add("openid")

                    // scope 목록을 전달하여 InsufficientScope 에러 생성
                    Single.error(ApiError.fromScopes(scopes))
                }
                else {
                    Single.just(user)
                }
            }
            .retryWhen(
                // InsufficientScope 에러에 대해 추가 동의 후 재요청
                RxAuthOperations.instance.incrementalAuthorizationRequired(this)
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                user.kakaoAccount?.email?.run {
                    Log.d("kakaoEmail", user.kakaoAccount!!.email!!)
                    authDataSource
                        .loginByKaKao(user.id.toString(), user.kakaoAccount?.email!!)
                        .subscribe({
                            Log.d("kakaoAccount", it.toString())
                            onLoginSuccess(it)
                        },{
                            Log.d("kakaoAccountE", it.toString())
                        })
                        .addTo(viewModel.compositeDisposable)

                } ?: toast("카카오 로그인 실패")
            }, { error ->
                Log.e("scopes", "사용자 정보 요청 실패", error)
            })
            .addTo(viewModel.compositeDisposable)
    }

    private fun loginGoogle(){
        val signInIntent = googleSignInIntent.signInIntent

        resultLauncher.launch(signInIntent)

    }

    private fun resultGoogleLogin(){

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            Log.d("account 1",result.resultCode.toString())
            Log.d("account 1",result.data.toString())

            if(result.resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try{
                    val account = task.getResult(ApiException::class.java)
                    Log.d("account",result.resultCode.toString())
                    authDataSource
                        .loginByGoogle(account.id ?: "", account.email ?: "")
                        .subscribe({
                            onLoginSuccess(it)
                        },{
                            it.printStackTrace()
                        })
                        .addTo(viewModel.compositeDisposable)
                }catch (e: ApiException){
                    toast("구글로그인 실패")
                }
            }
        }
    }

    //구글로그인 확인후 로그아웃
    private fun signOut() {
        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this@LoginActivity)
        if(googleSignInAccount != null){
            googleSignInIntent.signOut()
                .addOnCompleteListener(this, OnCompleteListener<Void?> {
                    toast("구글 로그아웃")
                })
        }
    }

    //기기에 유저정보 저장
    private fun onLoginSuccess(response: UserResponse) {
        userLoginLocalDataSource.saveLoginInfo(response.user)
        userLoginLocalDataSource.saveAccessToken(response.accessToken)
        userLoginLocalDataSource.saveRefreshToken(response.refreshToken)

        viewModel.successLogin()
    }
}