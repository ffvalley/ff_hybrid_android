package com.ffvalley.hybrid;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * @Description:
 * @Author: yjlove
 * @CreateDate: 2020/9/21
 */
public class WebViewUtil {
    public static void sendTo(WebView webView, String jsCode, final JsReceiveCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(jsCode, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    callback.onJsReceive(value);
                }
            });
        } else {
            webView.loadUrl(jsCode);
        }
    }
}
