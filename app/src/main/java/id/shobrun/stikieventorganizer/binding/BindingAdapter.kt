package id.shobrun.stikieventorganizer.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import id.shobrun.stikieventorganizer.extensions.bindResource
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerEventAdapter
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerInvitationAdapter
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantAdapter

@BindingAdapter("liveResourceList")
fun <T> bindParticipantsList(view: RecyclerView, result: Resource<List<T>>?) {
    view.bindResource(result) {
        when(view.adapter){
            is RecyclerParticipantAdapter -> {
                val res =  it?.data as (List<Participant>)
                (view.adapter as RecyclerParticipantAdapter).setItems(res)
            }
            is RecyclerInvitationAdapter -> {
                val res = it?.data as (List<Invitation>)
                (view.adapter as RecyclerInvitationAdapter).setItems(res)
            }
            is RecyclerEventAdapter -> {
                val res = it?.data as (List<Event>)
                (view.adapter as RecyclerEventAdapter).setItems(res)
            }
        }


    }
}