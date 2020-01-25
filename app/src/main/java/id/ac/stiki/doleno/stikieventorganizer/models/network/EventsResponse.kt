package id.ac.stiki.doleno.stikieventorganizer.models.network

import id.ac.stiki.doleno.stikieventorganizer.models.NetworkResponseModel
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Event

class EventsResponse(
    var result: List<Event>,
    var status: String,
    var message: String
) : NetworkResponseModel