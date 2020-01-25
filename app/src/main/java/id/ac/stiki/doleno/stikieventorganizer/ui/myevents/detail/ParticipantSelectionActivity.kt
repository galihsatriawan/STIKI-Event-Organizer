package id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import id.ac.stiki.doleno.stikieventorganizer.R
import id.ac.stiki.doleno.stikieventorganizer.databinding.ActivityParticipantSelectionBinding
import id.ac.stiki.doleno.stikieventorganizer.extensions.simpleToolbarWithHome
import id.ac.stiki.doleno.stikieventorganizer.ui.adapter.RecyclerParticipantSelectionAdapter
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailActivity.Companion.EXTRA_ID_EVENT
import kotlinx.android.synthetic.main.activity_participant_selection.*
import javax.inject.Inject

class ParticipantSelectionActivity : DaggerAppCompatActivity() {

    companion object {
        fun newInstance() = ParticipantSelectionActivity()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: ParticipantSelectionViewModel by viewModels { viewModelFactory }
    private lateinit var participantsAdapter: RecyclerParticipantSelectionAdapter
    lateinit var binding: ActivityParticipantSelectionBinding
    var eventId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_participant_selection)
        simpleToolbarWithHome(toolbar, "Select your participant")

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@ParticipantSelectionActivity
        }
        eventId = intent?.getStringExtra(EXTRA_ID_EVENT)

        participantsAdapter = RecyclerParticipantSelectionAdapter(ArrayList())

        val dividerItemDecoration =
            DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        binding.rvParticipants.addItemDecoration(dividerItemDecoration)
        binding.rvParticipants.adapter = participantsAdapter
        viewModel.recyclerAdapter = participantsAdapter
        viewModel.postEventId(eventId?: EventDetailActivity.currentEventId)
        viewModel.snackbarText.observe(this, Observer {
            if (it != null) Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })
        viewModel.isSuccess.observe(this, Observer {
            if (it != null) {
                if (it) onBackPressed()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}
