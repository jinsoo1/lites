package com.theshine.android.lites.ui.view.login.terms

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.theshine.android.lites.R

class TermsActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)


        val webView = findViewById<WebView>(R.id.webView)
        webView.apply {
            webViewClient = WebViewClient()
            if(intent.getStringExtra("이용약관") == "서비스이용약관"){
                loadUrl("file:///android_asset/theshine_service_2.html")
            }else{
                loadUrl("file:///android_asset/theshine_service_1.html")
            }

        }

    }
}