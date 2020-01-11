package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityEventDetailBinding
import javax.inject.Inject

class EventDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<EventDetailViewModel> { viewModelFactory }

    lateinit var binding : ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_event_detail)

        with(binding){
            lifecycleOwner = this@EventDetailActivity
            vm = viewModel
        }

        viewModel.postEventId("1")

    }
}
