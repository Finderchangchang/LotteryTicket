package gy.lotteryticket.ui

import android.app.AlertDialog
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.*
import com.arialyy.frame.module.AbsModule
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityGameBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.TagModel
import kotlinx.android.synthetic.main.activity_web.*

class CJActivity : BaseActivity<ActivityGameBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        success as NormalRequest<List<TagModel>>
        if (success.code == 0 && success.obj!!.isNotEmpty()) {
            var a = success.obj
            var mode = success.obj!![0]
            var urk = mode.con + "?sign=" + Utils.string2MD5("signStr=${sp.http_random}&key=${sp.http_key}&timeStamp=${Utils.dateToStamp()}") +
                    "&uid=" + Utils.getCache(sp.user_id)
            load_web(urk)
        }
    }

    fun load_web(u: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        CookieSyncManager.createInstance(this)
        val cookieManager = CookieManager.getInstance()
        val cookieString = Utils.getCache("all_session")
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(u, cookieString)
        CookieSyncManager.getInstance().sync()
        web.loadUrl(u)
        val webSetting = web.settings
        webSetting.javaScriptEnabled = true
        webSetting.useWideViewPort = true;
        webSetting.loadWithOverviewMode = true;
        //.headers("cookie", Utils.getCache(key.KEY_SESSIONID))
        //设置WebChromeClient
        web.setWebChromeClient(object : WebChromeClient() {
            //处理javascript中的alert
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                //构建一个Builder来显示网页中的对话框
                val builder = AlertDialog.Builder(this@CJActivity)
                builder.setTitle("提示")
                builder.setMessage(message)
                builder.setPositiveButton("确定") { dialog, which ->
                    result.confirm()
                }

                builder.setCancelable(false)
                builder.create()
                builder.show()
                return true
            }

        })
        web.setWebViewClient(object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }
        })
    }

    override fun onError(result: Int, error: Any?) {
        var s = ""
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_web
    }

    var index = 0
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        title_bar.setLeftClick { finish() }
        index = intent.getIntExtra("index", 0)
        if (index == 7) {
            title_bar.center_str = "抽奖"
        } else if (index == 5) {
            title_bar.center_str = "支付宝支付"
        } else {
            title_bar.center_str = "微信支付"
        }
        getModule(MainModule::class.java, this).get_jb_main(index)
    }

}