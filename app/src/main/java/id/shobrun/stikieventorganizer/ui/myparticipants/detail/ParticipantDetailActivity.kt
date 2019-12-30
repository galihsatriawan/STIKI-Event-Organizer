package id.shobrun.stikieventorganizer.ui.myparticipants.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityParticipantDetailBinding
import javax.inject.Inject

class ParticipantDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    val viewModel by viewModels<ParticipantDetailViewModel>{viewModelFactory}

    lateinit var binding : ActivityParticipantDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_participant_detail)
        with(binding){
            lifecycleOwner = this@ParticipantDetailActivity
            vm = viewModel
        }

        viewModel.postParticipantId(1)
    }
}
