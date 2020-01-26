package id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import id.ac.stiki.doleno.stikieventorganizer.R
import id.ac.stiki.doleno.stikieventorganizer.databinding.FragmentEventSummaryBinding
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailActivity.Companion.EXTRA_EVENT
import timber.log.Timber
import javax.inject.Inject

class EventSummaryFragment : DaggerFragment() {

    companion object {
        fun newInstance() = EventSummaryFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: EventSummaryViewModel by viewModels { viewModelFactory }
    val viewModelMain : EventDetailMainViewModel by viewModels { viewModelFactory }
    lateinit var binding: FragmentEventSummaryBinding
    private var eventId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_summary, container, false)
        eventId = arguments?.getString(EXTRA_EVENT)
        with(binding) {
            lifecycleOwner = this@EventSummaryFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.postEventId(eventId ?: EventDetailActivity.currentEventId)
        Timber.d("${EventDetailFragment.TAG} MainVM EventSummary ${viewModelMain.hashCode()} - ${viewModel.hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        viewModel.postEventId(eventId ?: EventDetailActivity.currentEventId)
    }

}
