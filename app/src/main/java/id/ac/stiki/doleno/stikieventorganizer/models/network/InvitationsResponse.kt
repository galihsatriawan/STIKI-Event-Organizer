package id.ac.stiki.doleno.stikieventorganizer.models.network

import id.ac.stiki.doleno.stikieventorganizer.models.NetworkResponseModel
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Invitation

class InvitationsResponse(
    var result: List<Invitation>,
    var status: String,
    var message: String
) : NetworkResponseModel