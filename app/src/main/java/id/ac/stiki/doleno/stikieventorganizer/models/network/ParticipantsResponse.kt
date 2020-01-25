package id.ac.stiki.doleno.stikieventorganizer.models.network

import id.ac.stiki.doleno.stikieventorganizer.models.NetworkResponseModel
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Participant

class ParticipantsResponse(
    var result: List<Participant>,
    var status: String,
    var message: String
) : NetworkResponseModel