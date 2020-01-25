package id.ac.stiki.doleno.stikieventorganizer.di.user.register

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.user.register.RegisterViewModel

@Module
abstract class RegisterViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterVM(registerViewModel: RegisterViewModel): ViewModel
}