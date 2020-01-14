package id.shobrun.stikieventorganizer.ui.user.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityLoginBinding
import id.shobrun.stikieventorganizer.ui.MainActivity
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    val viewModel: LoginViewModel by viewModels{viewModelFactory }
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        with(binding){
            lifecycleOwner = this@LoginActivity
            vm = viewModel
        }
        viewModel.snackbarText.observe(this, Observer {
            if(it!=null)
                binding.root.snackbar(it)
        })
        viewModel.isSuccess.observe(this, Observer {
            it?.let {
                if(it) {
                    val mainContent = intentFor<MainActivity>()
                    startActivity(mainContent)
                    finish()
                }
            }
        })
    }
}
