package id.shobrun.stikieventorganizer.binding

import android.text.Editable
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import id.shobrun.stikieventorganizer.extensions.bindResource
import id.shobrun.stikieventorganizer.extensions.gone
import id.shobrun.stikieventorganizer.extensions.visible
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerEventAdapter
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerInvitationAdapter
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantAdapter

@BindingAdapter("resourceLoading")
fun bindResourceItem(view: ProgressBar, result: Resource<Any,Any>?){
    view.bindResource(result){
        if(it.status == Status.LOADING) view.visible() else view.gone()
    }
}
@BindingAdapter("liveResourceList")
fun <T> bindParticipantsList(view: RecyclerView, result: Resource<List<T>,Any>?) {
    view.bindResource(result) {
        when(view.adapter){
            is RecyclerParticipantAdapter -> {
                val res =  it.data as (List<Participant>)
                (view.adapter as RecyclerParticipantAdapter).setItems(res)
            }
            is RecyclerInvitationAdapter -> {
                val res = it.data as (List<Invitation>)
                (view.adapter as RecyclerInvitationAdapter).setItems(res)
            }
            is RecyclerEventAdapter -> {
                val res = it.data as (List<Event>)
                (view.adapter as RecyclerEventAdapter).setItems(res)
            }
        }
    }
}
/*
Binding Participant Detail
 */
@BindingAdapter("participantName")
fun bindParticipantName(view : TextInputEditText, resource: Resource<Participant,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.participant_name)
    }
}
