package id.shobrun.stikieventorganizer.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import id.shobrun.stikieventorganizer.extensions.bindResource
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantAdapter

@BindingAdapter("participants")
fun bindParticipantsList(view: RecyclerView, result: Resource<List<Participant>>?) {
    view.bindResource(result) {
        (view.adapter as RecyclerParticipantAdapter).setItems(it?.data)

    }
}