package id.shobrun.stikieventorganizer.binding

import android.text.Editable
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
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
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantEventAdapter
import id.shobrun.stikieventorganizer.utils.Helper.generatedCode

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
            is RecyclerInvitationAdapter-> {
                val res = it.data as (List<Invitation>)
                (view.adapter as RecyclerInvitationAdapter).setItems(res)
            }
            is RecyclerParticipantEventAdapter-> {
                val res = it.data as (List<Invitation>)
                (view.adapter as RecyclerParticipantEventAdapter).setItems(res)
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
/**
 * Binding Invitation Detail
 */
@BindingAdapter("eventName")
fun bindEventName(view : TextView, resource: Resource<Event,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.event_name)
    }
}
@BindingAdapter("eventDescription")
fun bindEventDesc(view : TextView, resource: Resource<Event,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.event_description)
    }
}
@BindingAdapter("eventDate")
fun bindEventDate(view : TextView, resource: Resource<Event,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.event_date)
    }
}

@BindingAdapter("eventLocation")
fun bindEventLocation(view : TextView, resource: Resource<Event,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.event_location)
    }
}


@BindingAdapter("eventInviter")
fun bindEventInviter(view : TextView, resource: Resource<Event,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.user_username)
    }
}


@BindingAdapter("eventCp")
fun bindEventCp(view : TextView, resource: Resource<Event,Any>?){
    view.bindResource(resource){
        view.setText(it.data?.event_cp)
    }
}

@BindingAdapter("invitationQr")
fun bindEventLocation(view : ImageView, resource: Resource<Invitation,Any>?){
    view.bindResource(resource){
        it.data?.let {
            val gson = Gson()
            val data = gson.toJson(it)
            view.setImageBitmap(generatedCode(data,BarcodeFormat.QR_CODE))
        }

    }
}