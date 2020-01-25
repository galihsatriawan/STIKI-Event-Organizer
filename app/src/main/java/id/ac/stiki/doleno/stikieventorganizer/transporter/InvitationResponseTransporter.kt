package id.ac.stiki.doleno.stikieventorganizer.transporter

import id.ac.stiki.doleno.stikieventorganizer.models.network.InvitationsResponse

class InvitationResponseTransporter : NetworkResponseTransporter<InvitationsResponse> {
    override fun additionalData(response: InvitationsResponse): InvitationsResponse {
        response.result = ArrayList()
        return response
    }

}