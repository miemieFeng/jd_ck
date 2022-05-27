package cc.miemie.jd;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //实例化WebView对象
        webview = new WebView(this);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //支持alert的方法
        webview.setWebChromeClient(new WebChromeClient());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        try {
//            WebSettings settings = webview.getSettings();
//            settings.setDomStorageEnabled(true);//开启DOM
            String url = "https://plogin.m.jd.com/login/login?appid=445&returnurl=https%3A%2F%2Fm.healthjd.com%2Fs%2Fmy%3Fyyjdfromflag%3Dlogin";
            //设置打开的页面地址
            webview.loadUrl(url);
            CookieManager cookieManager = CookieManager.getInstance();

            String cookieStr = cookieManager.getCookie(url);
            System.out.println(cookieStr);
            System.out.println("====================>");
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("ck", cookieStr);
            cm.setPrimaryClip(mClipData);
            Toast.makeText(this,"已经复制",Toast.LENGTH_LONG).show();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


        setContentView(webview);
//        button = new Button(this);
//        button.setHeight(3);
//        button.setWidth(7);
//        setContentView(button);

    }
}