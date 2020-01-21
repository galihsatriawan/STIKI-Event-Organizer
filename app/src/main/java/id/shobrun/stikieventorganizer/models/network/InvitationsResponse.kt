package id.shobrun.stikieventorganizer.models.network

import id.shobrun.stikieventorganizer.models.NetworkResponseModel
import id.shobrun.stikieventorganizer.models.entity.Invitation

class InvitationsResponse(
    var result: List<Invitation>,
    var status: String,
    var message: String
) : NetworkResponseModel