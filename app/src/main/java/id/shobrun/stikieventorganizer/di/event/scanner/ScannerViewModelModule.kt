package id.shobrun.stikieventorganizer.di.event.scanner

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myevents.scanner.ScannerViewModel

@Module
abstract class ScannerViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ScannerViewModel::class)
    abstract fun bindScannerVM(scannerViewModel: ScannerViewModel): ViewModel
}