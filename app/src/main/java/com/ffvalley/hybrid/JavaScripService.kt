package com.ffvalley.hybrid

import android.webkit.JavascriptInterface
import android.widget.Toast


/**
 * @Description:
 * @Author: yjlove
 * @CreateDate: 2020/9/21
 */

class JavaScripService() {

    @JavascriptInterface
    fun toast() {
        Toast.makeText(App.instance, "我是Android本地的数据，无参", Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun toast(msg: String?) {
        Toast.makeText(App.instance, msg, Toast.LENGTH_SHORT).show()
    }
}