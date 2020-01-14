package id.shobrun.stikieventorganizer.ui.invitations.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityInvitationDetailBinding
import id.shobrun.stikieventorganizer.extensions.simpleToolbarWithHome
import id.shobrun.stikieventorganizer.models.entity.Invitation
import kotlinx.android.synthetic.main.activity_participant_detail.*
import javax.inject.Inject

class InvitationDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<InvitationDetailViewModel> {viewModelFactory}
    companion object{
        const val EXTRA_INVITATION = "extra_invitation"
    }
    var invitation : Invitation? = null
    lateinit var binding : ActivityInvitationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_invitation_detail)
        simpleToolbarWithHome(toolbar, "Invitation Detail")
        with(binding){
            lifecycleOwner = this@InvitationDetailActivity
            vm = viewModel
        }
        invitation = intent?.getParcelableExtra(EXTRA_INVITATION)
        viewModel.postEventId(invitation?.event_id)
        viewModel.postInvitationId(invitation?.invitation_id)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()

    }
}
