package id.ac.stiki.doleno.stikieventorganizer.ui.profile

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import id.ac.stiki.doleno.stikieventorganizer.R
import id.ac.stiki.doleno.stikieventorganizer.utils.Constants.Companion.HELP_URL

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        var web: WebView = findViewById(R.id.webView)
        web.loadUrl(HELP_URL)
    }
}
