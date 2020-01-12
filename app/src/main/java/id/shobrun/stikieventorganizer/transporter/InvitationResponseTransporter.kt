package id.shobrun.stikieventorganizer.transporter

import id.shobrun.stikieventorganizer.models.network.InvitationsResponse

class InvitationResponseTransporter : NetworkResponseTransporter<InvitationsResponse>{
    override fun additionalData(response: InvitationsResponse): InvitationsResponse {
        response.result = ArrayList()
        return response
    }

}