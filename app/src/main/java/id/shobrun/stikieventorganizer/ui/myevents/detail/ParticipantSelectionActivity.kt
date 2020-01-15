package id.shobrun.stikieventorganizer.ui.myevents.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity

import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.ActivityParticipantSelectionBinding
import id.shobrun.stikieventorganizer.extensions.simpleToolbarWithHome
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantSelectionAdapter
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailActivity.Companion.EXTRA_EVENT
import kotlinx.android.synthetic.main.activity_participant_selection.*
import javax.inject.Inject

class ParticipantSelectionActivity : DaggerAppCompatActivity() {

    companion object {
        fun newInstance() = ParticipantSelectionActivity()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: ParticipantSelectionViewModel by viewModels { viewModelFactory }
    private lateinit var participantsAdapter : RecyclerParticipantSelectionAdapter
    lateinit var binding : ActivityParticipantSelectionBinding
    var event: Event? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_participant_selection)
        simpleToolbarWithHome(toolbar, "Select your participant")

        with(binding){
            vm = viewModel
            lifecycleOwner = this@ParticipantSelectionActivity
        }
        event = intent?.getParcelableExtra(EXTRA_EVENT)

        participantsAdapter = RecyclerParticipantSelectionAdapter(ArrayList())

        val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        binding.rvParticipants.addItemDecoration(dividerItemDecoration)
        binding.rvParticipants.adapter = participantsAdapter

        viewModel.postEventId(event?.event_id?:"-1")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}
