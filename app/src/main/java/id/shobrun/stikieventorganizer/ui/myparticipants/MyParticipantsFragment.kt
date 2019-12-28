package id.shobrun.stikieventorganizer.ui.myparticipants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.shobrun.stikieventorganizer.R

class MyParticipantsFragment : Fragment() {

    private lateinit var myParticipantsViewModel: MyParticipantsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myParticipantsViewModel =
                ViewModelProviders.of(this).get(MyParticipantsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_participants, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        myParticipantsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}