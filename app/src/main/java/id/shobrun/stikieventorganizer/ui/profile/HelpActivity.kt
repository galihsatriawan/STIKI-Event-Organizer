package id.shobrun.stikieventorganizer.ui.profile

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import id.shobrun.stikieventorganizer.R

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        var web: WebView = findViewById(R.id.webView)
        web.loadUrl("file:///android_asset/Help.html")

    }
}
