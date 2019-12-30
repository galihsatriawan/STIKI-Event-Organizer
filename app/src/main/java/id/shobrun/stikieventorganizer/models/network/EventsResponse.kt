package id.shobrun.stikieventorganizer.models.network

import id.shobrun.stikieventorganizer.models.NetworkResponseModel
import id.shobrun.stikieventorganizer.models.entity.Event

class EventsResponse (
    val result : List<Event>,
    val status : String,
    val message : String
): NetworkResponseModel