package id.shobrun.stikieventorganizer.ui.myparticipants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.databinding.FragmentParticipantsBinding
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantAdapter
import id.shobrun.stikieventorganizer.utils.FakeData.fakeParticipants
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.fragment_participants.*
import kotlinx.android.synthetic.main.fragment_participants.fabAdd
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class MyParticipantsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<MyParticipantsViewModel> { viewModelFactory }

    private lateinit var binding: FragmentParticipantsBinding

    private lateinit var participantsAdapter : RecyclerParticipantAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_participants,container,false)
        with(binding){
            lifecycleOwner = this@MyParticipantsFragment
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        participantsAdapter = RecyclerParticipantAdapter(ArrayList())
        participantsAdapter.setItemListener {
            /**
             * When Click Item
             */
            rvParticipants.snackbar(it.participant_email)
        }
        viewModel.postUserId(2)
        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        binding.rvParticipants.addItemDecoration(dividerItemDecoration)
        binding.rvParticipants.adapter = participantsAdapter
        fabAdd.setOnClickListener{
            it.snackbar("Fab")
        }
    }
}