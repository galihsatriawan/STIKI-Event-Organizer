package id.shobrun.stikieventorganizer.ui.user.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityRegisterBinding
import id.shobrun.stikieventorganizer.ui.user.login.LoginActivity
import kotlinx.coroutines.Delay
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class RegisterActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel : RegisterViewModel by viewModels { viewModelFactory }
    lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        with(binding){
            lifecycleOwner = this@RegisterActivity
            vm = viewModel
        }
        viewModel.snackbarText.observe(this, Observer {
            it?.let {
                binding.root.snackbar(it)
            }
        })
        viewModel.isSuccess.observe(this, Observer {
            it?.let {
                if(it){
                    val login = intentFor<LoginActivity>()
                    startActivity(login)
                    finish()
                }
            }
        })
        viewModel.loginAction.observe(this, Observer {
            it?.let {

                val login = intentFor<LoginActivity>()
                startActivity(login)
                finish()
            }
        })
    }
}
