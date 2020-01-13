package com.liu.maji.ui.webview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import cn.com.liu.maji.R
import com.liu.maji.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.fragment_device.*
import kotlinx.android.synthetic.main.title.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        tv_add.visibility = View.INVISIBLE
        tv_title.text = ""
        val url = intent.getStringExtra("htmlUrl")
        val settings = webView.settings
        settings.run {
            //支持JS
            javaScriptEnabled = true
            //自适应屏幕,二者结合使用
            //图片调整到适合webview的大小
            useWideViewPort = true
            //webview页面缩放至屏幕大小
            loadWithOverviewMode = true

            //缩放操作
            setSupportZoom(true)
            //设置内置的控件缩放
            builtInZoomControls = true
            //隐藏原生的缩放控件
            displayZoomControls = false
        }

        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView.webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                tv_title.text = title

            }
        }

        webView.loadUrl(url)
        iv_back.setOnClickListener { this.finish() }
    }

    override fun onDestroy() {
        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webView.clearHistory()
        webView.destroy()
        super.onDestroy()
    }

}