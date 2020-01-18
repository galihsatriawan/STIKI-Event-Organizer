package id.shobrun.stikieventorganizer.transporter

import id.shobrun.stikieventorganizer.models.network.InvitationsResponse

class InvitationParticipantResponseTransporter : NetworkResponseTransporter<InvitationsResponse> {
    override fun additionalData(response: InvitationsResponse): InvitationsResponse {
        return response
    }
}