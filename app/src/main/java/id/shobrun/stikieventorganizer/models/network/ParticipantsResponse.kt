package id.shobrun.stikieventorganizer.models.network

import id.shobrun.stikieventorganizer.models.NetworkResponseModel
import id.shobrun.stikieventorganizer.models.entity.Participant

class ParticipantsResponse (
    val result: List<Participant>,
    val status : String,
    val message : String
): NetworkResponseModel