package id.shobrun.stikieventorganizer.models.network

import id.shobrun.stikieventorganizer.models.NetworkResponseModel
import id.shobrun.stikieventorganizer.models.entity.Event

class EventsResponse (
    var result : List<Event>,
    var status : String,
    var message : String
): NetworkResponseModel