package id.ac.stiki.doleno.stikieventorganizer.di.event.scanner

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.scanner.ScannerViewModel

@Module
abstract class ScannerViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ScannerViewModel::class)
    abstract fun bindScannerVM(scannerViewModel: ScannerViewModel): ViewModel
}