package id.shobrun.stikieventorganizer.ui.invitations.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityInvitationDetailBinding
import javax.inject.Inject

class InvitationDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<InvitationDetailViewModel> {viewModelFactory}
    companion object{
        const val EXTRA_INVITATION = "extra_invitation"
    }
    lateinit var binding : ActivityInvitationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_invitation_detail)

        with(binding){
            lifecycleOwner = this@InvitationDetailActivity
            vm = viewModel
        }

        viewModel.postEventId("1")
        viewModel.postInvitationId(1)

    }
}
