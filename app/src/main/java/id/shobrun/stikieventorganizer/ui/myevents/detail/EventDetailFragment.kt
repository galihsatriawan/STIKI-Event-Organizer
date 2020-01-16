package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment

import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.FragmentEventDetailBinding
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailActivity.Companion.EXTRA_EVENT
import id.shobrun.stikieventorganizer.ui.myevents.scanner.ScannerActivity
import kotlinx.android.synthetic.main.fragment_events.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.intentFor
import javax.inject.Inject

class EventDetailFragment : DaggerFragment() {

    companion object {
        fun newInstance() = EventDetailFragment()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : EventDetailViewModel by viewModels { viewModelFactory }
    private lateinit var binding : FragmentEventDetailBinding
    var event: Event? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_event_detail,container,false)
        if(arguments?.get(EXTRA_EVENT) !=null){
            event = arguments!![EXTRA_EVENT]  as Event
        }

        with(binding){
            lifecycleOwner = this@EventDetailFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        viewModel.postEventId(event?.event_id)
        viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty())  binding.root.snackbar(it).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_event_detail, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when(item.itemId){
            R.id.scan ->{
                val scan = intentFor<ScannerActivity>()
                startActivity(scan)
                true
            }

            else -> true
        }
    }
}
