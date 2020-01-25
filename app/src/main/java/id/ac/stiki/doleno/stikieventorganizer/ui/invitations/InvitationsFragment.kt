package id.ac.stiki.doleno.stikieventorganizer.ui.invitations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.android.support.DaggerFragment
import id.ac.stiki.doleno.stikieventorganizer.R
import id.ac.stiki.doleno.stikieventorganizer.databinding.FragmentInvitationsBinding
import id.ac.stiki.doleno.stikieventorganizer.ui.adapter.RecyclerInvitationAdapter
import id.ac.stiki.doleno.stikieventorganizer.ui.invitations.detail.InvitationDetailActivity
import org.jetbrains.anko.support.v4.intentFor
import javax.inject.Inject

class InvitationsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: InvitationsViewModel by viewModels { viewModelFactory }

    private lateinit var invitationAdapter: RecyclerInvitationAdapter

    companion object {
        fun newInstance() = InvitationsFragment()
    }

    private lateinit var binding: FragmentInvitationsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invitations, container, false)
        with(binding) {
            lifecycleOwner = this@InvitationsFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invitationAdapter = RecyclerInvitationAdapter(ArrayList())
        invitationAdapter.setItemListener { invitation ->
            val detail = intentFor<InvitationDetailActivity>(
                InvitationDetailActivity.EXTRA_INVITATION to invitation
            )
            startActivity(detail)
        }
        var dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvInvitations.addItemDecoration(dividerItemDecoration)
        binding.rvInvitations.adapter = invitationAdapter
    }
}