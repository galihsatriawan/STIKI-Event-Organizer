package id.shobrun.stikieventorganizer.di.user.register

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.user.register.RegisterViewModel

@Module
abstract class RegisterViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterVM(registerViewModel: RegisterViewModel): ViewModel
}