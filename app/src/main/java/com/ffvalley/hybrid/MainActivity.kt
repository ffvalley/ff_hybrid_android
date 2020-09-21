package com.ffvalley.hybrid

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val CHANNEL_ID: String = "HNPS_Handlers"
    private lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebView = findViewById(R.id.web_view)
        findViewById<Button>(R.id.btn01).setOnClickListener(this)
        findViewById<Button>(R.id.btn02).setOnClickListener(this)

        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun initWebView() {
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.displayZoomControls = false
        mWebView.settings.builtInZoomControls = true
        mWebView.settings.useWideViewPort = true
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.setSupportZoom(true)

        mWebView.settings.defaultTextEncodingName = "utf-8"

        // 存储相关
        mWebView.settings.allowContentAccess = true
        mWebView.settings.domStorageEnabled = true
        mWebView.settings.setAppCacheEnabled(true)
        mWebView.settings.savePassword = false
        mWebView.settings.saveFormData = false

        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }

        mWebView.addJavascriptInterface(JavaScripService(), CHANNEL_ID)

        val url = "file:///android_asset/index.html"
        mWebView.loadUrl(url)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn01 -> {
                WebViewUtil.sendTo(mWebView, "javascript:javacalljs()", object : JsReceiveCallback {
                    override fun onJsReceive(value: String?) {
                        println(value)
                    }
                })
            }
            R.id.btn02 -> {
                WebViewUtil.sendTo(
                    mWebView,
                    "javascript:javacalljswithargs('" + "我是Android传进来的数据， 有参" + "')",
                    object : JsReceiveCallback {
                        override fun onJsReceive(value: String?) {
                            println(value)
                        }
                    }
                )
            }
        }
    }
}