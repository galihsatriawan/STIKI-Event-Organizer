package id.shobrun.stikieventorganizer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.ui.user.login.LoginActivity
import org.jetbrains.anko.intentFor

class SplashScreen : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private val loadingTime = 4000L // 3 Seconds
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        progressBar = findViewById(R.id.progressBar)
        Thread(Runnable {
            doProgress()
            goToMain()
        }).start()

    }
    fun doProgress(){
        var progress = 0
        Thread.sleep(1000)
        val max = 100
        val cnt =90

        while (progress <= max) {
            try {
                Thread.sleep(loadingTime/cnt)
                progressBar.setProgress(progress)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            progress += (max/cnt)
        }
    }
    private fun goToMain(){
        val mainContent = intentFor<LoginActivity>()
        startActivity(mainContent)
        finish()
    }
}
