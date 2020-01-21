package id.shobrun.stikieventorganizer.models.network

import id.shobrun.stikieventorganizer.models.NetworkResponseModel
import id.shobrun.stikieventorganizer.models.entity.Participant

class ParticipantsResponse(
    var result: List<Participant>,
    var status: String,
    var message: String
) : NetworkResponseModel