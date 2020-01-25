package id.ac.stiki.doleno.stikieventorganizer.di.user.login

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.user.login.LoginViewModel

@Module
abstract class LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginVM(loginViewModel: LoginViewModel): ViewModel
}