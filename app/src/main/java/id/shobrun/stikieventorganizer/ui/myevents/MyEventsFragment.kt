package id.shobrun.stikieventorganizer.ui.myevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.android.support.DaggerFragment
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.FragmentEventsBinding
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerEventAdapter
import kotlinx.android.synthetic.main.fragment_events.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class MyEventsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel: MyEventsViewModel by viewModels { viewModelFactory }

    private lateinit var eventAdapter : RecyclerEventAdapter

    lateinit var binding : FragmentEventsBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_events,container,false)
        with(binding){
            lifecycleOwner = this@MyEventsFragment
            vm = viewModel
        }
        eventAdapter = RecyclerEventAdapter(ArrayList())
        eventAdapter.setItemListener {
            rvEvent.snackbar(it.event_name)
        }
        viewModel.postUserId(1)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dividerItemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        binding.rvEvent.addItemDecoration(dividerItemDecoration)
        binding.rvEvent.adapter = eventAdapter
        fabAdd.setOnClickListener{
            it.snackbar("Fab")
        }
    }
}